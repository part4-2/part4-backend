package com.example.demo.global.aop;

import com.amazonaws.HttpMethod;
import com.example.demo.jwt.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Aspect
@Component
@Log4j2
public class ServiceAspect {
    private final HttpServletRequest servletRequest;
    private final ObjectMapper objectMapper;

    public ServiceAspect(HttpServletRequest servletRequest, ObjectMapper objectMapper) {
        this.servletRequest = servletRequest;
        this.objectMapper = objectMapper;
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Before(value = "execution(* *..*Controller.*(..))")
    public void logRequest(JoinPoint joinPoint) throws Throwable {

        UriInfo uriinfo = this.getUriInfo();

        if (uriinfo.equals(UriInfo.UNDEFINED)) {
            return; // 아직 정의되지 않았으므로 리턴 (던지는 게 더 나아보임. 무조건 추가, 관리 되도록)
        }

        String description = uriinfo.getDescription();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        Map<String, Object> requestParameters = IntStream.range(0, args.length)
                .filter(i -> args[i] != null && !args[i].getClass().equals(CustomUserDetails.class))
                .boxed()
                .collect(Collectors.toMap(i -> parameterNames[i], i -> args[i]));

        String requestParametersJson = objectMapper.writeValueAsString(requestParameters);
        log.info("요청 정보 : {}, URI = {} \n <Request info JSON> \n{} \n </Request info JSON>", () -> description, uriinfo::getUri, () -> requestParametersJson);
    }

    private UriInfo getUriInfo() {
        String uri = servletRequest.getRequestURI();
        String method = servletRequest.getMethod();
        HttpMethod httpMethod = HttpMethod.valueOf(method);
        return UriInfo.getInstance(uri, httpMethod);
    }
}

// 1. 요청 URI 와 밸류를 JSON(objectMapper 사용해서) 으로 변경
// 2. 예쁘지 않음. objectMapper.enable(SerializationFeature.INDENT_OUTPUT); 적용
// 3. URI 정보만으로는 무슨 일을 하는지 정확히 찍어 줄 수 없음.
//    URI, METHOD 별로 Enum 정의해서 enum 의 description 필드로 매서드 상세 정보 (ex:여행지 조회) 표현 가능하지 않을까? -> 적용
// 4. pathVariable 에 대한 처리 추가
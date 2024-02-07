package com.example.demo.config;

import com.example.demo.global.converter.SearchConditionConverter;
import com.example.demo.global.converter.WeatherConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    // TODO: 2/7/24 이 부분 나중에 어노테이션으로 뺄 수 있나 확인하기
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SearchConditionConverter());
        registry.addConverter(new WeatherConverter());
    }
}

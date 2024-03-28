package com.example.demo.global.web_hook;

import com.example.demo.global.web_hook.dto.DiscordRequest;
import com.example.demo.global.web_hook.dto.EmbeddedObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.UnsupportedMediaTypeException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class DiscordWebHookService {

    @Value("${discord.web-hook.url}")
    private String url;

    @Value(value = "${discord.web-hook.username}")
    private String username;

    public void sendDiscordAlert(Exception e, String uri){
        EmbeddedObject embeddedObject = EmbeddedObject.of(e, uri);
        DiscordRequest discordRequest = new DiscordRequest(url, username, List.of(embeddedObject));
        String jsonString = convert(discordRequest);
        sendJsonToDiscord(jsonString);
    }

    // json 형식으로 변환
    private String convert(DiscordRequest discordRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(discordRequest);
        } catch (JsonProcessingException e){
            throw new UnsupportedMediaTypeException("잘못된 형식의 에러입니다");
        }
    }

    // post url to discord
    private void sendJsonToDiscord(String json){
        WebClient client = WebClient.create();

        log.info("에러 발생 json info {} ", () -> json);

        Mono<String> responseMono = client.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .retrieve()
                .bodyToMono(String.class);

        // discord 응답 값
        responseMono.subscribe(
                responseBody -> {
                    System.out.println("Data sent successfully!");
                    System.out.println("Response: " + responseBody);
                },
                error -> {
                    System.out.println("Failed to send data. Error: " + error.getMessage());
                }
        );
    }
}
package com.example.demo.global.webHook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DiscordWebHook {

    private final String url = "https://discordapp.com/api/webhooks/1222722687286378566/UxOJryrHYC57NBd23UdFRxVLSW2aY9_y1h6v0W6HhEEJaXCC2s3cqTw4hjJuwGeJxY0E";
    private final String username = "Bot";
    private final List<EmbedObject> embeds = new ArrayList<>();

    public void addEmbed(EmbedObject embedObject){
        embeds.add(embedObject);
    }

    @Setter
    @Getter
    public static class EmbedObject {

        private final String title;
        private final String description;
        private final Integer color;
        private final List<Field> fields = new ArrayList<>();

        public EmbedObject(String title, String description, int color, Field currentTime, Field url, Field stackTrace){
            this.title = title;
            this.description = description;
            this.color = color;
            this.fields.add(currentTime);
            this.fields.add(url);
            this.fields.add(stackTrace);
        }

        @Getter
        public static class Field {

            private final String name;
            private final String value;
            private final boolean inline;

            public Field(String name, String value, boolean inline) {
                this.name = name;
                this.value = value;
                this.inline = inline;
            }
        }
    }

    // json 형식으로 변환
    public String jsonConverter(DiscordWebHook discordWebHook) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(discordWebHook);
    }

    // post url to discord
    public void sendJsonToDiscord(String json){
        WebClient client = WebClient.create();
        System.out.println(json);

        Mono<String> responseMono = client.post()
                .uri(this.url)
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

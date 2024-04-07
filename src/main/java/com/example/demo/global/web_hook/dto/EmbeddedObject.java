package com.example.demo.global.web_hook.dto;

import java.util.Arrays;
import java.util.List;

public record EmbeddedObject(String title, String description, Integer color, List<DiscordRequestField> fields) {
    private static final int RED_COLOR = 0xFF0000;
    public static EmbeddedObject of(Exception e, String uri){
        return new EmbeddedObject(
                "\uD83D\uDEA8 에러 발생",
                e.getMessage(),
                RED_COLOR,
                List.of(
                        DiscordRequestField.ofTime(),
                        DiscordRequestField.ofErrorUri(uri),
                        DiscordRequestField.ofStackTrace(Arrays.toString(e.getStackTrace()))
                )
        );
    }
}
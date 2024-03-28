package com.example.demo.global.web_hook.dto;

import com.example.demo.global.utils.DateUtils;

import java.time.LocalDateTime;

public record DiscordRequestField(String name, String value, boolean inline) {

    public static DiscordRequestField ofTime() {
        return new DiscordRequestField(
                "발생 시간",
                DateUtils.parserErrorTimeToString(),
                false
        );
    }

    public static DiscordRequestField ofErrorUri(String root) {
        return new DiscordRequestField(
                "발생 원인",
                root,
                false
        );
    }

    public static DiscordRequestField ofStackTrace(String cause) {
        return new DiscordRequestField(
                "에러 스택 트레이스 : ",
                cause.substring(0, Math.min(1000, cause.length())),
                false
        );
    }
}
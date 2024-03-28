package com.example.demo.global.web_hook.dto;

public record DiscordRequest(String url, String username, EmbeddedObject embeds) {
}
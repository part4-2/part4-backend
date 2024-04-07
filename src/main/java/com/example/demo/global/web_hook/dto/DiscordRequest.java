package com.example.demo.global.web_hook.dto;

import java.util.List;

public record DiscordRequest(String url, String username, List<EmbeddedObject> embeds) {
}
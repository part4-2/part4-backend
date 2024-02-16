package com.example.demo.global.utils;

import org.h2.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    public static LocalDateTime parseVisitingTime(String visitingTimeString) {
        if (StringUtils.isNullOrEmpty(visitingTimeString)) {
            return null;
        }
        return LocalDateTime.parse(visitingTimeString, formatter);
    }
}
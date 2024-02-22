package com.example.demo.global.utils;

import com.example.demo.global.exception.DateTimeCustomException;
import org.h2.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    private DateUtils() {
        throw new UnsupportedOperationException("util class");
    }

    private static final String REGEX = "yyyyMMddHHmm";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(REGEX);

    public static LocalDateTime parseVisitingTime(String visitingTimeString) {
        if (StringUtils.isNullOrEmpty(visitingTimeString)) {
            return null;
        }
        try {
            return LocalDateTime.parse(visitingTimeString, formatter);
        } catch (DateTimeParseException e){
            throw new DateTimeCustomException.InvalidFormatOfDateCustomException(REGEX, visitingTimeString);
        }
    }

    public static String parseTimeToString(LocalDateTime dateTime){
        return dateTime.format(formatter);
    }
}
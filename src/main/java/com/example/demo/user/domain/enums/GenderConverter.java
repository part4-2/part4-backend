package com.example.demo.user.domain.enums;

import jakarta.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }

        return gender.name();
    }

    @Override
    public Gender convertToEntityAttribute(String s) {
        return Gender.getInstance(s);
    }
}

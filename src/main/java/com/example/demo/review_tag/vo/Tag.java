package com.example.demo.review_tag.vo;

import com.example.demo.global.converter.AbstractCodedEnumConverter;
import com.example.demo.global.converter.CodedEnum;
import jakarta.persistence.Converter;

public enum Tag implements CodedEnum<String> {
    FOODS("맛집"),
    SIGHT("관광지"), // SIGHT_SEEING?
//    NONE("없음")
    ;

    private final String description;

    Tag(String description) {
        this.description = description;
    }

    @Override
    public String getCode() {
        return description;
    }

    @Converter(autoApply = true)
    static class TagConverter extends AbstractCodedEnumConverter<Tag, String>{
        protected TagConverter() {
            super(Tag.class);
        }
    }
}

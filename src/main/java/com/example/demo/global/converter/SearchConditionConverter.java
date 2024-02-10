package com.example.demo.global.converter;

import com.example.demo.review.domain.vo.SearchCondition;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;

public class SearchConditionConverter implements Converter<String, SearchCondition> {
    @Override
    public SearchCondition convert(@Nonnull String name) {
        return SearchCondition.getInstance(name);
    }
}

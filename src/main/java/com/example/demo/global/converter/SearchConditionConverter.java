package com.example.demo.global.converter;

import com.example.demo.review.domain.vo.SearchCondition;
import org.springframework.core.convert.converter.Converter;

public class SearchConditionConverter implements Converter<String, SearchCondition> {
    @Override
    public SearchCondition convert(String name) {
        return SearchCondition.getInstance(name);
    }
}

package com.example.demo.review.domain.vo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SearchConditionTest {
    @ParameterizedTest
    @ValueSource(strings = {"LIKES", "NEW", "NONE"})
    void getInstanceTest(String value) {
        SearchCondition expected = SearchCondition.getInstance(value);
        assertThat(expected.name()).isEqualTo(value);
    }

    @ParameterizedTest
    @ValueSource(strings = {"FOO", "FOOOOOOO", "125125"})
    void getInstanceTest_WITH_INVALID_INPUT(String value) {
        SearchCondition expected = SearchCondition.getInstance(value);
        assertThat(expected).isEqualTo(SearchCondition.NONE);
    }
}
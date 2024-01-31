package com.example.demo.review.domain.vo;

import com.example.demo.review.exception.ReviewException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ContentTest {
    private static final String TEST_VALUE = "TEST";
    private static final int TEST_LENGTH = 144;
    @Test
    void testEquals() {
        final Content content = new Content(TEST_VALUE);
        final Content another = new Content(TEST_VALUE);
        assertThat(content).isEqualTo(another);
    }

    @Test
    void testHashCode() {
        final Content content = new Content(TEST_VALUE);
        final Content another = new Content(TEST_VALUE);
        assertThat(content).hasSameHashCodeAs(another);
    }

    @Test
    void getValue() {
        final Content content = new Content(TEST_VALUE);
        assertThat(content.getValue()).isEqualTo(TEST_VALUE);
    }

    @Test
    void validateNull() {
        assertThrows(NullPointerException.class, () -> new Content(null));
    }

    @Test
    void validateLength() {
        String testValue = TEST_VALUE.repeat(36);
        testValue += "1";
        final String testValueWith145Length = testValue;
        assertThat(testValueWith145Length).hasSize(TEST_LENGTH + 1);
        assertThrows(ReviewException.ContentLengthException.class, () -> new Content(testValueWith145Length));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", ""})
    void validateEmpty(final String blankValue) {
        assertThrows(ReviewException.ContentBlankException.class, () -> new Content(blankValue));
    }
}
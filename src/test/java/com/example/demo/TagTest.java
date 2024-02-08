package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;

class TagTest {
    @Test
    void foo() {
        String nullSafeString = StringUtils.nullSafeToString(null);
        assertThat(nullSafeString).isEqualTo("null");
    }
}

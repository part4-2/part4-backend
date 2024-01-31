package com.example.demo.review.domain.vo;

import com.example.demo.review.exception.ReviewException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class Content {
    private static final int TEMP_LENGTH = 144;
    @Column(name = "content", nullable = false, length = TEMP_LENGTH)
    private String value;

    public Content(final String value) {
        this.validateNull(value);
        this.validate(value);
        this.value = value;
    }

    private void validateNull(final String value){
        if (Objects.isNull(value)){
            throw new NullPointerException("내용이 없습니다");
        }
    }

    private void validate(final String value){
        if (value.length() > TEMP_LENGTH){
            throw new ReviewException.ContentLengthException(TEMP_LENGTH, value);
        }
        if (value.isBlank()){
            throw new ReviewException.ContentBlankException();
        }
    }
}

package com.example.demo.review_tag.domain.vo;

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
public class TagName {
    private static final int TAG_NAME_LENGTH = 10;
    @Column(name = "tag_name", nullable = false, length = TAG_NAME_LENGTH)
    private String value;

    public TagName(final String value) {
        this.validateNull(value);
        this.validate(value);
        this.value = value;
    }

    private void validateNull(final String value){
        if (Objects.isNull(value)){
            throw new NullPointerException("태그 제목이 없습니다");
        }
    }

    private void validate(final String value){
        if (value.length() > TAG_NAME_LENGTH){
            throw new ReviewException.ContentLengthException(TAG_NAME_LENGTH, value);
        }
        if (value.isBlank()){
            throw new ReviewException.ContentBlankException();
        }
    }
}

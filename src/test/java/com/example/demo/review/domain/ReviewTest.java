package com.example.demo.review.domain;

import com.example.demo.common.test_instance.SpotFixture;
import com.example.demo.common.test_instance.UserFixture;
import com.example.demo.review.domain.vo.Content;
import com.example.demo.review.domain.vo.Title;
import com.example.demo.review.domain.vo.Weather;
import com.example.demo.spot.domain.Spot;
import com.example.demo.user.domain.entity.Users;
import org.junit.jupiter.api.Test;

import static com.example.demo.common.test_instance.ReviewFixture.REVIEW_OF_ALL_FIELD_VALUE_IS_FOO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReviewTest {
    private static final String FOO = "FOO";
    private static Review REVIEW = REVIEW_OF_ALL_FIELD_VALUE_IS_FOO;

    @Test
    void equalsTest() {
        Title title = REVIEW.getTitle();
        Content content = REVIEW.getContent();
        Weather weather = REVIEW.getWeather();
        Spot spot = REVIEW.getSpot();
        Users users = REVIEW.getUsers();

        assertEquals(content, new Content(FOO));
        assertEquals(SpotFixture.FOO_SPOT, spot);
        assertEquals(UserFixture.FOO, users);
        assertEquals(title, new Title(FOO));
        assertEquals(Weather.NONE, weather);
    }

    @Test
    void updateTest() {
        // 업데이트 후 값 확인
        Weather newWeather = Weather.SUNNY;
        Title newTitle = new Title("New Title");
        Content newContent = new Content("New Content");

        Review review = new Review(
                REVIEW.getTitle(),
                REVIEW.getContent(),
                REVIEW.getWeather(),
                REVIEW.getUsers(),
                REVIEW.getSpot()
        );
        review.update(newWeather, newTitle, newContent);

        assertEquals(review.getWeather(), newWeather);
        assertEquals(review.getTitle(), newTitle);
        assertEquals(review.getContent(), newContent);
    }

    @Test
    void hashCodeTest() {
        Review review1 = REVIEW;
        Review review2 = REVIEW;
        assertEquals(review1.hashCode(), review2.hashCode());
    }
}
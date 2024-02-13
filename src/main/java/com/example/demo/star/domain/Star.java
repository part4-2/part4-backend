package com.example.demo.star.domain;

import com.example.demo.spot.domain.Spot;
import com.example.demo.star.domain.vo.StarRank;
import com.example.demo.user.domain.entity.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        indexes = {
                @Index(name = "star_unique_composite_idx", columnList = "user_id, place_id", unique = true)
        }
)
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Spot spot;
    private StarRank starRank;

    @Builder
    public Star(Users users, Spot spot, StarRank starRank) {
        this.users = users;
        this.spot = spot;
        this.starRank = starRank;
    }

    public void update(StarRank starRank){
        this.starRank = starRank;
    }
}

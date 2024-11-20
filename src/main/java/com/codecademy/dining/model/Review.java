package com.codecademy.dining.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="REVIEW")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private long id;
    @Column(name="dining_user_id")
    @Getter
    @Setter
    private long diningUserId;
    @Column(name="restaurant_id")
    @Getter
    @Setter
    private long restaurantId;
    @Column(name="peanut_score")
    @Getter
    @Setter
    private int peanutScore;
    @Column(name="egg_score")
    @Getter
    @Setter
    private int eggScore;
    @Column(name="dairy_score")
    @Getter
    @Setter
    private int dairyScore;
    @Column(name="comment")
    @Getter
    @Setter
    private String comment;
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private ReviewStatus status;
}

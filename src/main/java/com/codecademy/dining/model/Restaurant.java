package com.codecademy.dining.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="RESTAURANT")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private long id;
    @Column(name="name")
    @Getter
    @Setter
    private String name;
    @Column(name="zip_code")
    @Getter
    @Setter
    private String zipCode;
    @Column(name="peanut_score")
    @Getter
    @Setter
    private double peanutScore;
    @Column(name="egg_score")
    @Getter
    @Setter
    private double eggScore;
    @Column(name="dairy_score")
    @Getter
    @Setter
    private double dairyScore;
    @Column(name="avg_score")
    @Getter
    @Setter
    private double avgScore;
    @Column(name="total_reviews")
    @Getter
    @Setter
    private int totalReviews;
}

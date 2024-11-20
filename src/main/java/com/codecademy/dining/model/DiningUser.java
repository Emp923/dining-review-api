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
@Table(name="DINING_USER")
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class DiningUser {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private long id;
    @Column(name="name", unique=true)
    @Getter
    @Setter
    private String name;
    @Column(name="city")
    @Getter
    @Setter
    private String city;
    @Column(name="state")
    @Getter
    @Setter
    private String state;
    @Column(name="zip_code")
    @Getter
    @Setter
    private String zipCode;
    @Column(name="peanut_allergy")
    @Getter
    @Setter
    private boolean peanutAllergy;
    @Column(name="egg_allergy")
    @Getter
    @Setter
    private boolean eggAllergy;
    @Column(name="dairy_allergy")
    @Getter
    @Setter
    private boolean dairyAllergy;
}

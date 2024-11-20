package com.codecademy.dining.model;

import lombok.Getter;
import lombok.Setter;

public class AdminAction {
    @Getter
    @Setter
    private long reviewId;
    @Getter
    @Setter
    private boolean approved;
}

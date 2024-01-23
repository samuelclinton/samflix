package com.samflix.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViewStats {

    private Long totalViews;
    private Double averageViews;

}

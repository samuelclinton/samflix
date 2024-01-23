package com.samflix.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    private Long totalVideos;
    private Long likedVideos;
    private Long totalViews;
    private Double averageViews;

}

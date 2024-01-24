package com.samflix.backend.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VideoFilter {

    private String title;
    private LocalDate creationDate;

}

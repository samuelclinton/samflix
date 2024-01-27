package com.samflix.backend.infrastructure.gateways;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableFactory {

    public static Pageable createPageable(int size, int page, String sortDirection, String sortProperty) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortProperty);
        return PageRequest.of(page, size, sort);
    }

    private PageableFactory() {
    }

}

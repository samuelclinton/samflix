package com.samflix.backend.infrastructure.gateways;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PageableFactoryTest {

    @Test
    void should_createPageable() {
        int page = 1;
        int size = 5;
        String property = "views";
        String order = "asc";

        Pageable pageable = PageableFactory.createPageable(size, page, order, property);

        assertEquals(size, pageable.getPageSize());
        assertEquals(page, pageable.getPageNumber());

        Sort.Order sortOrder = pageable.getSort().getOrderFor(property);
        assertNotNull(sortOrder);
        assertEquals(Sort.Direction.ASC, sortOrder.getDirection());
    }
}

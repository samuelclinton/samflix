package com.samflix.backend.api.controller;

import com.samflix.backend.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryController {

    Category create();
    Category update(String categoryId);
    Page<Category> list(Pageable pageable);
    void delete(String categoryId);

}

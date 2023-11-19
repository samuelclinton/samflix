package com.samflix.backend.api.controller.category;

import com.samflix.backend.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryControllerV1 implements CategoryController {

    @Override
    @PostMapping
    public Category create() {
        return null;
    }

    @Override
    @PutMapping("/{categoryId}")
    public Category update(@PathVariable String categoryId) {
        return null;
    }

    @Override
    @GetMapping
    public Page<Category> list(Pageable pageable) {
        return null;
    }

    @Override
    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable String categoryId) {

    }

}

package com.samflix.backend.utils;

import com.samflix.backend.domain.model.Category;

public abstract class CategoryHelper {

    public static final String CATEGORY_ID = "655c1357260db97410f957d2";
    public static final String CATEGORY_NAME = "Category";
    public static final String CATEGORY_UPDATED_NAME = "New category name";

    public static Category generateCategory() {
        return Category.builder().id(CATEGORY_ID).name(CATEGORY_NAME).build();
    }

}

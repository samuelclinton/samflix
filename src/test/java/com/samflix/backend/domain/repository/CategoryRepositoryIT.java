package com.samflix.backend.domain.repository;

import com.samflix.backend.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static com.samflix.backend.utils.CategoryHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureDataMongo
class CategoryRepositoryIT {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        categoryRepository.deleteAll().block();
    }

    @Test
    void shouldSaveCategory() {
        var category = saveCategory(generateCategory());

        assertThat(category)
                .isNotNull()
                .isInstanceOf(Category.class);

        assertThat(category.getId())
                .isEqualTo(CATEGORY_ID);

        assertThat(category.getName())
                .isEqualTo(CATEGORY_NAME);
    }

    @Test
    void shouldUpdateCategoryName() {
        var category = saveCategory(generateCategory());

        assertThat(category.getName())
                .isEqualTo(CATEGORY_NAME);

        category.setName(CATEGORY_UPDATED_NAME);

        var updateCategory = saveCategory(category);

        assertThat(updateCategory.getName())
                .isNotNull()
                .isEqualTo(CATEGORY_UPDATED_NAME);
    }

    @Test
    void shouldListCategories() {
        var categories = new ArrayList<Category>();

        categories.add(generateCategory());
        categories.add(Category
                .builder()
                .id("655e64277caa1eae06196427")
                .name("Another category")
                .build());

        categoryRepository.saveAll(categories).blockLast();

        var savedCategoriesList = new ArrayList<Category>();

        categoryRepository.findAll().toIterable().forEach(savedCategoriesList::add);

        assertThat(savedCategoriesList)
                .isNotNull()
                .hasSize(2)
                .containsAll(categories);
    }

    @Test
    void shouldDeleteCategory() {
        var category = saveCategory(generateCategory());

        assertThat(category.getId())
                .isNotNull()
                .isEqualTo(CATEGORY_ID);

        categoryRepository.deleteById(CATEGORY_ID).block();

        var deletedCategory = categoryRepository.findById(CATEGORY_ID).block();

        assertThat(deletedCategory).isNull();
    }

    private Category saveCategory(Category category) {
        Mono<Category> save = categoryRepository.save(category);
        Mono<Category> find = categoryRepository.findById(category.getId());
        Mono<Category> mono = Mono.from(save).then(find);
        return mono.block();
    }

}

package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends StudyApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {

        String type = "COMPUTER";
        String title = "컴퓨터/가전";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(LocalDateTime.now());
        category.setCreatedBy("AdminServer");

        Category newCategory = categoryRepository.save(category);
        Assertions.assertNotNull(newCategory);
        Assertions.assertEquals(newCategory.getType(), type);
        Assertions.assertEquals(newCategory.getTitle(), title);

    }

    @Test
    public void read() {
        Optional<Category> category = categoryRepository.findById(1L);
        Assertions.assertNotNull(category.isPresent());
    }

    @Test
    public void readByType() {
        String type = "COMUTER";
        Optional<Category> category = categoryRepository.findByType(type);
        category.ifPresent(c -> {
            Assertions.assertEquals(c.getType(), type);
        });
    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {

    }
}

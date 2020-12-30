package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.Item;
import jdk.nashorn.internal.runtime.options.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest  extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = new Item();
        item.setName("삼성 노트북");
        item.setTitle("삼성 노트북 A100");
        item.setStatus("UNREGISTERED");
        item.setPrice(100000);
        item.setContent("2019년형 삼성 노트북 A100");
        item.setBrandName("삼성");
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);
        Assertions.assertEquals(newItem.getName(), "삼성 노트북");
    }

    @Test
    public void read() {

        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        Assertions.assertTrue(item.isPresent());

        item.ifPresent(i -> {
            System.out.println(i);
        });
    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {

    }
}

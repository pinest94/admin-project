package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        // String sql = insert into user (%s, %s, %d) value ( account, email, age); (X)

        for(int i=13942; i<=1000000; i++) {
            User user = new User();
            String number = String.format("%07d", i);
            user.setAccount("TestUser" + number);
            user.setEmail("TestUser"+number+"@gmail.com");
            user.setPhoneNumber("010-1111-1112");
            user.setCreatedAt(LocalDateTime.now());
            user.setCreatedBy("TestUser"+number);

            userRepository.save(user);
            // System.out.println("newUser : " + newUser);
        }

    }

    @Test
    public void read() {
        // userRepository.findAll();
        Optional<User> user = userRepository.findById(29835L);
        user.ifPresent(selectUser->{
            System.out.println("user : " + selectUser);
        });
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(29835L);
        user.ifPresent(selectUser->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("Admin");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional // delete 테스트를 위해 데이터를 지울 수 없는 경우에 해당 어노테이션을 사용할 경우 테스트가 종료되면 rollback하기 때문에 실제 table에 영향을 주지 않는다.
    public void delete() {
        Optional<User> user = userRepository.findById(29833L);

        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(29833L);

        Assertions.assertFalse(deleteUser.isPresent());
    }
}

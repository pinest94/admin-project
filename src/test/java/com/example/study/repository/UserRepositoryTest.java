package com.example.study.repository;


import com.example.study.StudyApplicationTests;
import com.example.study.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1234-5678";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);
        Assertions.assertEquals(newUser.getAccount(), account);
        Assertions.assertEquals(newUser.getStatus(), status);
        Assertions.assertEquals(newUser.getEmail(), email);
        Assertions.assertEquals(newUser.getPhoneNumber(), phoneNumber);
    }

    @Test
    @Transactional
    public void read() {
        User user1 = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");
        Assertions.assertNull(user1);
        User user2 = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1234-5678");
        Assertions.assertNotNull(user2);
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

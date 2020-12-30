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
        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test03@gmail.com";
        String phoneNumber = "010-1233-5679";

        User user = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .phoneNumber(phoneNumber).build();

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
        user2.getOrderGroupList().stream().forEach(og -> {
            System.out.println("--------- 주문 묶음 -----------");
            System.out.println("수령인 : " + og.getRevName());
            System.out.println("수령지 : " + og.getRevAddress());
            System.out.println("총 금액 : " + og.getTotalPrice());
            System.out.println("총 수량 : " + og.getTotalQuantity());
            System.out.println("--------- 주문 상세 -----------");
            og.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                System.out.println("카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태 : " + orderDetail.getStatus());
                System.out.println("도착 예정일자 : " +orderDetail.getArrivalDate());

            });
        });
        Assertions.assertNotNull(user2);
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser->{
            selectUser.setAccount("PPPP");
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

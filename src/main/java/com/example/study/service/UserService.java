package com.example.study.service;

import com.example.study.interfaces.CrudInterface;
import com.example.study.model.User;
import com.example.study.network.Header;
import com.example.study.network.request.UserApiRequest;
import com.example.study.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED") // 추후 enum타입으로 바꿀예정
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> UserApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // 1. User 가져오기
        Optional<User> optional = userRepository.findById(id);

        // 2. 가져온 데이터 -> UserApiResponse return
        return optional
                .map(user -> response(user))
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 찾기
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional
                .map(user -> {
                             user.setAccount(userApiRequest.getAccount())
                                     .setEmail(userApiRequest.getEmail())
                                     .setPassword(userApiRequest.getPassword())
                                     .setPhoneNumber(userApiRequest.getPhoneNumber())
                                     .setStatus(userApiRequest.getStatus())
                                     .setRegisteredAt(userApiRequest.getRegisteredAt())
                                     .setUnregisteredAt(userApiRequest.getUnregisteredAt())
                                     .setUpdatedAt(LocalDateTime.now());

                    return user;
                })
                .map(user -> userRepository.save(user)) // 3. update
                .map(user -> response(user))  // 4. userApiResponse
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> delete(Long id) {
        return null;
    }

    private Header<UserApiResponse> response(User user) {
        // user -> userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo: 암호화
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return Header.OK(userApiResponse);
    }
}

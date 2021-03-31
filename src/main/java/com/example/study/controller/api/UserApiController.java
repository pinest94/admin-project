package com.example.study.controller.api;

import com.example.study.interfaces.CrudInterface;
import com.example.study.network.Header;
import com.example.study.network.request.UserApiRequest;
import com.example.study.network.response.UserApiResponse;
import com.example.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    UserService userService;

    @Override
    @PostMapping("") // /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) { // C
        log.info("{}", request);
        return userService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header read(@PathVariable("id") Long id) {
        return null;
    }

    @Override
    @PutMapping("") // /api/user
    public Header<UserApiResponse> update(Header<UserApiRequest> userApiRequest) {
        return null;
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(Long id) {
        return null;
    }

}

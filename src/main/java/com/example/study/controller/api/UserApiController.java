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
    public Header<UserApiResponse> read(@PathVariable("id") Long id) {
        log.info("read id : {}", id);
        return userService.read(id);
    }

    @Override
    @PutMapping("") // /api/user
    public Header<UserApiResponse> update(@RequestBody  Header<UserApiRequest> request) {
        log.info("{}", request);
        return userService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(@PathVariable("id") Long id) {
        log.info("read id : {}", id);
        return userService.delete(id);
    }

}

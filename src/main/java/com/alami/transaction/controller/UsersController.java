package com.alami.transaction.controller;

import com.alami.transaction.entity.User;
import com.alami.transaction.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class UsersController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("users")
    public ResponseEntity<Object> findAllPaginatedUsers(@RequestParam(value = "page", defaultValue = "0", required = true) Integer page,
                                                                @RequestParam(value = "size", defaultValue = "10", required = true) Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> userPaginated = userService.findAllUsersPagination(pageable);

        return new ResponseEntity<>(userPaginated, HttpStatus.OK);
    }
}

package com.example.datamanagementserver.controller;

import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.user.UserLogin;
import com.example.datamanagementserver.dto.user.UserRequest;
import com.example.datamanagementserver.dto.user.UserResponse;
import com.example.datamanagementserver.mapper.DataMapper;
import com.example.datamanagementserver.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final DataMapper dataMapper;

    @PostMapping("/login")
    public ResponseEntity<String> create(@RequestBody UserLogin userLogin) {
        boolean isLogin = userService.login(userLogin.getEmail(), userLogin.getPassword());
        if (isLogin) {
            return ResponseEntity.ok("Good");
        }
        else {
            return ResponseEntity.ok("Incorrect password or email");
        }
    }

    @GetMapping()
    public List<UserResponse> getAll() {
        return dataMapper.convertFromUserListToResponse(userService.getAll());
    }

    @PostMapping()
    public UserResponse create(@RequestBody UserRequest userRequest) {
        return dataMapper.convertFromUserToResponse(userService.create(userRequest));
    }

    @PutMapping("/update/{id}")
    public UserResponse update(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        return dataMapper.convertFromUserToResponse(userService.updateById(id, userRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("Good");
    }

    @DeleteMapping("/delete/filter")
    public ResponseEntity<String> deleteByFilter(@RequestBody FilterDto filterDto) {
        userService.deleteByFilter(filterDto);
        return ResponseEntity.ok("Good");
    }
}

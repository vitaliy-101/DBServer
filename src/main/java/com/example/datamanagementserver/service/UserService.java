package com.example.datamanagementserver.service;

import com.example.datamanagementserver.dto.FilterDto;
import com.example.datamanagementserver.dto.user.UserRequest;
import com.example.datamanagementserver.entity.User;
import com.example.datamanagementserver.exceptions.NotFoundByFilterFieldException;
import com.example.datamanagementserver.exceptions.NotFoundByIdException;
import com.example.datamanagementserver.repository.GroupRepository;
import com.example.datamanagementserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupService groupService;

    public boolean login(String email, String password) {
        return userRepository.existsByPasswordAndEmail(password, email);
    }

    public User create(UserRequest userRequest) {
        User user = new User();
        user.setGroup(groupService.findById(userRequest.getGroupId()));
        user.setEmail(userRequest.getEmail());
        user.setFirstname(userRequest.getFirstname());
        user.setPassword(userRequest.getPassword());
        user.setSurname(userRequest.getSurname());
        user.setPatronymic(userRequest.getPatronymic());
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void existById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundByIdException("User", id);
        }
    }

    public User findById(Long id) {
        existById(id);
        return userRepository.getReferenceById(id);
    }

    public User updateById(Long id, UserRequest userRequest) {
        User user = findById(id);
        user.setGroup(groupService.findById(userRequest.getGroupId()));
        user.setEmail(userRequest.getEmail());
        user.setFirstname(userRequest.getFirstname());
        user.setPassword(userRequest.getPassword());
        user.setSurname(userRequest.getSurname());
        user.setPatronymic(userRequest.getPatronymic());
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        existById(id);
        userRepository.deleteById(id);
    }

    public void deleteByFilter(FilterDto filterDto) {
        switch (filterDto.getField()) {
            case "email" -> userRepository.deleteByEmail(filterDto.getTextFilter());
            case "firstname" -> userRepository.deleteByFirstname(filterDto.getTextFilter());
            case "surname" -> userRepository.deleteBySurname(filterDto.getTextFilter());
            case "patronymic" -> userRepository.deleteByPatronymic(filterDto.getTextFilter());
            default -> throw new NotFoundByFilterFieldException();
        }
    }

}

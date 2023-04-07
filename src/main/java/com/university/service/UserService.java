package com.university.service;

import com.university.model.Users;
import com.university.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(Users user) {
        userRepository.save(user);
    }

    public void createUserFromList(List<Users> userList) {
        userRepository.saveAll(userList);
    }

    public Users findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public Users findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow();
    }

    public List<Users> findAllUser() {
        return userRepository.findAll();
    }

    public void updateUser(Users users) {
        Users userById = findUserById(users.getId());
        userById.setEmail(users.getEmail());
        userById.setUserName(users.getUserName());
        userById.setRoles(users.getRoles());
        userRepository.save(userById);
    }

    public void deleteUser(Users users) {
        userRepository.delete(users);
    }
}

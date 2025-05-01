package com.example.Services;

import com.example.Model.User;
import com.example.Model.UserDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findById(Long id);

    UserDTO save(UserDTO userdto);

    UserDTO update(Long id, String address);

    void delete(Long id);

}

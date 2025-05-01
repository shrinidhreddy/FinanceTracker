package com.example.Services;

import com.example.Model.User;
import com.example.Model.UserDTO;
import com.example.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public UserDTO save(UserDTO userdto) {
        User user = convertToEntity(userdto);
        User savedUser=userRepository.save(user);
        return convertToDTO(savedUser);
    }




    @Override
    public UserDTO update(Long id, String address) {
        User user =userRepository.findById(id).orElseThrow();
        user.setAddress(address);
        User updatedUser=userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getNationality(), user.getPan(), user.getAddress());
    }

    private User convertToEntity(UserDTO userdto) {
        User user = new User();
        user.setId(userdto.id());
        user.setUsername(userdto.username());
        user.setPassword(userdto.password());
        user.setNationality(userdto.nationality());
        user.setPan(userdto.pan());
        user.setAddress(userdto.address());
        return user;
    }
}

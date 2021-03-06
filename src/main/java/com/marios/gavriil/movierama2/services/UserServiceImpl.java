package com.marios.gavriil.movierama2.services;

import com.marios.gavriil.movierama2.dto.UserDto;
import com.marios.gavriil.movierama2.exceptions.UserAlreadyExistsException;
import com.marios.gavriil.movierama2.model.User;
import com.marios.gavriil.movierama2.repositories.UserRepository;
import com.marios.gavriil.movierama2.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User registerUserAccount(UserDto userDto) throws Exception {

        if(userRepository.findUserByUsername(userDto.getUsername()) != null){
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEnabled(true);
        user.setRole("ROLE_USER");

        return userRepository.save(user);
    }

    @Override
    public User loadUserDetails(String username) {
        return userRepository.findUserByUsername(username);
    }
}

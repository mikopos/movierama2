package com.marios.gavriil.movierama2.services.interfaces;


import com.marios.gavriil.movierama2.dto.UserDto;
import com.marios.gavriil.movierama2.model.User;

public interface UserService {

    public User registerUserAccount(UserDto userDto);
}

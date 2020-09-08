package com.marios.gavriil.movierama2.services;

import com.marios.gavriil.movierama2.dto.UserDto;
import com.marios.gavriil.movierama2.model.User;
import com.marios.gavriil.movierama2.services.interfaces.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public ModelMapper modelMapper() { return new ModelMapper();}
    }

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private UserService userService;

    @Test
    public void registerUserAccountSuccessful() throws Exception {
        User givenUser = createGivenUser();
        UserDto userDto = modelMapper.map(givenUser, UserDto.class);
        Mockito.when(userService.registerUserAccount(userDto))
                .thenReturn(givenUser);
        User expectedUser = userService.registerUserAccount(userDto);
        assertEquals(expectedUser, givenUser);
    }

    @Test
    public void registerUserAccountNullUsername() throws Exception {
        User missingUsernameUser = createGivenUser();
        missingUsernameUser.setUsername(null);
        UserDto userDto = modelMapper.map(missingUsernameUser, UserDto.class);

        Mockito.when(userService.registerUserAccount(userDto))
                .thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, () -> {
            userService.registerUserAccount(userDto);
        });
    }

    @Test
    public void registerUserAccountEmptyUsername() throws Exception {
        User missingUsernameUser = createGivenUser();
        missingUsernameUser.setUsername("");
        UserDto userDto = modelMapper.map(missingUsernameUser, UserDto.class);

        Mockito.when(userService.registerUserAccount(userDto))
                .thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, () -> {
            userService.registerUserAccount(userDto);
        });
    }

    @Test
    public void registerUserAccountNullPassword() throws Exception {
        User missingPasswordUser = createGivenUser();
        missingPasswordUser.setPassword(null);
        UserDto userDto = modelMapper.map(missingPasswordUser, UserDto.class);


        Mockito.when(userService.registerUserAccount(userDto))
                .thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, () -> {
            userService.registerUserAccount(userDto);
        });
    }

    @Test
    public void registerUserAccountEmptyPassword() throws Exception {
        User missingPasswordUser = createGivenUser();
        missingPasswordUser.setPassword("");
        UserDto userDto = modelMapper.map(missingPasswordUser, UserDto.class);

        Mockito.when(userService.registerUserAccount(userDto))
                .thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, () -> {
            userService.registerUserAccount(userDto);
        });
    }

    @Test
    public void registerUserAccountNullFirstName() throws Exception {
        User missingFirstNameUser = createGivenUser();
        missingFirstNameUser.setFirstName(null);
        UserDto userDto = modelMapper.map(missingFirstNameUser, UserDto.class);

        Mockito.when(userService.registerUserAccount(userDto))
                .thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, () -> {
            userService.registerUserAccount(userDto);
        });
    }

    @Test
    public void registerUserAccountEmptyFirstName() throws Exception {
        User missingFirstNameUser = createGivenUser();
        missingFirstNameUser.setFirstName("");
        UserDto userDto = modelMapper.map(missingFirstNameUser, UserDto.class);

        Mockito.when(userService.registerUserAccount(userDto))
                .thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, () -> {
            userService.registerUserAccount(userDto);
        });
    }

    @Test
    public void registerUserAccountNullLastName() throws Exception {
        User missingLastNameUser = createGivenUser();
        missingLastNameUser.setLastName(null);
        UserDto userDto = modelMapper.map(missingLastNameUser, UserDto.class);

        Mockito.when(userService.registerUserAccount(userDto))
                .thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, () -> {
            userService.registerUserAccount(userDto);
        });
    }

    @Test
    public void registerUserAccountEmptyLastName() throws Exception {
        User missingLastNameUser = createGivenUser();
        missingLastNameUser.setLastName("");
        UserDto userDto = modelMapper.map(missingLastNameUser, UserDto.class);

        Mockito.when(userService.registerUserAccount(userDto))
                .thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, () -> {
            userService.registerUserAccount(userDto);
        });
    }

    @Test
    public void loadUserDetailsSuccessful() {
        User givenUser = createGivenUser();

        Mockito.when(userService.loadUserDetails(givenUser.getUsername()))
                .thenReturn(givenUser);
        User expectedUser = userService.loadUserDetails(givenUser.getUsername());
         assertEquals(expectedUser, givenUser);
    }

    @Test
    public void loadUserDetailsUserNotExist() {

        User missingUser = createGivenUser();

        Mockito.when(userService.loadUserDetails(missingUser.getUsername()))
                .thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            userService.loadUserDetails(missingUser.getUsername());
        });
    }

    private User createGivenUser(){

        User user = new User();

        user.setId(1L);
        user.setUsername("userDto");
        user.setPassword("1234");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setRole("ROLE_USER");
        user.setEnabled(true);

        return user;
    }
}
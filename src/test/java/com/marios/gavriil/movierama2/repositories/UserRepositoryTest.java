package com.marios.gavriil.movierama2.repositories;

import com.marios.gavriil.movierama2.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void findUserByUsernameSuccessfull() {
        User givenUser = createGivenUser();
        User expectedUser = userRepository.findUserByUsername(givenUser.getUsername());
        assertEquals(expectedUser,givenUser);

    }

    @Test
    public void findUserByUsernameUserDoesNotExist() {
        createGivenUser();
        User expectedUser = userRepository.findUserByUsername("notExistingUser");
        assertNull(expectedUser);

    }

    private User createGivenUser(){
        User givenUser = new User();
        givenUser.setUsername("user");
        givenUser.setPassword("1234");
        givenUser.setRole("ROLE_USER");
        givenUser.setEnabled(true);
        givenUser.setFirstName("firstname");
        givenUser.setLastName("lastname");
        testEntityManager.persistAndFlush(givenUser);
        return givenUser;
    }
}
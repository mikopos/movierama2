package com.marios.gavriil.movierama2.repositories;

import com.marios.gavriil.movierama2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

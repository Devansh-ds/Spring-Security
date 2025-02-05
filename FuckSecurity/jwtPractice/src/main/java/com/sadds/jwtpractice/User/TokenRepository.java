package com.sadds.jwtpractice.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    List<Token> findByUser(User user);
}

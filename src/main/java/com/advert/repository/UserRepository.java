package com.advert.repository;

import com.advert.repository.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Map;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    ArrayList<String> findByIsAdmin(boolean b);

    void deleteByUsername(String username);
}

package com.codecademy.dining.repository;

import com.codecademy.dining.model.DiningUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DiningUserRepository extends CrudRepository<DiningUser, Long> {
    Optional<DiningUser> findByName(String name);
}

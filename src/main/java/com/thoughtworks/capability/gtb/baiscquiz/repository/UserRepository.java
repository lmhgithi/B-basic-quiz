package com.thoughtworks.capability.gtb.baiscquiz.repository;

import com.thoughtworks.capability.gtb.baiscquiz.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
}

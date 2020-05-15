package com.codeup.springblogapp.repositories;

import com.codeup.springblogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(User username);
    User getByUsername(User username);

}

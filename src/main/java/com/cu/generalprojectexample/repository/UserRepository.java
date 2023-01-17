package com.cu.generalprojectexample.repository;

import com.cu.generalprojectexample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value="SELECT u FROM User u WHERE u.userName = :userName")
    User findByUserName(String userName);
}

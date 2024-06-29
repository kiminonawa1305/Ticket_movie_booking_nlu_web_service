package com.lamnguyen.server.repositories;

import com.lamnguyen.server.models.entity.User;
import com.lamnguyen.server.repositories.customs.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserCustomRepository {
    User findByEmail(String apiId);
}

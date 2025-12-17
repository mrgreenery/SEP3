package com.db.sep3.DAO;

import java.util.Optional;
import com.db.sep3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
}


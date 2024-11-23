package com.moniotring.portal.Repository;

import com.moniotring.portal.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

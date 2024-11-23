package com.moniotring.portal.Repository;

import com.moniotring.portal.Entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository< Server, String> {
}

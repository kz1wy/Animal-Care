package com.animalcare.repository;
import com.animalcare.model.LoginLog;
import com.animalcare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Integer> {
    List<LoginLog> findByUser(User user);
}
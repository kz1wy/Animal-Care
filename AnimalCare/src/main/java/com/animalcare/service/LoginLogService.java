package com.animalcare.service;

import com.animalcare.model.LoginLog;
import com.animalcare.model.User;
import org.springframework.stereotype.Service;
import com.animalcare.repository.LoginLogRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginLogService {

    private final LoginLogRepository loginLogRepository;
    public LoginLogService(LoginLogRepository loginLogRepository){
        this.loginLogRepository = loginLogRepository;
    }
    public LoginLog saveLoginLog(LoginLog loginLog) {
        loginLog.setLoginTime(LocalDateTime.now());
        return loginLogRepository.save(loginLog);
    }

    public List<LoginLog> getAllLoginLogs() {
        return loginLogRepository.findAll();
    }

    public List<LoginLog> getLoginLogsByUser(User user) {
        return loginLogRepository.findByUser(user);
    }

    public void deleteLoginLog(int id) {
        loginLogRepository.deleteById(id);
    }
}
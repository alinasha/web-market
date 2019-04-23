package com.vsu.webmarket.data.services;

import com.vsu.webmarket.data.repositories.UserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSettingsService {
    private final UserSettingsRepository repository;

    @Autowired
    public UserSettingsService(UserSettingsRepository repository) {
        this.repository = repository;
    }

    public UserSettingsRepository getRepository() {
        return repository;
    }
}

package com.vsu.webmarket.data.services;

import com.vsu.webmarket.data.repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    private final HistoryRepository repository;

    @Autowired
    public HistoryService(HistoryRepository repository) {
        this.repository = repository;
    }

    public HistoryRepository getRepository() {
        return repository;
    }
}

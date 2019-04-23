package com.vsu.webmarket.data.services;

import com.vsu.webmarket.data.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleRepository repository;

    @Autowired
    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public ArticleRepository getRepository() {
        return repository;
    }
}

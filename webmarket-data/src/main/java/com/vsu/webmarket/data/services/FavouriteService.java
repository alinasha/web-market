package com.vsu.webmarket.data.services;

import com.vsu.webmarket.data.repositories.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteService {
    private final FavouriteRepository repository;

    @Autowired
    public FavouriteService(FavouriteRepository repository) {
        this.repository = repository;
    }

    public FavouriteRepository getRepository() {
        return repository;
    }
}

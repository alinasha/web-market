package com.vsu.webmarket.data.repositories;

import com.vsu.webmarket.model.Favourite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends CrudRepository<Favourite, Long> {
}

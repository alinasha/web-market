package com.vsu.webmarket.data.repositories;

import com.vsu.webmarket.model.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {
}

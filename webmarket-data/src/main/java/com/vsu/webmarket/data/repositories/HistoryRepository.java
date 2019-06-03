package com.vsu.webmarket.data.repositories;

import com.vsu.webmarket.model.History;
import com.vsu.webmarket.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {
    List<History> getAllByUser_Id(long userId);
}

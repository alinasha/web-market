package com.vsu.webmarket.data.repositories;

import com.vsu.webmarket.model.UserSettings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSettingsRepository extends CrudRepository<UserSettings, Long> {
}

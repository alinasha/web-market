package com.vsu.webmarket.model;

import javax.persistence.*;

@Entity
@Table(name = "user_settings")
public class UserSettings {

    @Column(name = "user_id", nullable = false, unique = true)
    private long userId;

    @Column(name = "settings_user")
    private String settingsUser;

    @Column(name = "settings_favorites")
    private String settingsFavorites;

    @Column(name = "settings_articles")
    private String settingsArticles;

    @Column(name = "settings_history")
    private String settingsHistory;

    public UserSettings() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getSettingsUser() {
        return settingsUser;
    }

    public void setSettingsUser(String settingsUser) {
        this.settingsUser = settingsUser;
    }

    public String getSettingsFavorites() {
        return settingsFavorites;
    }

    public void setSettingsFavorites(String settingsFavorites) {
        this.settingsFavorites = settingsFavorites;
    }

    public String getSettingsArticles() {
        return settingsArticles;
    }

    public void setSettingsArticles(String settingsArticles) {
        this.settingsArticles = settingsArticles;
    }

    public String getSettingsHistory() {
        return settingsHistory;
    }

    public void setSettingsHistory(String settingsHistory) {
        this.settingsHistory = settingsHistory;
    }
}

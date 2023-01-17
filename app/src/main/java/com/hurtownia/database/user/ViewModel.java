package com.hurtownia.database.user;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

public class ViewModel extends AndroidViewModel {
    private final Repository uRepository;

    public ViewModel(Application application) {
        super(application);
        uRepository = new Repository(application);
    }
    public void insert(Users up) {
        uRepository.insert(up);
    }
    public Users find(String login) {
        return uRepository.find(login);
    }
    public void changeRole(String login, Roles role) {
        uRepository.changeRole(login, role);
    }
}

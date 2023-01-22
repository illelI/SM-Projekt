package com.hurtownia.database.user;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository uRepository;

    public UserViewModel(Application application) {
        super(application);
        uRepository = new UserRepository(application);
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

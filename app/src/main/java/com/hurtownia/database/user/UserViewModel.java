package com.hurtownia.database.user;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository uRepository;
    private final List<Users> allUsers;
    public UserViewModel(Application application) {
        super(application);
        uRepository = new UserRepository(application);
        allUsers = uRepository.getAllUsers();
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
    public void update(Users user) {
        uRepository.update(user);
    }
    public void delete(Users user) {
        uRepository.delete(user);
    }
    public List<Users> getAllUsers() {
        return allUsers;
    }

}

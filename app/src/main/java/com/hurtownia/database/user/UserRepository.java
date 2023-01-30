package com.hurtownia.database.user;

import android.app.Application;
import android.util.Log;

import com.hurtownia.database.DB;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class UserRepository {
    private final UsersDao dao;
    private List<Users> allUsers;
    UserRepository(Application application) {
        DB db = DB.getDatabase(application);
        dao = db.upDao();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            synchronized (this) {
                allUsers = dao.getAllUsers();
                latch.countDown();
            }
        }).start();
        try {
            latch.await();
        } catch (Exception e) {
            Log.d("", "");
        }
    }
    void insert(Users up) {
        DB.dbWriteExecutor.execute(() -> dao.insert(up));
    }
    Users find(String login) {
       CountDownLatch latch = new CountDownLatch(1);
       final Users[] up = new Users[1];
        new Thread(() -> {
            synchronized (this) {
                up[0] = dao.find(login);
                latch.countDown();
            }
        }).start();
        try {
            latch.await();
        } catch (Exception e) {
            Log.d("select", "error");
        }
        return up[0];
    }
    void changeRole(String login, Roles role) {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            synchronized (this) {
                dao.changeRole(login, role);
                latch.countDown();
            }
        }).start();
        try {
            latch.await();
        } catch (Exception e) {
            Log.d("ROLE","error");
        }
        Log.d("ROLE", "changed");
    }
    void update(Users user) {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            synchronized (this) {
                dao.update(user);
                latch.countDown();
            }
        }).start();
        try {
            latch.await();
        } catch (Exception e) {
            Log.d("UPDATE","error");
        }
        Log.d("UPDATE", "updated");
    }
    void delete(Users user) {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            synchronized (this) {
                dao.delete(user);
                latch.countDown();
            }
        }).start();
        try {
            latch.await();
        } catch (Exception e) {
            Log.d("DELETE","error");
        }
        Log.d("DELETE", "deleted");
    }
    List<Users> getAllUsers() {
        return allUsers;
    }
}

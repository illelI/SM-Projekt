package com.hurtownia.database.user;

import android.app.Application;
import android.util.Log;

import com.hurtownia.database.DB;

import java.util.concurrent.CountDownLatch;

public class Repository {
    private final UserPasswordDao dao;

    Repository(Application application) {
        DB db = DB.getDatabase(application);
        dao = db.upDao();
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
            Log.d("role","error");
        }
        Log.d("role", "changed");
    }
}

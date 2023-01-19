package com.hurtownia.database.user;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Users")
public class Users implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "login")
    private final String login;
    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "role")
    private Roles role;

    public Users(@NonNull String login, @NonNull String password, @NonNull Roles role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @NonNull
    public String getLogin() {
        return login;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @NonNull
    public Roles getRole() {
        return role;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public void setRole(@NonNull Roles role) {
        this.role = role;
    }
}

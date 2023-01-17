package com.hurtownia.database.user;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserPasswordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Users up);
    @Delete
    void delete(Users up);
    @Query("SELECT * FROM Users WHERE login=(:login)")
    Users find(String login);
    @Query("UPDATE Users SET role = (:role) WHERE login = (:login)")
    void changeRole(String login,Roles role);
}

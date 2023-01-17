package com.hurtownia.database;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.hurtownia.database.user.Roles;
import com.hurtownia.database.user.UserPasswordDao;
import com.hurtownia.database.user.Users;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Users.class}, version = 2)
public abstract class DB extends RoomDatabase {
    public abstract UserPasswordDao upDao();

    private static volatile DB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase database) {
            super.onCreate(database);
            dbWriteExecutor.execute(() -> {
                UserPasswordDao dao = INSTANCE.upDao();
                Users user = new Users("admin", "admin", Roles.Admin);
                dao.insert(user);
            });
        }
    };

    public static DB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DB.class, "database").addCallback(roomDatabaseCallback).addMigrations(MIGRATION_1_2).build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE passwords RENAME TO Users");
            database.execSQL("ALTER TABLE Users "
                    + " ADD COLUMN role TEXT");
        }
    };

}

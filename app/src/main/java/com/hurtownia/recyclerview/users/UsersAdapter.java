package com.hurtownia.recyclerview.users;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.hurtownia.R;
import com.hurtownia.activities.UserInfoActivity;
import com.hurtownia.database.user.Users;

import java.io.Serializable;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    private List<Users> usersList;
    private Context context;
    public UsersAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = usersList;
    }
    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UsersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_view , parent, false);
        return new UsersAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.MyViewHolder holder, int position) {
        holder.userLogin.setText(usersList.get(position).getLogin());
        holder.user = usersList.get(position);
        holder.context = context;
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements Serializable {
        TextView userLogin;
        RelativeLayout containter;
        Users user;
        Context context;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userLogin = itemView.findViewById(R.id.tv_userLogin);
            containter = itemView.findViewById(R.id.rl_user_container);
            containter.setOnClickListener(view -> {
                Intent intent = new Intent(context, UserInfoActivity.class);
                intent.putExtra("USER", user);
                context.startActivity(intent);
            });
        }
    }
}

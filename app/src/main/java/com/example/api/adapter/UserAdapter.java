package com.example.api.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.EditUserActivity;
import com.example.api.models.User;
import com.example.api.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder> {

    List<User> userList = new ArrayList<>();
    Context context;

    public UserAdapter(Context context) {
        this.context = context;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserAdapter.UserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserAdapterViewHolder holder, int position) {
        holder.bind(userList.get(position));
        if (userList.get(position).getStatus().equals("active")) {
            holder.status.setColorFilter(ContextCompat.getColor(context,R.color.teal_200));
        }
//        holder.itemView.setOnClickListener(view -> {
//            Intent intent = new Intent(context, EditUserActivity.class);
//            intent.putExtra("user_id", userList.get(position).getId());
//            intent.putExtra("user_name", userList.get(position).getName());
//            intent.putExtra("user_email", userList.get(position).getEmail());
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView name, email, gender;
        ImageView status;
        String val_status;

        public UserAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            gender = itemView.findViewById(R.id.gender);
            status = itemView.findViewById(R.id.status);
        }

        public void bind(User user) {
            name.setText(user.getName());
            email.setText(user.getEmail());
            gender.setText(user.getGender());
            val_status = user.getStatus();
        }

    }
}

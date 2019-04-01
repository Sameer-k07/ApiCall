package com.example.api.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.api.R;
import com.example.api.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User> mUserList;

    /**
     *
     * @param mUserList - list of users
     */
    public UserAdapter(List<User> mUserList) {
        this.mUserList = mUserList;
    }

    /**
     *
     * @param viewGroup - contains collection of views
     * @param i - viewType
     * @return particular view of recycler view
     */
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup,
                false);

        return new MyViewHolder(v);
    }

    /**
     *
     * @param myViewHolder - to hold the view
     * @param i - position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //binds the data to the view and also uses old views
        User users = mUserList.get(myViewHolder.getAdapterPosition());
        MyViewHolder viewHolder =  myViewHolder;
        viewHolder.tvName.setText(users.getName());
        viewHolder.tvId.setText(String.valueOf(users.getId()));

    }

    /**
     *
     * @return - size of arraylist to be inflated
     */
    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName,tvId;
        //holds the particular view of recycler view
        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvId= view.findViewById(R.id.tv_id);
        }
    }

}


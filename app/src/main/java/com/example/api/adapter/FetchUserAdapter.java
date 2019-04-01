package com.example.api.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.api.R;
import com.example.api.model.FetchUser;

import java.util.List;

public class FetchUserAdapter extends RecyclerView.Adapter<FetchUserAdapter.MyViewHolder> {

    private List<FetchUser> mFetchUserList;

    /**
     *
     * @param mFetchUserList - list of users
     */
    public FetchUserAdapter(List<FetchUser> mFetchUserList) {
        this.mFetchUserList = mFetchUserList;
    }

    /**
     *
     * @param viewGroup - contains collection of views
     * @param i - viewType
     * @return particular view of recycler view
     */
    public FetchUserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fetch_item_layout, viewGroup,
                false);

        return new FetchUserAdapter.MyViewHolder(v);
    }
    /**
     *
     * @param myViewHolder - to hold the view
     * @param i - position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {//binds the data to the view and also uses old views
        FetchUser users = mFetchUserList.get(myViewHolder.getAdapterPosition());
        FetchUserAdapter.MyViewHolder viewHolder =  myViewHolder;
        viewHolder.tvName.setText(users.getName());
        viewHolder.tvId.setText(String.valueOf(users.getId()));
        viewHolder.tvTitle.setText(users.getTitle());
        viewHolder.tvBody.setText(users.getBody());
    }


    /**
     *
     * @return - size of arraylist to be inflated
     */
    @Override
    public int getItemCount() {
        return mFetchUserList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUser,tvName,tvId,tvTitle,tvBody;
        //holds the particular view of recycler view
        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvId= view.findViewById(R.id.tv_id);
            tvTitle= view.findViewById(R.id.tv_title);
            tvBody= view.findViewById(R.id.tv_body);

        }
    }
}

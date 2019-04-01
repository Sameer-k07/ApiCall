package com.example.api.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.api.util.Constant;
import com.example.api.R;
import com.example.api.api.ApiClient;
import com.example.api.api.RetrofitAPI;
import com.example.api.listener.TouchListener;
import com.example.api.model.User;
import com.example.api.adapter.UserAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<User> mUserList = new ArrayList<User>();
    private UserAdapter mAdapter;
    private Context mContext;
    private TextView tvName,tvId;
    private ImageView ivUser;
    private Button fetchButton;
    private int id;
    private String name;
    private RetrofitAPI apiInterface;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //to initialise views
        init();

        if(savedInstanceState != null){
            tvName.setText(savedInstanceState.getString(Constant.USERNAME));
            tvId.setText(savedInstanceState.getString(Constant.USERID));
            ivUser.setBackgroundResource(R.drawable.user);
        }

        //to hit api call
        apiCall();

        //to perform operations on clicking recyclerview
        recyclerViewOperation();

        //fetching posts into FetchPostActivity
        fetchButtonListener();

    }

    //to send data for check at savedInstance
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constant.USERNAME,tvName.getText().toString());
        outState.putString(Constant.USERID,tvId.getText().toString());
    }

    //method to initialise views
    private void init(){
        mRecyclerView = findViewById(R.id.recycler_view);
        fetchButton = findViewById(R.id.btn_fetch);
        tvName=findViewById(R.id.tv_username);
        tvId=findViewById(R.id.tv_userid);
        ivUser=findViewById(R.id.iv_user);

        mAdapter= new UserAdapter(mUserList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(UserActivity.this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(UserActivity.this, LinearLayoutManager.VERTICAL));

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        apiInterface = ApiClient.getClient().create(RetrofitAPI.class);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

    }
    //method to hit api call
    private void apiCall(){

        Call<List<User>> call = apiInterface.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();

                for(int i=0;i<userList.size();i++) {
                    mUserList.add(new User(userList.get(i).getName(),userList.get(i).getId()));
                }
                mAdapter.notifyDataSetChanged();
                // stop animating Shimmer and hide the layout
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
    //method to perform operations on clicking recyclerview i.e to show user details
    private void recyclerViewOperation(){
        mRecyclerView.addOnItemTouchListener(new TouchListener(mContext,mRecyclerView, new TouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                name= mUserList.get(position).getName();
                id = mUserList.get(position).getId();
                tvName = findViewById(R.id.tv_username);
                tvId = findViewById(R.id.tv_userid);
                ivUser = findViewById(R.id.iv_user);
                tvName.setText(name);
                tvId.setText(String.valueOf(id));
                ivUser.setBackgroundResource(R.drawable.user);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
    //to shift to FetchPostActivity to fetch post of selected user
    private void fetchButtonListener(){
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserActivity.this,FetchPostActivity.class);
                intent.putExtra(Constant.USERNAME,name);
                intent.putExtra(Constant.USERID,tvId.getText().toString());
                startActivity(intent);
            }
        });
    }
    //to start shimmer
    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }
    //to stop shimmer
    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }
}

package com.example.api.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.api.util.Constant;
import com.example.api.R;
import com.example.api.adapter.FetchUserAdapter;
import com.example.api.api.ApiClient;
import com.example.api.api.RetrofitAPI;
import com.example.api.model.FetchUser;
import com.facebook.shimmer.ShimmerFrameLayout;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchPostActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FetchUserAdapter mAdapter;
    private List<FetchUser> mFetchUserList = new ArrayList<FetchUser>();
    private int id;
    private String name;
    private TextView tvUser;
    private RetrofitAPI apiInterface;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_post);
        //to do all initialisations
        init();

        //to fetch post of selected user
        fetchPost();
    }
    //method to do all initialisations
    private void init(){
        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra(Constant.USERID));
        name = intent.getStringExtra(Constant.USERNAME);
        mRecyclerView = findViewById(R.id.recycler_view);
        tvUser = findViewById(R.id.tv_user_name);
        tvUser.setText(name);
        mAdapter = new FetchUserAdapter(mFetchUserList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(FetchPostActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        apiInterface= ApiClient.getClient().create(RetrofitAPI.class);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
    }
    //method to fetch post of selected user
    private void fetchPost () {
        Call<List<FetchUser>> call = apiInterface.getUsersPost(id);
        call.enqueue(new Callback<List<FetchUser>>() {
            @Override
            public void onResponse(Call<List<FetchUser>> call, Response<List<FetchUser>> response) {
                List<FetchUser> fetchUserList = response.body();
                for (int i = 0; i < fetchUserList.size(); i++) {
                    mFetchUserList.add(new FetchUser(fetchUserList.get(i).getName(), fetchUserList.get(i).getId(), fetchUserList.get(i).getTitle(), fetchUserList.get(i).getBody()));
                }
                // stop animating Shimmer and hide the layout
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FetchUser>> call, Throwable t) {

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

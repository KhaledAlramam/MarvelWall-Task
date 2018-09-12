package com.android.khaled.marvelwallinternship.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.khaled.marvelwallinternship.R;
import com.android.khaled.marvelwallinternship.service.model.Post;
import com.android.khaled.marvelwallinternship.viewmodel.PostsViewModel;
import com.roger.catloadinglibrary.CatLoadingView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostsAdapter adapter;
    private PostsViewModel mPostsViewModel;
    private RecyclerView.LayoutManager layoutManager;
    private CatLoadingView mView;
    private SwipeRefreshLayout swipeContainer;
    private final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecyclerView();
        mView = new CatLoadingView();
        mView.show(getSupportFragmentManager(), TAG);
        swipeContainer = findViewById(R.id.swipeContainer);

        //Apply pull to refresh
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isNetworkAvailable()) {
                    Toast.makeText(MainActivity.this, getString(R.string.noConnection)
                            , Toast.LENGTH_SHORT)
                            .show();
                    swipeContainer.setRefreshing(false);
                    return;
                }
                mView.show(getSupportFragmentManager(), TAG);
                fetchData();
                swipeContainer.setRefreshing(false);
            }
        });

        //Check internet connection before start fetching data
        if (isNetworkAvailable()) {
            fetchData();
        } else {
            mView.dismiss();
            Toast.makeText(this, getString(R.string.noConnection)
                    , Toast.LENGTH_SHORT)
                    .show();
        }
    }

    //Helper method to start fetching data
    private void fetchData() {
        mPostsViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);
        mPostsViewModel.getPostsObservable().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {
                mView.dismiss();
                adapter.setmPosts(posts);
            }
        });
    }

    //Helper method to setup the recyclerView, adapter then attach them
    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.data_recycler_view);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PostsAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    //Helper method to check internet connection
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

package com.android.khaled.marvelwallinternship.service.repository;

import com.android.khaled.marvelwallinternship.service.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/posts")
    Call<List<Post>> getAllPosts();
}

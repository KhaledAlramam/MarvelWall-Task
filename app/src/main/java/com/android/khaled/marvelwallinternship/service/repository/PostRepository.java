package com.android.khaled.marvelwallinternship.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.android.khaled.marvelwallinternship.service.model.Post;
import com.android.khaled.marvelwallinternship.service.model.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private GetDataService getDataService;

    public LiveData<List<Post>> getAllPosts() {
        final MutableLiveData<List<Post>> data = new MutableLiveData<>();

        getDataService = RetrofitClientInstance.getRetrofitInstance()
                .create(GetDataService.class);
        Call<List<Post>> call = getDataService.getAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}

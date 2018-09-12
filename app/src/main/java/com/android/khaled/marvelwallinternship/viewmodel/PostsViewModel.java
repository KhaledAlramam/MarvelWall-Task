package com.android.khaled.marvelwallinternship.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.android.khaled.marvelwallinternship.service.model.Post;
import com.android.khaled.marvelwallinternship.service.repository.PostRepository;

import java.util.List;

public class PostsViewModel extends ViewModel {

    private PostRepository mPostRepository;
    private final LiveData<List<Post>> postsObservable;

    public PostsViewModel() {
        super();
        mPostRepository = new PostRepository();
        postsObservable = mPostRepository.getAllPosts();
    }

    public LiveData<List<Post>> getPostsObservable() {
        return postsObservable;
    }
}

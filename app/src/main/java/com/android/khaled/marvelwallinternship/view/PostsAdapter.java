package com.android.khaled.marvelwallinternship.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.khaled.marvelwallinternship.R;
import com.android.khaled.marvelwallinternship.service.model.Post;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    class PostsViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        final TextView titleTextView;
        final TextView bodyTextView;

        PostsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            titleTextView = mView.findViewById(R.id.post_title);
            bodyTextView = mView.findViewById(R.id.post_body);
        }
    }

    private final LayoutInflater mInflater;
    private List<Post> mPosts;

    public PostsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        if (mPosts != null) {
            Post currentPost = mPosts.get(position);
            holder.titleTextView.setText(currentPost.getTitle());
            holder.bodyTextView.setText(currentPost.getBody());
        }
    }

    public void setmPosts(List<Post> posts) {
        mPosts = posts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mPosts != null) {
            return mPosts.size();
        } else {
            return 0;
        }
    }

}

package com.parveendala.dagger.ui.main.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parveendala.dagger.R;
import com.parveendala.dagger.models.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.PostViewHolder> {
    private static final String TAG = "PostRecyclerAdapter";
    List<Post> postList = new ArrayList<>();

    public void setPosts(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post current = postList.get(position);
        holder.tvTitle.setText(current.getTitle());
        holder.tvBody.setText(current.getBody());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvBody;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvBody = itemView.findViewById(R.id.body);
        }
    }

}

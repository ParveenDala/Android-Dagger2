package com.parveendala.dagger.ui.main.post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parveendala.dagger.R;
import com.parveendala.dagger.models.Post;
import com.parveendala.dagger.ui.main.Resource;
import com.parveendala.dagger.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public class PostFragment extends DaggerFragment {

    private static final String TAG = "PostFragment";

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    PostViewModel postViewModel;


    @Inject
    PostRecyclerAdapter recyclerAdapter;

    private RecyclerView recyclerView;
    private TextView tvError;
    private ProgressBar progressBar;

    @Inject
    PostFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        recyclerView = view.findViewById(R.id.recycler_view);
        tvError = view.findViewById(R.id.error);
        progressBar = view.findViewById(R.id.progress_bar);
        setRecyclerView();
        observeUserPosts();
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void observeUserPosts() {
        postViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(PostViewModel.class);
        postViewModel.observeUserPosts().removeObservers(getViewLifecycleOwner());
        postViewModel.observeUserPosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> postPostResource) {
                if (postPostResource != null) {
                    switch (postPostResource.status) {
                        case LOADING:
                            showLoading(true);
                            break;
                        case SUCCESS:
                            showLoading(false);
                            showPosts(postPostResource.data);
                            break;
                        case ERROR:
                            showLoading(false);
                            showErrorDetails(postPostResource.message);
                            break;
                    }
                }
            }
        });
        postViewModel.getPosts();
    }

    private void showPosts(List<Post> posts) {
        recyclerAdapter.setPosts(posts);
    }


    private void showLoading(boolean isShowing) {
        if (isShowing) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            tvError.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.GONE);
        }
    }

    private void showErrorDetails(String message) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(message);
    }
}

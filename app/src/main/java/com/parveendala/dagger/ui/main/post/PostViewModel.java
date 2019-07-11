package com.parveendala.dagger.ui.main.post;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.parveendala.dagger.SessionManager;
import com.parveendala.dagger.models.Post;
import com.parveendala.dagger.network.main.MainApi;
import com.parveendala.dagger.ui.main.Resource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {

    private static final String TAG = "PostViewModel";

    private MediatorLiveData<Resource<List<Post>>> postData = new MediatorLiveData<>();

    private SessionManager sessionManager;
    private final MainApi postApi;


    @Inject
    public PostViewModel(final SessionManager sessionManager, final MainApi postApi) {
        this.sessionManager = sessionManager;
        this.postApi = postApi;
    }

    public void getPosts() {
        postData.setValue(Resource.loading((List<Post>) null));
        postApi.getPosts(getUserId()).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                if (posts != null) {
                    postData.setValue(Resource.success(posts));
                } else {
                    postData.setValue(Resource.error("Unable to fetch user posts", (List<Post>) null));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postData.setValue(Resource.error("Unable to fetch user posts - " + t.getLocalizedMessage(), (List<Post>) null));
            }
        });
    }

    public void getPostsRx() {
        postData.setValue(Resource.loading((List<Post>) null));
        //.....................
    }

    public LiveData<Resource<List<Post>>> observeUserPosts() {
        return postData;
    }

    private int getUserId() {
        return sessionManager.getLoginUser().getValue().data.getId();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
    }
}

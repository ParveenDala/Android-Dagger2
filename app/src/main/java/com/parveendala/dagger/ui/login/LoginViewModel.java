package com.parveendala.dagger.ui.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.parveendala.dagger.SessionManager;
import com.parveendala.dagger.models.User;
import com.parveendala.dagger.network.login.LoginApi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public class LoginViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";

    private final LoginApi loginApi;
    private final SessionManager sessionManager;

    @Inject
    public LoginViewModel(LoginApi loginApi, SessionManager sessionManager) {
        this.loginApi = loginApi;
        this.sessionManager = sessionManager;
    }

    public void authenticateUser(int userId) {
        final MediatorLiveData<LoginResource<User>> source = new MediatorLiveData<>();
        sessionManager.authenticateUserById(source);
        loginApi.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null) {
                    Log.d(TAG, "onResponse: " + user.getEmail());
                    Log.d(TAG, "onResponse: UPDATE DATA");
                    source.setValue(LoginResource.authenticated(user));
                } else {
                    Log.d(TAG, "onResponse: NULL");
                    source.setValue(LoginResource.error("Unable to login", (User) null));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                source.setValue(LoginResource.error(t.getLocalizedMessage(), (User) null));
            }
        });
    }

    public void authenticateUserUsingRxJava(int userId) {
        Log.d(TAG, "authenticateUserUsingRxJava: 1");
        sessionManager.authenticateUserById(LiveDataReactiveStreams.fromPublisher(
                loginApi.getUserRx(userId)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, LoginResource<User>>() {
                            @Override
                            public LoginResource<User> apply(User user) throws Exception {
                                if (user.getId() == -1)
                                    return LoginResource.error("Unable to login", null);
                                return LoginResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io())
        ));
        Log.d(TAG, "authenticateUserUsingRxJava: 2");
    }

    public LiveData<LoginResource<User>> observeLoginState() {
        return sessionManager.getLoginUser();
    }

    @Override
    protected void onCleared() {
        Log.d(TAG, "onCleared: ");
        super.onCleared();
    }
}
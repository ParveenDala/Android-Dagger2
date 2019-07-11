package com.parveendala.dagger.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public class LoginResource<T> {
    public enum LoginStatus {AUTHENTICATED, LOADING, ERROR, NOT_AUTHENTICATED}

    @NonNull
    public final LoginStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    public LoginResource(@NonNull LoginStatus status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> LoginResource<T> authenticated(T data) {
        return new LoginResource<>(LoginStatus.AUTHENTICATED, data, null);
    }

    public static <T> LoginResource<T> loading(T data) {
        return new LoginResource<>(LoginStatus.LOADING, data, null);
    }

    public static <T> LoginResource<T> error(String message, T data) {
        return new LoginResource<>(LoginStatus.ERROR, data, message);
    }

    public static <T> LoginResource<T> logout() {
        return new LoginResource<>(LoginStatus.NOT_AUTHENTICATED, null, null);
    }

}

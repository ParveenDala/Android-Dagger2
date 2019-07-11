package com.parveendala.dagger.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.lifecycle.ViewModelProviders;

import com.parveendala.dagger.BaseActivity;
import com.parveendala.dagger.R;
import com.parveendala.dagger.ui.main.post.PostFragment;
import com.parveendala.dagger.ui.main.profile.ProfileFragment;
import com.parveendala.dagger.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    MainViewModel mainViewModel;

    @Inject
    ProfileFragment profileFragment;

    @Inject
    PostFragment postFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(MainViewModel.class);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, postFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            sessionManager.logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements ProfileFragment.OnEditButtonListener {

    private ProfileFragment profileFragment;
    private PostsFragment postsFragment;
    private BottomNavigationView navigator;

    private EditProfileFragment editProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileFragment = ProfileFragment.newInstance();
        profileFragment.setListener(this);
        postsFragment = PostsFragment.newInstance();
        editProfileFragment = EditProfileFragment.newInstance();

        navigator = findViewById(R.id.navigator);

        showFragment(profileFragment);

        navigator.setOnItemSelectedListener(menuItem->{

            if(menuItem.getItemId()==R.id.profileItem){
                showFragment(profileFragment);
            }else if(menuItem.getItemId()==R.id.postsItem){
                showFragment(postsFragment);
                }
            return true;
        });

    }

    public void showFragment(Fragment f){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer,f);
        transaction.commit();
    }

    @Override
    public void swapFragment(Fragment f) {
        showFragment(f);
    }
}
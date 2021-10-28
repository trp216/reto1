package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProfileFragment.OnEditButtonListener, PostsFragment.OnAddPostListener {

    private ProfileFragment profileFragment;
    private PostsFragment postsFragment;
    private MapsFragment mapsFragment;
    private BottomNavigationView navigator;

    private NewPublicationFragment newPublicationFragment;

    private EditProfileFragment editProfileFragment;

    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions(new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        },1);

        profileFragment = ProfileFragment.newInstance();
        profileFragment.setListener(this);
        profile = new Profile();
        postsFragment = PostsFragment.newInstance();

        newPublicationFragment = NewPublicationFragment.newInstance();
        postsFragment.setListener(this);

        editProfileFragment = EditProfileFragment.newInstance();

        mapsFragment = new MapsFragment();

        navigator = findViewById(R.id.navigator);

        showFragment(profileFragment);

        navigator.setOnItemSelectedListener(menuItem->{

            if(menuItem.getItemId()==R.id.profileItem){
                swapFragment(profileFragment,0);
            }else if(menuItem.getItemId()==R.id.postsItem){
                swapFragment(postsFragment,0);
            }else if(menuItem.getItemId()==R.id.mapItem){
                swapFragment(mapsFragment,0);
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
    public void addFragment(Fragment f){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentContainer,f);
        transaction.commit();
    }

    @Override
    public void swapFragment(Fragment f, int opt) {
        if(opt == 0){
            showFragment(f);
        }
        else{
            addFragment(f);
        }
    }

    @Override
    public void onEditProfile(Profile profile) {
        this.profile = profile;
        editProfileFragment.setProfile(profile);
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    public void onAdd(Post newPost) {
        newPost.setBusiness(profile.getName());
        newPost.setUri(profile.getUri());
        postsFragment.addPost(newPost);
        swapFragment(postsFragment,0);
    }
//    @Override
//    public void onResume() {
//        SharedPreferences sharedPreferences = getSharedPreferences("MainActivity",MODE_PRIVATE);
//
//        String json = sharedPreferences.getString("profile","NO_OBJ");
//        if(!json.equals("NO_OBJ")){
//            Gson gson = new Gson();
//            Profile p = gson.fromJson(json,Profile.class);
//            editProfileFragment.setProfile(p);
//        }
//
//        String jsonPosts = sharedPreferences.getString("posts","NO_OBJ");
//        if(!jsonPosts.equals("NO_OBJ")){
//            Gson gson = new Gson();
//            Type type = new TypeToken<ArrayList<Post>>(){}.getType();
//            ArrayList<Post> posts = gson.fromJson(jsonPosts,type);
//            postsFragment.getAdapter().setPosts(posts);
//        }
//
//        super.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        Profile p = profileFragment.getProfile();
//        ArrayList<Post> posts = postsFragment.getPosts();
//        Gson gson = new Gson();
//        String json = gson.toJson(p);
//        String jsonPosts = gson.toJson(posts);
//        //Local storage
//        SharedPreferences sharedPreferences = getSharedPreferences("MainActivity",MODE_PRIVATE);
//        sharedPreferences.edit()
//                .putString("profile",json)
//                .putString("posts",jsonPosts)
//                .apply();
//        super.onPause();
//    }
}
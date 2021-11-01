package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProfileFragment.OnEditButtonListener, PostsFragment.OnAddPostListener, GeneralMapFragment.OnGeneralMapListener {

    private ProfileFragment profileFragment;
    private PostsFragment postsFragment;
    //private MapsFragment mapsFragment;
    private GeneralMapFragment generalMapFragment;

    private BottomNavigationView navigator;

    private NewPublicationFragment newPublicationFragment;

    private EditProfileFragment editProfileFragment;

    private Profile profile;
    private ArrayList<Post> posts;

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

        postsFragment = PostsFragment.newInstance();

        newPublicationFragment = NewPublicationFragment.newInstance();
        postsFragment.setListener(this);

        editProfileFragment = EditProfileFragment.newInstance();

        generalMapFragment = new GeneralMapFragment();
        generalMapFragment.setListener(this);

        navigator = findViewById(R.id.navigator);

        loadProfile();
        loadPosts();



        showFragment(profileFragment);

        navigator.setOnItemSelectedListener(menuItem->{

            if(menuItem.getItemId()==R.id.profileItem){
                swapFragment(profileFragment,0);
            }else if(menuItem.getItemId()==R.id.postsItem){
                swapFragment(postsFragment,0);
            }else if(menuItem.getItemId()==R.id.mapItem){
                swapFragment(generalMapFragment,0);
            }
            return true;
        });

    }

    private void loadProfile() {
        SharedPreferences sharedPreferences = getSharedPreferences("MainActivity",MODE_PRIVATE);
        String json = sharedPreferences.getString("profile","NO_OBJ");
        if(!json.equals("NO_OBJ")){
            Gson gson = new Gson();
            Profile p = gson.fromJson(json,Profile.class);
            profile = p;
            profileFragment.setProfile(p);
        }
    }

    private void loadPosts(){
        SharedPreferences sharedPreferences = getSharedPreferences("MainActivity",MODE_PRIVATE);
        String json = sharedPreferences.getString("posts","NO_OBJ");
        if(!json.equals("NO_OBJ")){
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Post>>(){}.getType();
            ArrayList<Post> ps = gson.fromJson(json,type);
            posts = ps;
            postsFragment.setPosts(ps);

            for(int i = 0;i<posts.size();i++){
                Log.e(">>>>>>>",posts.get(i).getLocation());
            }
            generalMapFragment.setPosts(posts);
        }
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
        savePosts();
        swapFragment(postsFragment,0);
    }
    private void savePosts() {
        posts = postsFragment.getPosts();
        generalMapFragment.setPosts(posts);
        Gson gson = new Gson();
        String json = gson.toJson(posts);
        //Local storage
        SharedPreferences sharedPreferences = getSharedPreferences("MainActivity", MODE_PRIVATE);
        sharedPreferences.edit()
                .putString("posts", json)
                .apply();
    }

    @Override
    public void onGeneralMap() {
        swapFragment(generalMapFragment,0);
    }
}
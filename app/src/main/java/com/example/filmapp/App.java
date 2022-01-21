package com.example.filmapp;

import android.app.Application;

import com.example.filmapp.data.remote.FilmApi;
import com.example.filmapp.data.remote.RetrofitClient;

public class App extends Application {

    public static FilmApi api;
    public  RetrofitClient rClient;

    @Override
    public void onCreate() {
        super.onCreate();
        rClient = new RetrofitClient();
        api = rClient.provideFilmApi();
    }
}

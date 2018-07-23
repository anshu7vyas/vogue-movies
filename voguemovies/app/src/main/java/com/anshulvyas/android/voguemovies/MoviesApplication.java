package com.anshulvyas.android.voguemovies;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MoviesApplication extends Application{

    public static String BASE_URL_POSTER_IMAGE = "https://image.tmdb.org/t/p/w185";
    public static String BASE_URL_BACKDROP_IMAGE = "https://image.tmdb.org/t/p/w300";

    @Override
    public void onCreate() {
        super.onCreate();


        Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this));

        Stetho.Initializer initializer = initializerBuilder.build();

        Stetho.initialize(initializer);
    }
}

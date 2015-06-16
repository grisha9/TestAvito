package ru.rzn.myasoedov.testavito;

import android.app.Application;
import android.content.Context;

import retrofit.RestAdapter;
import ru.rzn.myasoedov.testavito.service.GitHubRestClient;

/**
 * Created by grisha on 16.06.15.
 */
public class AvitoTestApplication extends Application {

    private static Context context;
    private static GitHubRestClient service;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BuildConfig.GIT_HUB_API)
                .build();

        service = restAdapter.create(GitHubRestClient.class);
    }

    public static GitHubRestClient getService() {
        return service;
    }
}

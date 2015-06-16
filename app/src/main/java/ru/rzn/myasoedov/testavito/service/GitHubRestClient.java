package ru.rzn.myasoedov.testavito.service;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import ru.rzn.myasoedov.testavito.BuildConfig;
import ru.rzn.myasoedov.testavito.dto.User;

/**
 * Created by grisha on 16.06.15.
 */
public interface GitHubRestClient {
    @GET("/users?per_page=" + BuildConfig.ITEM_OM_PAGE)
    List<User> getUsers(@Query("since") long since);
}

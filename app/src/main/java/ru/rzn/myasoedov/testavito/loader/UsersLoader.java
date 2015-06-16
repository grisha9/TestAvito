package ru.rzn.myasoedov.testavito.loader;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import java.util.Collections;
import java.util.List;

import ru.rzn.myasoedov.testavito.AvitoTestApplication;
import ru.rzn.myasoedov.testavito.dto.ResponseWrapper;
import ru.rzn.myasoedov.testavito.dto.User;

/**
 * Created by grisha on 16.06.15.
 */
public class UsersLoader extends AsyncTaskLoader<ResponseWrapper<List<User>>> {
    public static final String NAME = "name";
    public static final String SINCE = "since";

    private String name;
    private long since;

    public UsersLoader(Context context, Bundle bundle) {
        super(context);
        if (bundle != null) {
            //name = bundle.getString(NAME);
            since = bundle.getLong(SINCE, 0);
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ResponseWrapper<List<User>> loadInBackground() {
        ResponseWrapper<List<User>> response = new ResponseWrapper<>();
        try {
            List<User> users = AvitoTestApplication.getService().getUsers(since);
            response.setResponse((users != null) ? users : Collections.EMPTY_LIST);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }
}

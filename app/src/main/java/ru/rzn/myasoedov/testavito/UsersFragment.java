package ru.rzn.myasoedov.testavito;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.rzn.myasoedov.testavito.adapter.EndlessScrollListener;
import ru.rzn.myasoedov.testavito.adapter.UsersAdapter;
import ru.rzn.myasoedov.testavito.dto.ResponseWrapper;
import ru.rzn.myasoedov.testavito.dto.User;
import ru.rzn.myasoedov.testavito.loader.UsersLoader;

/**
 * Created by grisha on 16.06.15.
 */
public class UsersFragment extends ListFragment implements LoaderManager.LoaderCallbacks<ResponseWrapper<List<User>>>{

    public static final int USERS_LOADER_ID = 1;
    private boolean addUsers;
    private List<User> users;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        addUsers = true;
        users = new ArrayList<>();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setEmptyText(getResources().getString(R.string.no_users));
        getListView().setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                restartLoader();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(USERS_LOADER_ID, null, this);
    }



    private void restartLoader() {
        addUsers = true;
        Bundle bundle = new Bundle();
        bundle.putLong(UsersLoader.SINCE, ((UsersAdapter) getListAdapter()).getLastUser().getId());
        getLoaderManager().restartLoader(USERS_LOADER_ID, bundle, this);
    }



    @Override
    public Loader<ResponseWrapper<List<User>>> onCreateLoader(int id, Bundle bundle) {
        Loader<ResponseWrapper<List<User>>> loader = null;
        if (id == USERS_LOADER_ID) {
            loader = new UsersLoader(this.getActivity(), bundle);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<ResponseWrapper<List<User>>> loader,
                               ResponseWrapper<List<User>> data) {
        if (data.getException() != null) {
            setListAdapter(null);
            Toast.makeText(this.getActivity(), data.getException().getMessage(), Toast.LENGTH_LONG)
                    .show();
        } else {
            if (getListAdapter() == null) {
                setListAdapter(new UsersAdapter(getActivity(), users));
            }
            if (addUsers) {
                ((UsersAdapter) getListAdapter()).addUsers(data.getResponse());
            }
            users = ((UsersAdapter) getListAdapter()).getUsers();
            addUsers = false;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
    }
}

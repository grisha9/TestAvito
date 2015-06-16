package ru.rzn.myasoedov.testavito;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class UsersActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new UsersFragment())
                    .commit();
        }
    }
}

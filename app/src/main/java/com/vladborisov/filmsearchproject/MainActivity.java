package com.vladborisov.filmsearchproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;


public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    final static String TAG = MainActivity.class.getSimpleName();
    private static final String INVITE_MASSAGE = "Заходи на огонек!";
    private static final String INVITE_TITLE = "Пригласить друга";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_draw_open, R.string.nav_draw_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setOnMenuItemClickListener(this);
        MainListFilmsFragment mainListFilmsFragment = new MainListFilmsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_fragment_container, mainListFilmsFragment, MainListFilmsFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    private void inviteFriend() {
        Log.d(TAG, "Метод поделиться");
        Intent sendInvite = new Intent();
        sendInvite.setAction(Intent.ACTION_SEND);
        sendInvite.putExtra(Intent.EXTRA_TEXT, INVITE_MASSAGE);
        sendInvite.setType("text/plain");
        Intent chooser = Intent.createChooser(sendInvite, INVITE_TITLE);
        if (sendInvite.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            inviteFriend();
            return true;
        } else
            return false;
    }

    public void showAlertDialog(MenuItem item) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setMessage("Вы уверены что хотите выйти?");
        bld.setTitle("Подтвердите");

        bld.setPositiveButton("Да", (dialog, which) -> {
            MainActivity.super.onBackPressed();
            dialog.dismiss();
        });
        bld.setNegativeButton("Нет", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = bld.create();
        dialog.show();
    }
}
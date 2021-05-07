package com.vladborisov.filmsearchproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    static final String ANSWER_CHECKBOX = "Checkbox";
    static final String ANSWER_COMMENT = "Comments";
    static final String ANSWER = "Answer";
    static final String NAME_NUMBER_OF_CHOOSE_FILM_EXTRA = "Число";
    final static String TAG = MainActivity.class.getSimpleName();
    final static int REQUEST_CODE = 228;
    private static final String INVITE_MASSAGE = "Заходи на огонек!";
    private static final String INVITE_TITLE = "Пригласить друга";
    private List<Film> defaultsContent;
    private FilmAdapter filmAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defaultsContent = generateFilmList();
        /* Setup recyclerView */
        setupRecyclerView();
        onAddClick("Лига справедливости", "Описание к лиге справедливости", R.drawable.film1);
        onAddClick("Лига справедливости", "Описание к лиге справедливости", R.drawable.film1);
        onAddClick("Лига справедливости", "Описание к лиге справедливости", R.drawable.film1);
        onAddClick("Лига справедливости", "Описание к лиге справедливости", R.drawable.film1);
        onAddClick("Мортал комбат", "Описание к мортал комбат", R.drawable.film2);
        /* Create Navigation view and listener on toolbar menu*/
        Toolbar toolbar = findViewById(R.id.content_main_toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_draw_open, R.string.nav_draw_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setOnMenuItemClickListener(this);
        /*Listener on fab*/
        FloatingActionButton floatingActionButton = findViewById(R.id.content_main_fab_button);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityAddFilm.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    private void inviteFriend() {
        Intent sendInvite = new Intent();
        sendInvite.setAction(Intent.ACTION_SEND);
        sendInvite.putExtra(Intent.EXTRA_TEXT, INVITE_MASSAGE);
        sendInvite.setType("text/plain");
        Intent chooser = Intent.createChooser(sendInvite, INVITE_TITLE);
        if (sendInvite.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    public void startActivityForResultWithPutParams(Integer num) {
        Intent intent = new Intent(MainActivity.this, ShowFilm.class);
        intent.putExtra(NAME_NUMBER_OF_CHOOSE_FILM_EXTRA, num);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                boolean answerCheckbox = data.getBooleanExtra(ANSWER_CHECKBOX, true);
                String answerComment = data.getStringExtra(ANSWER_COMMENT);
                String answer = data.getStringExtra(ANSWER);
                Log.d(TAG, "onActivityResult ANSWER_CHECKBOX: [" + answerCheckbox + "]");
                Log.d(TAG, "onActivityResult ANSWER_COMMENT: [" + answerComment + "]");
                Log.d(TAG, "onActivityResult TEST_ANSWER: [" + answer + "]");

            }
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            inviteFriend();
            return true;
        } else return false;
    }
    private void onAddClick(String title, String subTitle, int resource){
        defaultsContent.add(generateNewFilm(title, subTitle, resource));
        filmAdapter.notifyDataSetChanged();
    }
    private Film generateNewFilm(String title, String subTitle, int resource){
        return new Film(title, subTitle, resource);
    }
    private List<Film> generateFilmList(){
        List<Film> films = new ArrayList<>();
        films.add(new Film("Лига справедливости", "Описание к лиге справедливости", R.drawable.film1));
        films.add(new Film("Мортал комбат", "Описание к мортал комбат", R.drawable.film2));
        return films;
    }
    private void onFilmClick(Film film) {
        startActivityForResultWithPutParams(1);
    }
    private void setupRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.content_main_films_list);
        filmAdapter = new FilmAdapter(defaultsContent, this::onFilmClick);
        recyclerView.setAdapter(filmAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
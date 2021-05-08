package com.vladborisov.filmsearchproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainListFilmsFragment extends Fragment {
    private List<Film> defaultsContent;
    private FilmAdapter filmAdapter;
    public static final String TAG = "MainFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defaultsContent = generateFilmList();
        /* Setup recyclerView */
        setupRecyclerView(view);
        onAddClick("Лига справедливости", "Описание к лиге справедливости", R.drawable.film1);
        onAddClick("Лига справедливости", "Описание к лиге справедливости", R.drawable.film1);
        onAddClick("Лига справедливости", "Описание к лиге справедливости", R.drawable.film1);
        onAddClick("Лига справедливости", "Описание к лиге справедливости", R.drawable.film1);
        onAddClick("Мортал комбат", "Описание к мортал комбат", R.drawable.film2);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fragment_content_main_fab_button);
        floatingActionButton.setOnClickListener(v -> {
            System.out.println("Shi");
        });
    }

    private List<Film> generateFilmList() {
        List<Film> films = new ArrayList<>();
        films.add(new Film("Лига справедливости", "Описание к лиге справедливости", R.drawable.film1));
        films.add(new Film("Мортал комбат", "Описание к мортал комбат", R.drawable.film2));
        return films;
    }
    private Film generateNewFilm(String title, String subTitle, int resource) {
        return new Film(title, subTitle, resource);
    }
    private void onAddClick(String title, String subTitle, int resource) {
        defaultsContent.add(generateNewFilm(title, subTitle, resource));
        filmAdapter.notifyDataSetChanged();
    }private void onFilmClick(Film film) {
        Toast.makeText(getContext(), "I", Toast.LENGTH_SHORT).show();
    }
    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.fragment_content_main_films_list);
        filmAdapter = new FilmAdapter(defaultsContent, this::onFilmClick);
        recyclerView.setAdapter(filmAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}

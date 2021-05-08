package com.vladborisov.filmsearchproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class AddFilmFragment extends Fragment {
    public static final String TAG = "AddFilmFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_film, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewGroup container = view.findViewById(R.id.fragment_add_main_container);
        View viewBottomSheet = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_main, container, false);
        container.addView(viewBottomSheet);
        Button createFilmBtn = view.findViewById(R.id.fragment_add_main_edit_film_button);
        createFilmBtn.setOnClickListener(v -> Snackbar.make(container, "Фильм добавлен", Snackbar.LENGTH_LONG).show());
    }
}

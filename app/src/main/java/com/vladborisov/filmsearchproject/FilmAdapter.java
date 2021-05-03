package com.vladborisov.filmsearchproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {
    private final List<Film> films;
    private final Listener onFilmClickListener;

    public FilmAdapter(List<Film> films, Listener onFilmClickListener) {
        this.films = films;
        this.onFilmClickListener = onFilmClickListener;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item, parent, false);
        view.setOnClickListener(v -> onFilmClickListener.onFilmClick((Film) v.getTag()));
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        Film film = films.get(position);
        holder.bind(film);
        holder.itemView.setTag(film);
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    static final class FilmViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView subTitleTextView;
        private final ImageView imageImageView;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.film_item_title_film);
            subTitleTextView = itemView.findViewById(R.id.film_item_sub_title_film);
            imageImageView = itemView.findViewById(R.id.film_item_image_view);
        }

        private void bind(@NonNull Film film) {
            titleTextView.setText(film.title);
            subTitleTextView.setText(film.description);
            imageImageView.setImageResource(film.image);
        }
    }
}

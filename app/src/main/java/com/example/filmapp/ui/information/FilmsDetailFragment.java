package com.example.filmapp.ui.information;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.filmapp.App;
import com.example.filmapp.R;
import com.example.filmapp.data.models.Film;
import com.example.filmapp.databinding.FragmentFilmsDetailBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsDetailFragment extends Fragment {

    private FragmentFilmsDetailBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilmsDetailBinding.inflate(inflater);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = getArguments().getString("key");
        App.api.getMovie(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                Film film = response.body();
                Glide.with(requireActivity()).load(film.getImage()).into(binding.ivImage);
                binding.tvName.setText(film.getTitle());
                binding.tvDescription.setText(film.getDescription());
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }
}
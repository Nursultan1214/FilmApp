package com.example.filmapp.ui.films;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filmapp.App;
import com.example.filmapp.R;
import com.example.filmapp.data.models.Film;
import com.example.filmapp.databinding.FragmentFilmsBinding;
import com.example.filmapp.databinding.ItemFilmBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsFragment extends Fragment {

    private FragmentFilmsBinding binding;
    private FilmAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FilmAdapter();
        adapter.setClick(new FilmAdapter.Click() {
            @Override
            public void clicker(Film film) {
                Bundle bundle = new Bundle();
                bundle.putString("key",film.getId());
                NavController nController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                nController.navigate(R.id.filmsDetailFragment,bundle);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmsBinding.inflate(inflater);
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recycler.setAdapter(adapter);

        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() !=null){
                    adapter.setList(response.body());
                }else{
                    Log.e("TAG", "onResponse: " + response.errorBody().toString() );
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {

            }
        });
    }
}
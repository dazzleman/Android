package ru.geekbrains.lesson9.data;

import ru.geekbrains.lesson9.data.models.RepsModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface Endpoints {

    @GET("repositories")
    Single<List<RepsModel>> getRepos();
}

package ru.geekbrains.lesson9.data.rest;

import ru.geekbrains.lesson9.data.models.RepsModel;

import java.util.List;

import io.reactivex.Single;

public interface NetApiClientInterface {
    Single<List<RepsModel>> getReps();
}

package ru.geekbrains.lesson9.data.rest;

import ru.geekbrains.lesson9.data.Endpoints;
import ru.geekbrains.lesson9.data.models.RepsModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetApiClient implements NetApiClientInterface{

    private static NetApiClient ourInstance;

    public static NetApiClient getInstance() {
        if(ourInstance == null){
            ourInstance = new NetApiClient();
        }
        return ourInstance;
    }

    private Endpoints netApi = new ServiceGenerator().createService(Endpoints.class);

    @Override
    public Single<List<RepsModel>> getReps() {
        return netApi.getRepos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}

package ru.geekbrains.lesson9.presenter;

import moxy.*;
import ru.geekbrains.lesson9.data.models.RepsModel;
import ru.geekbrains.lesson9.data.rest.NetApiClientInterface;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;


@InjectViewState
public class RepsPresenter extends MvpPresenter<RepsView> {

    private NetApiClientInterface client;

    public RepsPresenter(NetApiClientInterface client) {
        this.client = client;
    }

    @Override
    public void attachView(RepsView view) {
        super.attachView(view);
        loadData();
    }

    private void loadData() {
        getViewState().showLoading();
        client.getReps()
                .delaySubscription(5, TimeUnit.SECONDS)
                .subscribe(new SingleObserver<List<RepsModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<RepsModel> repsModels) {
                if (repsModels.isEmpty()) {
                    getViewState().showEmptyState();
                } else {
                    getViewState().showRepoList(repsModels);
                }
                getViewState().hideLoading();
            }

            @Override
            public void onError(Throwable t) {
                getViewState().showError(t);
                getViewState().hideLoading();
            }
        });
    }
}

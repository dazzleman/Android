package ru.geekbrains.lesson9.presenter;

import moxy.*;
import moxy.viewstate.strategy.*;
import ru.geekbrains.lesson9.data.models.RepsModel;

import java.util.List;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface RepsView extends MvpView {

    void showError(Throwable e);

    void showLoading();

    void hideLoading();

    void showRepoList(List<RepsModel> list);

    void showEmptyState();
}

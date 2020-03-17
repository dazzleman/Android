package ru.geekbrains.lesson9;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import moxy.*;
import moxy.presenter.*;
import ru.geekbrains.lesson9.data.models.RepsModel;
import ru.geekbrains.lesson9.data.rest.NetApiClient;
import ru.geekbrains.lesson9.presenter.RepsPresenter;
import ru.geekbrains.lesson9.presenter.RepsView;

import java.util.List;

public class MainActivity extends MvpAppCompatActivity
        implements RepsView {

    private static View view;

    private ProgressBar progress;
    private View content;
    private View empty;
    private Button btnCrash;

    @InjectPresenter
    RepsPresenter repsPresenter;

    @ProvidePresenter
    RepsPresenter providePresenter() {
        return new RepsPresenter(NetApiClient.getInstance());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = findViewById(R.id.contentView);
        progress = findViewById(R.id.loadingView);
        empty = findViewById(R.id.emptyView);
        btnCrash = findViewById(R.id.btnCrash);

        btnCrash.setOnClickListener((v) -> {
            //CrashlyticsCore.getInstance().crash();
            throw new NumberFormatException();
        });

        view = content;
    }

    @Override
    public void showLoading() {
        empty.setVisibility(View.GONE);
        content.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showRepoList(List<RepsModel> list) {
        empty.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyState() {
        empty.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}

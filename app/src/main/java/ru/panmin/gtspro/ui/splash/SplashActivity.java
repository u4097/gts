package ru.panmin.gtspro.ui.splash;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import ru.panmin.gtspro.Application;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.login.LoginActivity;
import ru.panmin.gtspro.ui.tredpoints.TradePointActivity;
import ru.panmin.gtspro.utils.DialogUtils;

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject SplashPresenter splashPresenter;

    public SplashActivity() {
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        splashPresenter.onResume();
    }

    @Override
    protected void onStop() {
        splashPresenter.onStop();
        super.onStop();
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void attachView() {
        splashPresenter.attachView(this);
    }

    @Override
    protected void initViews() {
        splashPresenter.init(Application.get(this).isOnline());
    }

    @Override
    protected void detachView() {
        splashPresenter.detachView();
    }

    @Override
    public void showError(String error) {
        showErrorDialog(error);
    }

    @Override
    public void showNoInternetDialog() {
        showErrorDialog(getString(R.string.no_internet_dialog_content));
    }

    @Override
    public void openAuthActivity() {
        startActivity(LoginActivity.getStartIntent(this));
    }

    @Override
    public void openMainActivity() {
        startActivity(TradePointActivity.getStartIntent(this));
    }

    private void showErrorDialog(String text) {
        DialogUtils.createSplashErrorDialog(
                this, text,
                (dialog, which) -> finishActivity(),
                (dialog, which) -> splashPresenter.init(Application.get(this).isOnline())
        ).show();
    }

}
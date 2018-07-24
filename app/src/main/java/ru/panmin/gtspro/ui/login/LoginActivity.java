package ru.panmin.gtspro.ui.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.main.MainActivity;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject LoginPresenter loginPresenter;

    @BindView(R.id.editTextUserName) AppCompatEditText editTextUserName;
    @BindView(R.id.editTextPassword) AppCompatEditText editTextPassword;
    @BindView(R.id.buttonEnter) AppCompatButton buttonEnter;

    public LoginActivity() {
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void attachView() {
        loginPresenter.attachView(this);
    }

    @Override
    protected void initViews() {
        buttonEnter.setOnClickListener(
                view -> loginPresenter.enter(editTextUserName.getText().toString(), editTextPassword.getText().toString())
        );
    }

    @Override
    protected void detachView() {
        loginPresenter.detachView();
    }

    @Override
    public void openMainActivity() {
        startActivity(MainActivity.getStartIntent(this));
    }

}
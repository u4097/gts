package ru.panmin.gtspro.ui.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.customviews.VectorsSupportEditText;
import ru.panmin.gtspro.ui.tredpoint.TradePointActivity;
import ru.panmin.gtspro.utils.MessageUtils;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject LoginPresenter loginPresenter;

    @BindView(R.id.editTextUserName) VectorsSupportEditText editTextUserName;
    @BindView(R.id.editTextPassword) VectorsSupportEditText editTextPassword;
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
    public void showLoginValidError() {
        MessageUtils.showShortToast(this, R.string.check_user_name_is_correct);
        showKeyboard(editTextUserName);
    }

    @Override
    public void showPasswordValidError() {
        MessageUtils.showShortToast(this, R.string.check_password_is_correct);
        showKeyboard(editTextPassword);
    }

    @Override
    public void openMainActivity() {
        startActivity(TradePointActivity.getStartIntent(this));
    }

    @Override
    public void showError(String error) {
        MessageUtils.showShortToast(this, error);
    }

}
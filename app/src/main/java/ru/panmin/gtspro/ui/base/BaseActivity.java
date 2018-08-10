package ru.panmin.gtspro.ui.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import ru.panmin.gtspro.Application;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.injection.component.ActivityComponent;
import ru.panmin.gtspro.injection.component.ConfigPersistentComponent;
import ru.panmin.gtspro.injection.component.DaggerConfigPersistentComponent;
import ru.panmin.gtspro.injection.module.ActivityModule;
import ru.panmin.gtspro.ui.splash.SplashActivity;
import ru.panmin.gtspro.utils.DialogUtils;
import ru.panmin.gtspro.utils.LocaleManager;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    @SuppressLint("UseSparseArrays")
    private static final Map<Long, ConfigPersistentComponent> sComponentsMap = new HashMap<>();
    @Inject
    SyncPresenter syncPresenter;
    private ActivityComponent mActivityComponent;
    private long activityId;

    private MaterialDialog materialDialog = null;

    public BaseActivity() {
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent(savedInstanceState);
        inject();
        setContentView(getLayout());
        inflateView();
        ButterKnife.bind(this);
        syncPresenter.attachView(this);
        attachView();
        init();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, activityId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!(this instanceof SplashActivity)) {
            syncPresenter.checkNeedSync();
        }
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            sComponentsMap.remove(activityId);
        }
        SmartLocation.with(this).location().stop();
        syncPresenter.detachView();
        detachView();
        super.onDestroy();
    }

    protected void inflateView() {
    }

    protected void init() {
        initViews();
    }

    protected void initActivityComponent(Bundle savedInstanceState) {
        activityId = savedInstanceState != null ? savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();
        ConfigPersistentComponent configPersistentComponent;
        if (!sComponentsMap.containsKey(activityId)) {
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(Application.get(this).getComponent())
                    .build();
            sComponentsMap.put(activityId, configPersistentComponent);
        } else {
            configPersistentComponent = sComponentsMap.get(activityId);
        }
        mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));
    }

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }

    @Override
    public void showProgressDialog() {
        if (materialDialog == null) {
            materialDialog = DialogUtils.createProgressDialog(this);
        }
        if (!materialDialog.isShowing()) {
            materialDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (materialDialog != null) {
            if (materialDialog.isShowing()) {
                materialDialog.dismiss();
            }
            materialDialog = null;
        }
    }

    @Override
    public void showKeyboard(EditText editText) {
        if (editText != null) {
            editText.requestFocus();
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        }
    }

    @Override
    public void hideKeyboard() {
        if (KeyboardVisibilityEvent.isKeyboardVisible(this)) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = getCurrentFocus();
            if (view == null) {
                view = new View(this);
            }
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void finishActivity() {
        hideKeyboard();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    @Override
    public void showError(String error) {
    }

    @Override
    public void showUnknownServerError() {
        showError(getString(R.string.unknown_server_error_title));
    }

    @Override
    public void showUnknownError() {
        showError(getString(R.string.unknown_error_title));
    }

    @Override
    public void startSync() {
        Intent intent = SplashActivity.getStartIntent(this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected abstract void inject();

    @LayoutRes
    protected abstract int getLayout();

    protected abstract void attachView();

    protected abstract void initViews();

    protected abstract void detachView();

    @SuppressLint("CheckResult")
    protected void initGpsConnect(OnLocationUpdatedListener onLocationUpdatedListener) {
        new RxPermissions(this).request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        SmartLocation.with(this)
                                .location()
                                .start(onLocationUpdatedListener);
                    } else {
                    }
                });
    }

}
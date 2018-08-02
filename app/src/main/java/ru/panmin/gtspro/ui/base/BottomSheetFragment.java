package ru.panmin.gtspro.ui.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.panmin.gtspro.ui.splash.SplashActivity;
import ru.panmin.gtspro.utils.DialogUtils;


public abstract class BottomSheetFragment extends BottomSheetDialogFragment implements MvpView {

    protected Unbinder unbinder;
    protected MvpView mvpView;
    private MaterialDialog materialDialog = null;

    public BottomSheetFragment() {
    }

    @Override
    public void onAttach(Context context) {
        mvpView = (MvpView) context;
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        detachView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        mvpView = null;
        super.onDestroyView();
    }

    protected void init() {
        initViews();
    }

    @Override
    public void showProgressDialog() {
        if (materialDialog == null) {
            materialDialog = DialogUtils.createProgressDialog(getActivity());
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
        mvpView.showKeyboard(editText);
    }

    @Override
    public void hideKeyboard() {
        mvpView.hideKeyboard();
    }

    @Override
    public void finishActivity() {
        mvpView.finishActivity();
    }

    @Override
    public void startSync() {
        Intent intent = SplashActivity.getStartIntent(getActivity());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View rootView = Objects.requireNonNull(getActivity()).getLayoutInflater().inflate(getLayout(), null, false);
        unbinder = ButterKnife.bind(this, rootView);
        inject();
        attachView();
        init();
        dialog.setContentView(rootView);
        FrameLayout bottomSheet = Objects.requireNonNull(dialog.getWindow()).findViewById(android.support.design.R.id.design_bottom_sheet);
        //bottomSheet.setBackgroundResource(R.drawable.bottom_sheet_background);
    }

    @LayoutRes
    protected abstract int getLayout();

    protected abstract void inject();

    protected abstract void attachView();

    protected abstract void initViews();

    protected abstract void detachView();
}

package ru.panmin.gtspro.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.panmin.gtspro.utils.DialogUtils;

public abstract class BaseFragment extends Fragment implements MvpView {

    protected Unbinder unbinder;

    private MaterialDialog materialDialog = null;

    private BaseActivity baseActivity;

    public BaseFragment() {
    }

    @Override
    public void onAttach(Context context) {
        baseActivity = (BaseActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject();
        attachView();
        init();
    }

    @Override
    public void onDestroyView() {
        detachView();
        if (unbinder != null) {
            unbinder.unbind();
        }
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
        baseActivity.showKeyboard(editText);
    }

    @Override
    public void hideKeyboard() {
        baseActivity.hideKeyboard();
    }

    @Override
    public void finishActivity() {
        baseActivity.finishActivity();
    }

    @LayoutRes
    protected abstract int getLayout();

    protected abstract void inject();

    protected abstract void attachView();

    protected abstract void initViews();

    protected abstract void detachView();

}
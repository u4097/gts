package ru.panmin.gtspro.ui.toolbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import ru.panmin.gtspro.ui.progress.ProgressFragment;

public abstract class ToolbarFragment extends ProgressFragment implements ToolbarMvpView {

    private ToolbarMvpView toolbarMvpView;

    public ToolbarFragment() {
    }

    @Override
    public void onAttach(Context context) {
        toolbarMvpView = (ToolbarMvpView) context;
        super.onAttach(context);
    }

    @Override
    protected void init() {
        initToolbar();
        super.init();
    }

    @Override
    public void initSearchView() {
        toolbarMvpView.initSearchView();
    }

    @Override
    public void showToolbarShadow() {
        toolbarMvpView.showToolbarShadow();
    }

    @Override
    public void hideToolbarShadow() {
        toolbarMvpView.hideToolbarShadow();
    }

    @Override
    public void showToolbar() {
        toolbarMvpView.showToolbar();
    }

    @Override
    public void hideToolbar() {
        toolbarMvpView.hideToolbar();
    }

    @Override
    public void setTitle(String title) {
        toolbarMvpView.setTitle(title);
    }

    @Override
    public void setTitle(@StringRes int title) {
        toolbarMvpView.setTitle(title);
    }

    @Override
    public void setNavigationIcon(int resId) {
        toolbarMvpView.setNavigationIcon(resId);
    }

    @Override
    public void setNavigationIcon(@Nullable Drawable icon) {
        toolbarMvpView.setNavigationIcon(icon);
    }

    @Override
    public void setNavigationOnClickListener(View.OnClickListener listener) {
        toolbarMvpView.setNavigationOnClickListener(listener);
    }

    @Override
    public void inflateMenu(@MenuRes int menuRes) {
        toolbarMvpView.inflateMenu(menuRes);
    }

    @Nullable
    @Override
    public Menu getMenu() {
        return toolbarMvpView.getMenu();
    }

    @Nullable
    @Override
    public MenuItem findMenuItemById(@IdRes int menuItemId) {
        return toolbarMvpView.findMenuItemById(menuItemId);
    }

    @Override
    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        toolbarMvpView.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    public void removeMenuItem(@IdRes int menuItemId) {
        toolbarMvpView.removeMenuItem(menuItemId);
    }

    @Override
    public MaterialSearchView getSearchView() {
        return toolbarMvpView.getSearchView();
    }

    protected abstract void initToolbar();

}
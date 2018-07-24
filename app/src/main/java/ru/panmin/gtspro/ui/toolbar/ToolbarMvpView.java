package ru.panmin.gtspro.ui.toolbar;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import ru.panmin.gtspro.ui.progress.ProgressMvpView;

public interface ToolbarMvpView extends ProgressMvpView {

    void initSearchView();

    void showToolbarShadow();

    void hideToolbarShadow();

    void showToolbar();

    void hideToolbar();

    void setTitle(String title);

    void setTitle(@StringRes int title);

    void setNavigationIcon(@DrawableRes int resId);

    void setNavigationIcon(@Nullable Drawable icon);

    void setNavigationOnClickListener(View.OnClickListener listener);

    void inflateMenu(@MenuRes int menuRes);

    @Nullable
    Menu getMenu();

    @Nullable
    MenuItem findMenuItemById(@IdRes int menuItemId);

    void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener onMenuItemClickListener);

    void removeMenuItem(@IdRes int menuItemId);

    MaterialSearchView getSearchView();

}
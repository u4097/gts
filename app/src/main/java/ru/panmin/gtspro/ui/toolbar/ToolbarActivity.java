package ru.panmin.gtspro.ui.toolbar;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.progress.ProgressActivity;

public abstract class ToolbarActivity extends ProgressActivity implements ToolbarMvpView {

    @BindView(R.id.toolbar) protected Toolbar toolbar;
    @BindView(R.id.materialSearchView) protected MaterialSearchView materialSearchView;
    @BindView(R.id.toolbarShadow) View toolbarShadow;

    public ToolbarActivity() {
    }

    @Override
    public void onBackPressed() {
        if (materialSearchView.isSearchOpen()) {
            materialSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_toolbar;
    }

    @Override
    protected void init() {
        initToolbar();
        super.init();
    }

    @Override
    public void initSearchView() {
        materialSearchView.setBackgroundColor(ContextCompat.getColor(this, R.attr.colorPrimary));
        materialSearchView.setHintTextColor(ContextCompat.getColor(this, R.color.white_50));
        materialSearchView.setCursorDrawable(R.drawable.cursor_search_view);
        materialSearchView.setBackIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_black_24px));
        materialSearchView.setTextColor(ContextCompat.getColor(this, R.color.white));
        materialSearchView.setCloseIcon(ContextCompat.getDrawable(this, R.drawable.ic_close_black_24px));
        inflateMenu(R.menu.search);
        MenuItem item = findMenuItemById(R.id.action_search);
        materialSearchView.setMenuItem(item);
    }

    @Override
    public void showToolbarShadow() {
        toolbarShadow.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideToolbarShadow() {
        toolbarShadow.setVisibility(View.GONE);
    }

    @Override
    public void showToolbar() {
        toolbar.setVisibility(View.VISIBLE);
        showToolbarShadow();
    }

    @Override
    public void hideToolbar() {
        hideToolbarShadow();
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void setTitle(@StringRes int title) {
        toolbar.setTitle(title);
    }

    @Override
    public void setNavigationIcon(int resId) {
        toolbar.setNavigationIcon(resId);
    }

    @Override
    public void setNavigationIcon(@Nullable Drawable icon) {
        toolbar.setNavigationIcon(icon);
    }

    @Override
    public void setNavigationOnClickListener(View.OnClickListener listener) {
        toolbar.setNavigationOnClickListener(listener);
    }


    @Override
    public void inflateMenu(@MenuRes int menuRes) {
        toolbar.inflateMenu(menuRes);
    }

    @Nullable
    @Override
    public Menu getMenu() {
        return toolbar.getMenu();
    }

    @Nullable
    @Override
    public MenuItem findMenuItemById(@IdRes int menuItemId) {
        return toolbar.getMenu().findItem(menuItemId);
    }

    @Override
    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    public void removeMenuItem(@IdRes int menuItemId) {
        toolbar.getMenu().removeItem(menuItemId);
    }

    @Override
    public MaterialSearchView getSearchView() {
        return materialSearchView;
    }

    protected abstract void initToolbar();

}
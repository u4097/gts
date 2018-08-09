package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressActivity;

public class ChoiseSkuActivity
        extends ProgressActivity
        implements ChoiseSkuMvpView {

    private static final String INTENT_KEY_CLIENT_ID = "client.id";

    public static Intent getStartIntent(Context context, String clientId) {
        Intent intent = new Intent(context, ChoiseSkuActivity.class);
        intent.putExtra(INTENT_KEY_CLIENT_ID, clientId);
        return intent;
    }

    @Inject
    ChoiseSkuPresenter choiseSkuPresenter;

    @BindView(R.id.closeSkuChoiceText)
    AppCompatTextView closeSkuChoiceText;

    @BindView(R.id.skuChoiceTab_layout)
    TabLayout skuChoiceTab_layout;

    @BindView(R.id.skuChoiceView_pager)
    ViewPager skuChoiceView_pager;

    private SkuChoicePagerAdapter skuChoicePagerAdapter = null;

    @Override
    protected int getDataView() {
        return R.layout.activiti_sku_choice;
    }

    @Override
    protected EmptyBundle getEmptyBundle() {
        return null;
    }

    @Override
    protected void emptyButtonClick() {

    }

    @Override
    protected void errorButtonClick() {

    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        choiseSkuPresenter.attachView(this);
    }

    @Override
    protected void initViews() {
        initCloseButton();
        initViewPager();
    }

    private void initViewPager() {

    }

    private void initCloseButton() {
        closeSkuChoiceText.setOnClickListener(view -> finish());
    }

    @Override
    protected void detachView() {
        choiseSkuPresenter.detachView();
    }


}

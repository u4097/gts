package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.SkuListElement;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressActivity;

public class ChoiseSkuActivity
        extends ProgressActivity
        implements ChoiseSkuMvpView,
        BaseSelectSkuInterface {

    private static final String INTENT_KEY_CLIENT_ID = "client.id";

    private static final String INTENT_KEY_TRADEPOINT_ID = "tradepoint.id";

    @Inject
    ChoiseSkuPresenter choiseSkuPresenter;


    @BindView(R.id.closeSkuChoiceText)
    AppCompatTextView closeSkuChoiceText;

    @BindView(R.id.skuChoiceTab_layout)
    TabLayout skuChoiceTab_layout;

    @BindView(R.id.skuChoiceView_pager)
    ViewPager skuChoiceView_pager;

    private SkuChoicePagerAdapter skuChoicePagerAdapter = null;

    public ChoiseSkuActivity() {
    }

    public static Intent getStartIntent(Context context,
                                        String clientId,
                                        String tradePointId) {
        Intent intent = new Intent(context, ChoiseSkuActivity.class);
        intent.putExtra(INTENT_KEY_CLIENT_ID, clientId);
        intent.putExtra(INTENT_KEY_TRADEPOINT_ID, tradePointId);
        return intent;
    }

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
        skuChoicePagerAdapter = new SkuChoicePagerAdapter(getSupportFragmentManager());
        String clientId = getIntent().getStringExtra(INTENT_KEY_CLIENT_ID);
        String tradePointId = getIntent().getStringExtra(INTENT_KEY_TRADEPOINT_ID);
        skuChoicePagerAdapter.setData(clientId, tradePointId);
        skuChoiceView_pager.setAdapter(skuChoicePagerAdapter);
        skuChoiceTab_layout.setupWithViewPager(skuChoiceView_pager);
        skuChoiceView_pager.setOffscreenPageLimit(skuChoicePagerAdapter.getCount());
setStateData();
    }

    private void initCloseButton() {
        closeSkuChoiceText.setOnClickListener(view -> finish());
    }

    @Override
    protected void detachView() {
        choiseSkuPresenter.detachView();
    }


    @Override
    public void selectSku(int page, SkuListElement skuListElement) {
        skuChoicePagerAdapter.selectSku(page, skuListElement);
    }

    @Override
    public void deselectSku(int page, SkuListElement skuListElement) {
        skuChoicePagerAdapter.deselectSku(page, skuListElement);
    }

}
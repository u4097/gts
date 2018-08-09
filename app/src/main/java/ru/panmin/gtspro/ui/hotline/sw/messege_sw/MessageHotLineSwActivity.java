package ru.panmin.gtspro.ui.hotline.sw.messege_sw;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ViewFlipper;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Client;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.ChoiseSkuActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class MessageHotLineSwActivity
        extends ToolbarActivity
        implements MessegeHotLineSwMvpView {

    public MessageHotLineSwActivity() {
    }

    private static final String INTENT_KEY_CLIENT_ID = "client.id";
    private static final String INTENT_KEY_TRADE_POINT_ID = "trade.point.id";

    public static Intent getStartIntent(Context context, String clientId, String tradePointId) {
        Intent intent = new Intent(context, MessageHotLineSwActivity.class);
        intent.putExtra(INTENT_KEY_CLIENT_ID, clientId);
        intent.putExtra(INTENT_KEY_TRADE_POINT_ID, tradePointId);
        return intent;
    }

    @Inject
    MessageHotLineSwPresenter messageHotLineSwPresenter;

    @BindView(R.id.buttonSkuTypeShort)
    AppCompatButton buttonSkuTypeShort;

    @BindView(R.id.buttonTtTypeShort)
    AppCompatButton buttonTtTypeShort;

    @BindView(R.id.viewFlipperType)
    ViewFlipper viewFlipperType;

    @BindView(R.id.skuChoiceTextButton)
    AppCompatImageView skuChoiceTextButton;

    private String clientId;

    @Override
    protected void initToolbar() {
        messageHotLineSwPresenter.getClient(
                getIntent().getStringExtra(INTENT_KEY_CLIENT_ID),
                getIntent().getStringExtra(INTENT_KEY_TRADE_POINT_ID)
        );
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_messedge_hotline;
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
        messageHotLineSwPresenter.attachView(this);
    }

    @Override
    protected void initViews() {
        initButtonChoice();

    }

    private void initButtonChoice() {
        buttonSkuTypeShort.setBackgroundResource(R.drawable.greyish_button_background);
        buttonTtTypeShort.setBackgroundResource(R.drawable.greyish_button_background);
        buttonSkuTypeShort.setOnClickListener(view -> showSkuFragment());
        buttonTtTypeShort.setOnClickListener(view -> showTtFragment());
    }

    private void showTtFragment() {
        viewFlipperType.setVisibility(View.VISIBLE);
        viewFlipperType.setDisplayedChild(1);
        buttonTtTypeShort.setBackgroundResource(R.drawable.azure_button_background);
        buttonSkuTypeShort.setBackgroundResource(R.drawable.greyish_button_background);
    }

    private void showSkuFragment() {
        viewFlipperType.setVisibility(View.VISIBLE);
        viewFlipperType.setDisplayedChild(0);
        buttonSkuTypeShort.setBackgroundResource(R.drawable.azure_button_background);
        buttonTtTypeShort.setBackgroundResource(R.drawable.greyish_button_background);
        initSkuMessageButton();
    }

    private void initSkuMessageButton() {
        skuChoiceTextButton.setOnClickListener(view -> startActivity(ChoiseSkuActivity.getStartIntent(this,clientId)));
    }

    @Override
    protected void detachView() {
        messageHotLineSwPresenter.detachView();
    }


    @Override
    public void setClientSku(Client client) {
        clientId = client.getId();
        setTitle(client.getName().toString(this));
setStateData();
    }
}

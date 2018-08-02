package ru.panmin.gtspro.ui.tradepointinfo.me;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class TradePointInfoMeActivity extends ToolbarActivity implements TradePointInfoMeMvpView {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static final String INTENT_KEY_TRADE_POINT_ID = "trade.point.id";

    @Inject TradePointInfoMePresenter tradePointInfoMePresenter;
    @BindView(R.id.address_text_info) AppCompatTextView address;
    @BindView(R.id.schedule_text_info) AppCompatTextView schedule;
    @BindView(R.id.client_text_info) AppCompatTextView client;
    @BindView(R.id.claims_quantity_text) AppCompatTextView claimsQuantity;
    @BindView(R.id.promotions_text) AppCompatTextView promotions;
    @BindView(R.id.photo_report_text) AppCompatTextView photoReport;
    @BindView(R.id.report_text) AppCompatTextView report;
    private TradePoint tradePoint = null;
    public TradePointInfoMeActivity() {
    }

    public static Intent getStartIntent(Context context, String tradePointId) {
        Intent intent = new Intent(context, TradePointInfoMeActivity.class);
        intent.putExtra(INTENT_KEY_TRADE_POINT_ID, tradePointId);
        return intent;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_trade_point_info_me;
    }

    @Override
    protected void attachView() {
        tradePointInfoMePresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        tradePointInfoMePresenter.getTradePoint(getIntent().getStringExtra(INTENT_KEY_TRADE_POINT_ID));
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void detachView() {
        tradePointInfoMePresenter.detachView();
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
    public void setTradePoint(TradePoint tradePoint) {
        this.tradePoint = tradePoint;
        setTitle(tradePoint.getSignboard().toString(this));
        showInfo();
    }

    @SuppressLint("SetTextI18n")
    private void showInfo() {
        address.setText("Адрес:" + " " + tradePoint.getAddress().toString(this));
        StringBuilder listClients = new StringBuilder();
        for (int i = 0; i < tradePoint.getClients().size(); i++) {
            if (i != tradePoint.getClients().size() - 1) {
                listClients.append(tradePoint.getClients().get(i).getName().toString(this)).append(", ");
            } else {
                listClients.append(tradePoint.getClients().get(i).getName().toString(this));
            }
        }

        StringBuilder listTime = new StringBuilder();

        for (int i = 0; i < tradePoint.getTimes().size(); i++) {
            if (tradePoint.getTimes().get(i).getBegin() != null || tradePoint.getTimes().get(i).getEnd() != null) {
                listTime.append(simpleDateFormat.format(tradePoint.getTimes().get(i).getBegin())).append(" - ").append(simpleDateFormat.format(tradePoint.getTimes().get(i).getEnd()));
            }
        }
        schedule.setText("График Визита:" + " " + "" + listTime);
        client.setText("Клиенты:" + " " + listClients);
        claimsQuantity.setText(String.valueOf(tradePoint.getClaims().size()));
        promotions.setText(String.valueOf(tradePoint.getPromos().size()));
        photoReport.setText(String.valueOf(tradePoint.getPhotoreports().size()));
        report.setText(String.valueOf(tradePoint.getReports().size()));
        setStateData();
    }

}
package ru.panmin.gtspro.ui.tradepointinfo.sv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import ru.panmin.gtspro.utils.MessageUtils;

public class TradePointInfoSvActivity extends ToolbarActivity implements TradePointInfoSvMvpView {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static final String INTENT_KEY_TRADE_POINT_ID = "trade.point.id";

    @Inject TradePointInfoSvPresenter tradePointInfoSvPresenter;
    @Inject ListMeAdapter adapter;
    @BindView(R.id.address_text_info) AppCompatTextView address;
    @BindView(R.id.schedule_text_info) AppCompatTextView schedule;
    @BindView(R.id.client_text_info) AppCompatTextView client;
    @BindView(R.id.claims_quantity_text) AppCompatTextView claimsQuantity;
    @BindView(R.id.promotions_text) AppCompatTextView promotions;
    @BindView(R.id.photo_report_text) AppCompatTextView photoReport;
    @BindView(R.id.report_text) AppCompatTextView report;
    @BindView(R.id.recycler_list_me) RecyclerView recycler;
    private TradePoint tradePoint = null;

    public TradePointInfoSvActivity() {
    }

    public static Intent getStartIntent(Context context, String tradePointId) {
        Intent intent = new Intent(context, TradePointInfoSvActivity.class);
        intent.putExtra(INTENT_KEY_TRADE_POINT_ID, tradePointId);
        return intent;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_trade_point_info_sv;
    }

    @Override
    protected void attachView() {
        tradePointInfoSvPresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        tradePointInfoSvPresenter.getTradePoint(getIntent().getStringExtra(INTENT_KEY_TRADE_POINT_ID));
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
    }

    @Override
    protected void initViews() {
        initGpsConnect(location -> {
            MessageUtils.showShortToast(this, location.getLatitude() + "\n" + location.getLongitude());
        });
    }

    @SuppressLint("SetTextI18n")
    private void showInfo() {
        recycler.setLayoutManager(new GridLayoutManager(this, 3));
        adapter.setData(tradePoint.getMerchandisers());
        recycler.setAdapter(adapter);

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
                listTime.append(tradePoint.getTimes().get(i).getBegin()).append(" - ").append(tradePoint.getTimes().get(i).getEnd());
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

    @Override
    protected void detachView() {
        tradePointInfoSvPresenter.detachView();
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

}
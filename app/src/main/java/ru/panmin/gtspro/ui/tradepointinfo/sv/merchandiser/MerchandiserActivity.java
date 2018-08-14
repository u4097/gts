package ru.panmin.gtspro.ui.tradepointinfo.sv.merchandiser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Merchandiser;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class MerchandiserActivity extends ToolbarActivity implements MerchandiserMvpView {

    private static final String INTENT_KEY_MERCHANDISER_NAME = "merchandiser.name";

    @Inject
    MerchandiserPresenter merchandiserPresenter;
    @Inject
    ClientsMeAdapter clientsMeAdapter;
    @BindView(R.id.client_me_recycler)
    RecyclerView client_me_recycler;
    @BindView(R.id.schedule_de_ure_text_data)
    AppCompatTextView schedule_de_ure_text_data;
    private Merchandiser merchandiser = null;

    public MerchandiserActivity() {
    }

    public static Intent getStartIntent(Context context, String merchandiserName) {
        Intent intent = new Intent(context, MerchandiserActivity.class);
        intent.putExtra(INTENT_KEY_MERCHANDISER_NAME, merchandiserName);
        return intent;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_me_info_trade_point;
    }

    @Override
    protected void attachView() {
        merchandiserPresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        merchandiserPresenter.getMerchandiser(getIntent().getStringExtra(INTENT_KEY_MERCHANDISER_NAME));
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void detachView() {
        merchandiserPresenter.detachView();
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
    public void setMerchandiser(Merchandiser merchandiser) {
        this.merchandiser = merchandiser;
        setTitle(merchandiser.getName());
        initTime();
        initRecycler();
        setStateData();
    }

    private void initTime() {
        StringBuilder listTime = new StringBuilder();
        for (int i = 0; i < merchandiser.getTimes().size(); i++) {
            if (merchandiser.getTimes().get(i).getBegin() != null || merchandiser.getTimes().get(i).getEnd() != null) {
                listTime.append(merchandiser.getTimes().get(i).getBegin()).append(" - ").append(merchandiser.getTimes().get(i).getEnd());
            }
        }
        schedule_de_ure_text_data.setText(listTime);
    }

    private void initRecycler() {
        clientsMeAdapter.setData(merchandiser);
        client_me_recycler.setAdapter(clientsMeAdapter);
        client_me_recycler.setLayoutManager(new LinearLayoutManager(this));
    }

}
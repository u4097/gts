package ru.panmin.gtspro.ui.tradepointinfo.sv.merchandiser;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Merchandiser;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class MerchandiserActivity extends ToolbarActivity implements MerchandiserMvpView {

    private static final String INTENT_KEY_MERCHANDISER_NAME = "merchandiser.name";

    @Inject MerchandiserPresenter merchandiserPresenter;

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
        return R.layout.activity_trade_point_info_sv;
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
    }

}
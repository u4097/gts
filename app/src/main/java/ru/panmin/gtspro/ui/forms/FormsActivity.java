package ru.panmin.gtspro.ui.forms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Form;
import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import ru.panmin.gtspro.utils.OtherUtils;
import ru.panmin.gtspro.utils.SpaceItemDecoration;

public class FormsActivity extends ToolbarActivity implements FormsMvpView, FormsAdapter.OnFormClickListener {

    private static final String INTENT_KEY_PROMO_ID = "promo.id";

    @Inject FormsPresenter formsPresenter;
    @Inject FormsAdapter formsAdapter;

    @BindView(R.id.recyclerViewForms) RecyclerView recyclerViewForms;

    private Promo promo = null;

    public FormsActivity() {
    }

    public static Intent getStartIntent(Context context, String promoId) {
        Intent intent = new Intent(context, FormsActivity.class);
        intent.putExtra(INTENT_KEY_PROMO_ID, promoId);
        return intent;
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_forms;
    }


    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        formsPresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
        setTitle(R.string.form);
    }

    @Override
    protected void initViews() {
        recyclerViewForms.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewForms.addItemDecoration(new SpaceItemDecoration(OtherUtils.dpToPx(8)));
        formsAdapter.setOnFormClickListener(this);
        recyclerViewForms.setAdapter(formsAdapter);

        formsPresenter.getPromo(getIntent().getStringExtra(INTENT_KEY_PROMO_ID));
    }

    @Override
    protected void detachView() {
        formsPresenter.detachView();
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
    public void setPromo(Promo promo) {
        this.promo = promo;
        formsAdapter.setData(promo.getForms());
        setStateData();
    }

    @Override
    public void onFormClick(Form form) {

    }

}
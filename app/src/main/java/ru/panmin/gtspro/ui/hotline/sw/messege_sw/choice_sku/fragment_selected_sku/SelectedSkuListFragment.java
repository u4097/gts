package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_selected_sku;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.SkuListElement;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.BaseSelectSkuInterface;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressFragment;

public class SelectedSkuListFragment
        extends ProgressFragment
        implements SelectedSkuListMvpView,
        BaseSelectSkuInterface {

    private static final String ARG_KEY_CLIENT_ID = "client.id";

    private static final String ARG_KEY_TRADE_POINT_ID = "tradepoint.id";

    @Inject
    SelectedSkuListPresenter selectedSkuListPresenter;
    @Inject
    SelectedAdapter selectedAdapter;
    @BindView(R.id.baseRecycler)
    RecyclerView recyclerView;
    private BaseSelectSkuInterface skuInterface;

    public static SelectedSkuListFragment createInstance(String clientId, String tradePointId) {
        SelectedSkuListFragment fragment = new SelectedSkuListFragment();
        Bundle args = new Bundle();

        args.putString(ARG_KEY_TRADE_POINT_ID, tradePointId);

        args.putString(ARG_KEY_CLIENT_ID, clientId);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        skuInterface = (BaseSelectSkuInterface) context;
    }

    @Override
    protected int getDataView() {
        return R.layout.base_recycler;
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
        ((BaseActivity) Objects.requireNonNull(getActivity())).activityComponent().inject(this);

    }

    @Override
    protected void attachView() {
        selectedSkuListPresenter.attachView(this);
    }

    @Override
    protected void initViews() {
        initRecycler();
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(selectedAdapter);
        setStateData();
    }

    @Override
    protected void detachView() {
        skuInterface = null;
        selectedSkuListPresenter.detachView();
    }


    @Override
    public void selectSku(int page, SkuListElement skuListElement) {
        selectedAdapter.selectSku(skuListElement);
    }

    @Override
    public void deselectSku(int page, SkuListElement skuListElement) {
        selectedAdapter.deselectSku(skuListElement);
    }

}
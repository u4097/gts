package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_all_sku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArraySet;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.SkuListElement;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.BaseSelectSkuInterface;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressFragment;

public class ChoiceSkuAllListFragment
        extends ProgressFragment
        implements ChoiceSkuAllListMvpView,
        BaseSelectSkuInterface {


    private static final String ARG_KEY_CLIENT_ID = "client.id";

    private static final String ARG_KEY_TRADE_POINT_ID = "tradepoint.id";

    @Inject
    ChoiceSkuAllListPresenter choiceSkuAllListPresenter;

    @Inject
    AllAdapter allAdapter;
    @BindView(R.id.baseRecycler)
    RecyclerView recyclerView;
    private BaseSelectSkuInterface skuInterface;

    public static ChoiceSkuAllListFragment createInstance(String clientId, String tradePointId) {
        ChoiceSkuAllListFragment fragment = new ChoiceSkuAllListFragment();
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
        choiceSkuAllListPresenter.attachView(this);
    }

    @Override
    protected void initViews() {
        assert getArguments() != null;
        String clientId = getArguments().getString(ARG_KEY_CLIENT_ID);
        String tradePointId = getArguments().getString(ARG_KEY_TRADE_POINT_ID);
        choiceSkuAllListPresenter.getSkuListElements(tradePointId, clientId);
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(allAdapter);
        setStateData();
    }

    @Override
    protected void detachView() {
        skuInterface = null;
        choiceSkuAllListPresenter.detachView();
    }

    @Override
    public void selectSku(int fromAction, SkuListElement skuListElement) {
        skuInterface.selectSku(fromAction, skuListElement);
    }

    @Override
    public void deselectSku(int fromAction, SkuListElement skuListElement) {
        skuInterface.deselectSku(fromAction, skuListElement);
    }

    @SuppressLint("NewApi")
    @Override
    public void showData(ArraySet<SkuListElement> skuListElements) {
        allAdapter.setData(skuListElements);

        initRecycler();
    }
}

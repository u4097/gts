package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_grop_sku;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Group;
import ru.panmin.gtspro.data.models.SkuForAdapter;
import ru.panmin.gtspro.data.models.SkuListElement;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.BaseSelectSkuInterface;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressFragment;

public class ChoiceSkuGroupListFragment
        extends ProgressFragment
        implements ChoiceSkuListMvpView,
        BaseSelectSkuInterface {

    private static final String ARG_KEY_CLIENT_ID = "client.id";

    private static final String ARG_KEY_TRADE_POINT_ID = "tradepoint.id";

    @Inject
    ChoiceSkuListPresenter choiceSkuListPresenter;

    @BindView(R.id.baseRecycler)
    RecyclerView recyclerView;
    GroupAdapter groupAdapter;
    private List<SkuListElement> skuListElements = new ArrayList<>();
    private BaseSelectSkuInterface skuInterface;

    public static ChoiceSkuGroupListFragment createInstance(String clientId, String tradePointId) {
        ChoiceSkuGroupListFragment fragment = new ChoiceSkuGroupListFragment();
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
        choiceSkuListPresenter.attachView(this);
    }

    @Override
    protected void initViews() {
        assert getArguments() != null;
        String clientId = getArguments().getString(ARG_KEY_CLIENT_ID);
        String tradePointId = getArguments().getString(ARG_KEY_TRADE_POINT_ID);
        choiceSkuListPresenter.getSkuListElements(tradePointId, clientId);
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(groupAdapter);
        setStateData();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (groupAdapter != null) {
            groupAdapter.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (groupAdapter != null && savedInstanceState != null) {
            groupAdapter.onSaveInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void detachView() {
        skuInterface = null;
        choiceSkuListPresenter.detachView();
    }


    @Override
    public void selectSku(int fromAction, SkuListElement skuListElement) {
        skuInterface.selectSku(fromAction, skuListElement);
    }

    @Override
    public void deselectSku(int fromAction, SkuListElement skuListElement) {
        skuInterface.deselectSku(fromAction, skuListElement);
    }


    @Override
    public void showData(HashMap<Group, List<SkuForAdapter>> sort) {
        List<GroupAdapter.GroupForAdapter> list = new ArrayList<>();
        for (Group group : sort.keySet()) {
            list.add(new GroupAdapter.GroupForAdapter(group.getName().toString(), sort.get(group)));
        }
        groupAdapter = new GroupAdapter(list, this);
        initRecycler();
        setStateData();
    }

}
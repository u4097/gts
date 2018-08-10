package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_grop_sku;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.SkuListElement;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressFragment;

public class ChoiceSkuGroupListFragment
        extends ProgressFragment
        implements ChoiceSkuListMvpView {

    public static ChoiceSkuGroupListFragment createInstance() {
        return new ChoiceSkuGroupListFragment();
    }

    private List<SkuListElement> skuListElements = new ArrayList<>();
    @Inject
    ChoiceSkuListPresenter choiceSkuListPresenter;
    @Inject
    GroupAdapter groupAdapter;

    @BindView(R.id.baseRecycler)
    RecyclerView recyclerView;

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
        initRecycler();
    }

    private void initRecycler() {

        // groupAdapter.setSku(skuListElements);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(groupAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        groupAdapter.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        groupAdapter.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void detachView() {
        choiceSkuListPresenter.detachView();
    }


}

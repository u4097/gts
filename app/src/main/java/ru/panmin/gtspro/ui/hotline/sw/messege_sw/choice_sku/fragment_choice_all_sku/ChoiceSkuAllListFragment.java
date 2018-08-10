package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_all_sku;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.fragment_selected_sku.SelectedSkuListFragment;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressFragment;

public class ChoiceSkuAllListFragment
        extends ProgressFragment
        implements ChoiceSkuAllListMvpView {

    public static ChoiceSkuAllListFragment createInstance() {
        return new ChoiceSkuAllListFragment();
    }

    @Inject
    ChoiceSkuAllListPresenter choiceSkuAllListPresenter;
    @Inject
    AllAdapter allAdapter;

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
        choiceSkuAllListPresenter.attachView(this);
    }

    @Override
    protected void initViews() {
        initRecycler();
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(allAdapter);
    }

    @Override
    protected void detachView() {
        choiceSkuAllListPresenter.detachView();
    }
}

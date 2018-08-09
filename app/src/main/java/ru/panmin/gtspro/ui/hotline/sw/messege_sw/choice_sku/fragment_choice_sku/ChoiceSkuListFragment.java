package ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_sku;

import android.support.v7.widget.RecyclerView;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.progress.ProgressFragment;

public class ChoiceSkuListFragment
        extends ProgressFragment
        implements ChoiceSkuListMvpView {

    public static ChoiceSkuListFragment createInstance() {
        return new ChoiceSkuListFragment();
    }

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

    }

    @Override
    protected void detachView() {
        choiceSkuListPresenter.detachView();
    }
}

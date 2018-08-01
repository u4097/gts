package ru.panmin.gtspro.ui.blocks;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import ru.lliepmah.lib.UniversalAdapter;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.blocks.holders.BlockPromoVHBuilder;
import ru.panmin.gtspro.ui.blocks.holders.BlockTitleVHBuilder;
import ru.panmin.gtspro.ui.blocks.holders.BlocksVHBuilder;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.blocks.viewmodel.BlockViewModel;
import ru.panmin.gtspro.ui.blocks.viewmodel.PromViewModelStub;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;

public class BlockActivity extends ToolbarActivity implements BlockMvpView {

    @Inject
    BlockPresenter mainPresenter;

    @BindView(R.id.rvTradePointBrowse)
    RecyclerView rvTradePointBrowse;


    private UniversalAdapter adapter;

    public BlockActivity() {
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, BlockActivity.class);
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        mainPresenter.attachView(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_blocks;
    }

    @Override
    protected void initToolbar() {
        setTitle("                   ОАО Магнит");
        setNavigationIcon(R.drawable.ic_back_arrow);
        inflateMenu(R.menu.logout);
    }

    @Override
    protected void initViews() {
        setStateData();
        initRvAdapter();
    }

    private void initRvAdapter() {
        adapter = new UniversalAdapter(new BlocksVHBuilder(mainPresenter), new BlockTitleVHBuilder(),
                new BlockPromoVHBuilder());

        adapter.clear();

        BlockViewModel blockViewModel = new BlockViewModel();
        blockViewModel.loadData("0");
        adapter.add(blockViewModel.getBlocks());

        BlockType blockType =  new BlockType(BlockType.Type.PROMO);
        adapter.add(blockType);

        PromViewModelStub promViewModelStub = new PromViewModelStub();

        promViewModelStub.loadData("0");
        adapter.addAll(promViewModelStub.getData());

        rvTradePointBrowse.setLayoutManager(new LinearLayoutManager(this));
        rvTradePointBrowse.setAdapter(this.adapter);
    }

    @Override
    protected void detachView() {
        mainPresenter.detachView();
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

}
package ru.panmin.gtspro.ui.blocks;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import ru.lliepmah.lib.UniversalAdapter;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.blocks.holders.BlockPromoVHBuilder;
import ru.panmin.gtspro.ui.blocks.holders.BlockTitleVHBuilder;
import ru.panmin.gtspro.ui.blocks.holders.BlocksVHBuilder;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.blocks.model.PromoModel;
import ru.panmin.gtspro.ui.blocks.viewmodel.BlockViewModel;
import ru.panmin.gtspro.ui.blocks.viewmodel.PromViewModelStub;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.promoinfo.PromoInfoActivity;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import ru.panmin.gtspro.ui.tradepointinfo.me.TradePointInfoMeActivity;

public class BlockActivity extends ToolbarActivity implements BlockMvpView {

    @Inject
    BlockPresenter blockPresenter;

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
        blockPresenter.attachView(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_blocks;
    }

    @Override
    protected void initToolbar() {
        // TODO: 01/08/2018 : Fix title by center toolbar.
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
        adapter = new UniversalAdapter(new BlocksVHBuilder(blockPresenter), new BlockTitleVHBuilder(),
                new BlockPromoVHBuilder(blockPresenter));

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
        blockPresenter.detachView();
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
    public void showInfo(PromoModel promoModel) {
        startActivity(PromoInfoActivity.getStartIntent(this, null));
    }

}
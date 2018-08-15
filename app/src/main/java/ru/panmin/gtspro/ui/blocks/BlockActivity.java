package ru.panmin.gtspro.ui.blocks;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.FormOrReport;
import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.base.BottomSheetFragment;
import ru.panmin.gtspro.ui.blocks.adapters.PhotoReportMeAdapter;
import ru.panmin.gtspro.ui.blocks.adapters.PhotoReportSvAdapter;
import ru.panmin.gtspro.ui.blocks.adapters.PromoMeAdapter;
import ru.panmin.gtspro.ui.blocks.adapters.PromoSvAdapter;
import ru.panmin.gtspro.ui.blocks.adapters.ReportMeAdapter;
import ru.panmin.gtspro.ui.blocks.adapters.ReportSvAdapter;
import ru.panmin.gtspro.ui.blocks.filter.BlockFilter;
import ru.panmin.gtspro.ui.blocks.model.Block;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.blocks.model.BlocksModel;
import ru.panmin.gtspro.ui.blocks.viewmodel.BlockViewModel;
import ru.panmin.gtspro.ui.login.LoginActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.promoinfo.me.PromoInfoMeActivity;
import ru.panmin.gtspro.ui.promoinfo.sv.PromoInfoSvActivity;
import ru.panmin.gtspro.ui.report.me.ReportMeActivity;
import ru.panmin.gtspro.ui.report.sv.ReportSvActivity;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import ru.panmin.gtspro.utils.Constants;

public class BlockActivity
        extends ToolbarActivity
        implements BlockMvpView,
        PromoMeAdapter.InfoClickListener,
        PromoSvAdapter.InfoClickListener,
        PhotoReportMeAdapter.OnPhotoReportClickListener,
        PhotoReportSvAdapter.OnPhotoReportClickListener,
        ReportMeAdapter.OnReportClickListener,
        ReportSvAdapter.OnReportClickListener {

    private static final String INTENT_KEY_TRADE_POINT_ID = "trade.point.id";

    @Inject BlockPresenter blockPresenter;

    @BindView(R.id.btnClaims) FloatingActionButton btnClaims;
    @BindView(R.id.tCounterClaims) TextView tCounterClaims;
    @BindView(R.id.btnPromo) FloatingActionButton btnPromo;
    @BindView(R.id.tCounterPromo) TextView tCounterPromo;
    @BindView(R.id.btnPhotoReport) FloatingActionButton btnPhotoReport;
    @BindView(R.id.tCounterPhotoReport) TextView tCounterPhotoReport;
    @BindView(R.id.btnReport) FloatingActionButton btnReport;
    @BindView(R.id.tCounterReport) TextView tCounterReport;
    @BindView(R.id.btnSku) FloatingActionButton btnSku;
    @BindView(R.id.tCounterSku) TextView tCounterSku;
    @BindView(R.id.btnPlanogram) FloatingActionButton btnPlanogram;
    @BindView(R.id.tCounterPlanogram) TextView tCounterPlanogram;
    @BindView(R.id.btnHotLine) FloatingActionButton btnHotLine;
    @BindView(R.id.tCounterHotLine) TextView tCounterHotLine;
    @BindView(R.id.btnStatistics) FloatingActionButton btnStatistics;
    @BindView(R.id.tCounterStatistics) TextView tCounterStatistics;
    @BindView(R.id.rvPromo) RecyclerView rvBlock;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.fab_filter) FloatingActionButton filter;

    private PhotoReportMeAdapter photoReportMeAdapter;
    private PhotoReportSvAdapter photoReportSvAdapter;

    private PromoMeAdapter promoMeAdapter;
    private PromoSvAdapter promoSvAdapter;

    private ReportMeAdapter reportMeAdapter;
    private ReportSvAdapter reportSvAdapter;

    private String userRole;
    private TradePoint tradePoint = null;

    private BlockType.Type currentBlock = BlockType.Type.NONE;

    public BlockActivity() {
    }

    public static Intent getStartIntent(Context context, String tradePointId) {
        Intent intent = new Intent(context, BlockActivity.class);
        intent.putExtra(INTENT_KEY_TRADE_POINT_ID, tradePointId);
        return intent;
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
    protected void initViews() {
        blockPresenter.initViews(getIntent().getStringExtra(INTENT_KEY_TRADE_POINT_ID));
    }

    @Override
    public void initFilter() {
        filter.setOnClickListener(view -> showDialogFragment(new BlockFilter()));
    }

    private <T extends BottomSheetFragment> void showDialogFragment(T bottomSheetFilter) {
        String tag = bottomSheetFilter.getClass().getSimpleName();
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            bottomSheetFilter.show(getSupportFragmentManager(), bottomSheetFilter.getClass().getSimpleName());
        }
    }

    @Override
    protected int getDataView() {
        return R.layout.activity_blocks;
    }

    @Override
    protected void initToolbar() {
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
        setNavigationOnClickListener(view -> finishActivity());
        inflateMenu(R.menu.logout);
        setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.action_logout:
                            blockPresenter.logout();
                            return true;
                        default:
                            return false;
                    }
                }
        );

    }

    @Override
    public void initViews(String role, TradePoint tradePoint) {
        rvBlock.setLayoutManager(new LinearLayoutManager(this));

        this.userRole = role;
        this.tradePoint = tradePoint;
        setTitle(tradePoint.getSignboard().toString());

        initFilter();
        initBlocks();
        initBlockData(BlockType.Type.PROMO);
        setStateData();
    }

    public void initBlocks() {
        BlockViewModel blockViewModel = new BlockViewModel();
        blockViewModel.loadData(this.tradePoint);
        BlocksModel model = blockViewModel.getBlocks();

        Map<BlockType.Type, Holder> tradePointBlockViews = new HashMap<>();
        tradePointBlockViews.put(BlockType.Type.CLAIMS, new Holder(btnClaims, tCounterClaims));
        tradePointBlockViews.put(BlockType.Type.PROMO, new Holder(btnPromo, tCounterPromo));
        tradePointBlockViews.put(BlockType.Type.PHOTO_REPORT, new Holder(btnPhotoReport, tCounterPhotoReport));
        tradePointBlockViews.put(BlockType.Type.REPORT, new Holder(btnReport, tCounterReport));
        tradePointBlockViews.put(BlockType.Type.SKU, new Holder(btnSku, tCounterSku));
        tradePointBlockViews.put(BlockType.Type.PLANOGRAM, new Holder(btnPlanogram, tCounterPlanogram));
        tradePointBlockViews.put(BlockType.Type.HOT_LINE, new Holder(btnHotLine, tCounterHotLine));
        tradePointBlockViews.put(BlockType.Type.STATISTICS, new Holder(btnStatistics, tCounterStatistics));

        for (Map.Entry<BlockType.Type, Holder> entry : tradePointBlockViews.entrySet()) {
            entry.getValue().btn.setOnClickListener(view -> initBlockData(entry.getKey()));
        }

        for (Block block : model.getBlocks()) {
            Holder holder = tradePointBlockViews.get(block.getType());
            if (holder != null) {
                TextView tvBadge = holder.getTvBadge();
                String count = block.getSize().toString();
                tvBadge.setText(count);
                if (block.getSize() == 0) {
                    tvBadge.setVisibility(View.INVISIBLE);
                } else {
                    tvBadge.setVisibility(View.VISIBLE);
                }

                FloatingActionButton btn = holder.getBtn();
                btn.setBackgroundTintList(getResources().getColorStateList(R.color.btn_selector));

                btn.setSelected(block.isSelected());
                tvBadge.setSelected(block.isSelected());
            }
        }
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
    public void showInfo(Promo promo) {
        switch (this.userRole) {
            case Constants.ROLE_MERCHANDISER:
                startActivity(PromoInfoMeActivity.getStartIntent(this, promo.getId()));
                break;
            case Constants.ROLE_SUPERVISOR:
                startActivity(PromoInfoSvActivity.getStartIntent(this, promo.getId()));
                break;
        }
    }

    @Override
    public void setBlockTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void initBlockData(BlockType.Type blockType) {
        if (currentBlock != blockType) {
            currentBlock = blockType;
            clean();
            rvBlock.setVisibility(View.VISIBLE);
            filter.setVisibility(View.GONE);
            switch (blockType) {
                case PROMO:
                    filter.setVisibility(View.VISIBLE);
                    setBlockTitle("Промо");
                    switch (this.userRole) {
                        case Constants.ROLE_MERCHANDISER:
                            promoMeAdapter = new PromoMeAdapter();
                            promoMeAdapter.setInfoClickListener(this);
                            promoMeAdapter.setData(tradePoint.getPromos());
                            rvBlock.setAdapter(promoMeAdapter);
                            break;
                        case Constants.ROLE_SUPERVISOR:
                            promoSvAdapter = new PromoSvAdapter();
                            promoSvAdapter.setInfoClickListener(this);
                            promoSvAdapter.setData(tradePoint.getPromos());
                            rvBlock.setAdapter(promoSvAdapter);
                            break;
                    }
                    break;
                case PHOTO_REPORT:
                    setBlockTitle("Фотоотчеты");
                    switch (this.userRole) {
                        case Constants.ROLE_MERCHANDISER:
                            photoReportMeAdapter = new PhotoReportMeAdapter();
                            photoReportMeAdapter.setOnPhotoReportClickListener(this);
                            photoReportMeAdapter.setData(tradePoint.getPhotoreports());
                            rvBlock.setAdapter(photoReportMeAdapter);
                            break;
                        case Constants.ROLE_SUPERVISOR:
                            photoReportSvAdapter = new PhotoReportSvAdapter();
                            photoReportSvAdapter.setOnPhotoReportClickListener(this);
                            photoReportSvAdapter.setData(tradePoint.getPhotoreports());
                            rvBlock.setAdapter(photoReportSvAdapter);
                            break;
                    }
                    break;
                case REPORT:
                    setBlockTitle("Отчеты");
                    switch (this.userRole) {
                        case Constants.ROLE_MERCHANDISER:
                            reportMeAdapter = new ReportMeAdapter();
                            reportMeAdapter.setOnReportClickListener(this);
                            reportMeAdapter.setData(tradePoint.getReports());
                            rvBlock.setAdapter(reportMeAdapter);
                            break;
                        case Constants.ROLE_SUPERVISOR:
                            reportSvAdapter = new ReportSvAdapter();
                            reportSvAdapter.setOnReportClickListener(this);
                            reportSvAdapter.setData(tradePoint.getReports());
                            rvBlock.setAdapter(reportSvAdapter);
                            break;
                    }
                    break;
                default:
                    rvBlock.setVisibility(View.GONE);
                    setBlockTitle("Блок " + blockType + " в разработке");
                    break;
            }
        }
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void selectNewSortType(String sortType) {
        blockPresenter.selectNewSortType(sortType);
    }

    private void clean() {
        rvBlock.setAdapter(null);

        if (promoMeAdapter != null) {
            promoMeAdapter.setInfoClickListener(null);
            promoMeAdapter.setData(new ArrayList<>());
            promoMeAdapter = null;
        }

        if (promoSvAdapter != null) {
            promoSvAdapter.setInfoClickListener(null);
            promoSvAdapter.setData(new ArrayList<>());
            promoSvAdapter = null;
        }

        if (photoReportMeAdapter != null) {
            photoReportMeAdapter.setOnPhotoReportClickListener(null);
            photoReportMeAdapter.setData(new ArrayList<>());
            photoReportMeAdapter = null;
        }

        if (photoReportSvAdapter != null) {
            photoReportSvAdapter.setOnPhotoReportClickListener(null);
            photoReportSvAdapter.setData(new ArrayList<>());
            photoReportSvAdapter = null;
        }

        if (reportMeAdapter != null) {
            reportMeAdapter.setOnReportClickListener(null);
            reportMeAdapter.setData(new ArrayList<>());
            reportMeAdapter = null;
        }

        if (reportSvAdapter != null) {
            reportSvAdapter.setOnReportClickListener(null);
            reportSvAdapter.setData(new ArrayList<>());
            reportSvAdapter = null;
        }
    }

    @Override
    public void onPhotoReportClick(FormOrReport photoReport) {
        switch (this.userRole) {
            case Constants.ROLE_MERCHANDISER:
                startActivity(ReportMeActivity.getStartIntent(this, photoReport.getId()));
                break;
            case Constants.ROLE_SUPERVISOR:
                startActivity(ReportSvActivity.getStartIntent(this, photoReport.getId()));
                break;
        }
    }

    @Override
    public void onReportClick(FormOrReport report) {
        switch (this.userRole) {
            case Constants.ROLE_MERCHANDISER:
                startActivity(ReportMeActivity.getStartIntent(this, report.getId()));
                break;
            case Constants.ROLE_SUPERVISOR:
                startActivity(ReportSvActivity.getStartIntent(this, report.getId()));
                break;
        }
    }

    class Holder {

        FloatingActionButton btn;
        TextView tvBadge;

        Holder(FloatingActionButton btn, TextView tvBadge) {
            this.btn = btn;
            this.tvBadge = tvBadge;
        }

        FloatingActionButton getBtn() {
            return btn;
        }

        TextView getTvBadge() {
            return tvBadge;
        }

    }

}
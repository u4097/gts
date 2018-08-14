package ru.panmin.gtspro.ui.blocks;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import io.realm.RealmList;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Claim;
import ru.panmin.gtspro.data.models.Client;
import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.base.BottomSheetFragment;
import ru.panmin.gtspro.ui.blocks.adapters.ClaimMeAdapter;
import ru.panmin.gtspro.ui.blocks.adapters.ClaimSvAdapter;
import ru.panmin.gtspro.ui.blocks.adapters.PromoMeAdapter;
import ru.panmin.gtspro.ui.blocks.adapters.PromoSvAdapter;
import ru.panmin.gtspro.ui.blocks.filter.BlockFilter;
import ru.panmin.gtspro.ui.blocks.model.Block;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.blocks.model.BlocksModel;
import ru.panmin.gtspro.ui.blocks.viewmodel.BlockViewModel;
import ru.panmin.gtspro.ui.claiminfo.me.ClaimInfoMeActivity;
import ru.panmin.gtspro.ui.claiminfo.sv.ClaimInfoSvActivity;
import ru.panmin.gtspro.ui.login.LoginActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.promoinfo.me.PromoInfoMeActivity;
import ru.panmin.gtspro.ui.promoinfo.sv.PromoInfoSvActivity;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import ru.panmin.gtspro.utils.Constants;
import timber.log.Timber;

public class BlockActivity extends ToolbarActivity implements BlockMvpView,
        PromoMeAdapter.InfoClickListener,
        PromoSvAdapter.PromoClickListener,
        ClaimMeAdapter.ClaimClickListener,
        ClaimSvAdapter.ClaimClickListener
{

    @BindView(R.id.btnClaims)
    FloatingActionButton btnClaims;
    @BindView(R.id.tCounterClaims)
    TextView tCounterClaims;
    @BindView(R.id.btnPromo)
    FloatingActionButton btnPromo;
    @BindView(R.id.tCounterPromo)
    TextView tCounterPromo;
    @BindView(R.id.btnPhotoReport)
    FloatingActionButton btnPhotoReport;
    @BindView(R.id.tCounterPhotoReport)
    TextView tCounterPhotoReport;
    @BindView(R.id.btnReport)
    FloatingActionButton btnReport;
    @BindView(R.id.tCounterReport)
    TextView tCounterReport;
    @BindView(R.id.btnSku)
    FloatingActionButton btnSku;
    @BindView(R.id.tCounterSku)
    TextView tCounterSku;
    @BindView(R.id.btnPlanogram)
    FloatingActionButton btnPlanogram;
    @BindView(R.id.tCounterPlanogram)
    TextView tCounterPlanogram;
    @BindView(R.id.btnHotLine)
    FloatingActionButton btnHotLine;
    @BindView(R.id.tCounterHotLine)
    TextView tCounterHotLine;
    @BindView(R.id.btnStatistics)
    FloatingActionButton btnStatistics;
    @BindView(R.id.tCounterStatistics)
    TextView tCounterStatistics;
    @BindView(R.id.rvPromo)
    RecyclerView rvBlock;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.fab_filter)
    FloatingActionButton filter;

    @Inject
    BlockPresenter blockPresenter;


    PromoMeAdapter promoMeAdapter;
    PromoSvAdapter promoSvAdapter;

    ClaimMeAdapter claimMeAdapter;
    ClaimSvAdapter claimSvAdapter;
    String userRole;


    private static final String INTENT_KEY_TRADE_POINT_ID = "trade.point.id";
    private TradePoint tradePoint = null;
    private RealmList<Claim> claims;


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
        blockPresenter.initViews();
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
    public void initViews(String fullName, String role) {
        initFilter();
        this.userRole = role;
//        this.userRole = Constants.ROLE_SUPERVISOR;
        blockPresenter.getTradePoint(getIntent().getStringExtra(INTENT_KEY_TRADE_POINT_ID));
        initBlocks();

    }


    private Map<BlockType.Type, Holder> tradePointBlockViews;
    private Map<String, Client> clients = new HashMap<>();

    public void initBlocks() {
        BlockViewModel blockViewModel = new BlockViewModel();
        blockViewModel.loadData(this.tradePoint);
        BlocksModel model = blockViewModel.getBlocks();

        tradePointBlockViews = new HashMap<>();
        tradePointBlockViews.put(BlockType.Type.CLAIMS, new Holder(btnClaims, tCounterClaims));
        tradePointBlockViews.put(BlockType.Type.PROMO, new Holder(btnPromo, tCounterPromo));
        tradePointBlockViews.put(BlockType.Type.PHOTO_REPORT, new Holder(btnPhotoReport, tCounterPhotoReport));
        tradePointBlockViews.put(BlockType.Type.REPORT, new Holder(btnReport, tCounterReport));
        tradePointBlockViews.put(BlockType.Type.SKU, new Holder(btnSku, tCounterSku));
        tradePointBlockViews.put(BlockType.Type.PLANOGRAM, new Holder(btnPlanogram, tCounterPlanogram));
        tradePointBlockViews.put(BlockType.Type.HOT_LINE, new Holder(btnHotLine, tCounterHotLine));
        tradePointBlockViews.put(BlockType.Type.STATISTICS, new Holder(btnStatistics, tCounterStatistics));

        for (Map.Entry<BlockType.Type, Holder> entry :
                tradePointBlockViews.entrySet()) {
            entry.getValue().btn.setOnClickListener(view -> blockPresenter.onTradePointBlockClick(entry.getKey()));
        }

        for (Block block : model.getBlocks()
        ) {
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

    class Holder {
        FloatingActionButton btn;
        TextView tvBadge;

        public Holder(FloatingActionButton btn, TextView tvBadge) {
            this.btn = btn;
            this.tvBadge = tvBadge;
        }

        public FloatingActionButton getBtn() {
            return btn;
        }

        public TextView getTvBadge() {
            return tvBadge;
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
    public void showPromo(Promo promo) {
        switch (this.userRole) {
            case Constants.ROLE_MERCHANDISER:
                startActivity(PromoInfoMeActivity.getStartIntent(this, promo.getId()));
                break;
            case Constants.ROLE_SUPERVISOR:
                startActivity(PromoInfoSvActivity.getStartIntent(this, promo.getId()));
                break;
            default:
                break;
        }
    }

    @Override
    public void showClaim(Claim claim) {
        switch (this.userRole) {
            case Constants.ROLE_MERCHANDISER:
                startActivity(ClaimInfoMeActivity.getStartIntent(this, claim.getId()));
                break;
            case Constants.ROLE_SUPERVISOR:
                startActivity(ClaimInfoSvActivity.getStartIntent(this, claim.getId()));
                break;
            default:
                Timber.d("Not implemented");
//                startActivity(PromoInfoMeActivity.getStartIntent(this, promo.getId()));
                break;
        }
    }

    @Override
    public void setBlockTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setTradePoint(TradePoint tradePoint) {
        this.tradePoint = tradePoint;
        if (tradePoint != null) {
            this.claims = tradePoint.getClaims();
            for (Claim claim : claims) {
                this.clients.put(claim.getClientId(), blockPresenter.getClientById(claim.getClientId()));
            }
            setTitle(tradePoint.getSignboard().toString(this));
        }
        BlockType.Type blockType = blockPresenter.getCurrentBlock();
        initBlockData(blockType);
        setStateData();
    }

    @Override
    public void initBlockData(BlockType.Type blockType) {
        if (this.tradePoint == null) {
            Timber.e("tradePoint is null");
            return;
        }
        if (blockType == BlockType.Type.PROMO) {
            rvBlock.setVisibility(View.VISIBLE);
            setBlockTitle("Промо");
        } else if (blockType == BlockType.Type.CLAIMS) {
            rvBlock.setVisibility(View.VISIBLE);
            setBlockTitle("Претензии");
        } else {
            setBlockTitle("Блок " + blockType + " в разработке");
            rvBlock.setVisibility(View.GONE);
        }
        rvBlock.setLayoutManager(new LinearLayoutManager(this));

        if (blockType == BlockType.Type.PROMO) {
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
                default:
                    rvBlock.setVisibility(View.GONE);
                    break;
            }
        }

        if (blockType == BlockType.Type.CLAIMS) {
            switch (this.userRole) {
                case Constants.ROLE_MERCHANDISER:
                    claimMeAdapter = new ClaimMeAdapter();
                    claimMeAdapter.setInfoClickListener(this);

                    claimMeAdapter.setData(this.claims, this.clients);
                    rvBlock.setAdapter(claimMeAdapter);
                    break;
                case Constants.ROLE_SUPERVISOR:
                    claimSvAdapter = new ClaimSvAdapter();
                    claimSvAdapter.setInfoClickListener(this);
                    claimSvAdapter.setData(this.claims, this.clients);
                    rvBlock.setAdapter(claimSvAdapter);
                    break;
                default:
                    rvBlock.setVisibility(View.GONE);
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

    @Override
    public void setClaim(RealmList<Claim> claims) {
        this.claims = claims;
    }
}
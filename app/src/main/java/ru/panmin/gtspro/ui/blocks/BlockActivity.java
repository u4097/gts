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
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Promo;
import ru.panmin.gtspro.data.models.TradePoint;
import ru.panmin.gtspro.ui.blocks.adapters.PromoMeAdapter;
import ru.panmin.gtspro.ui.blocks.adapters.PromoSvAdapter;
import ru.panmin.gtspro.ui.blocks.model.Block;
import ru.panmin.gtspro.ui.blocks.model.BlockType;
import ru.panmin.gtspro.ui.blocks.model.BlocksModel;
import ru.panmin.gtspro.ui.blocks.viewmodel.BlockViewModel;
import ru.panmin.gtspro.ui.login.LoginActivity;
import ru.panmin.gtspro.ui.progress.EmptyBundle;
import ru.panmin.gtspro.ui.promoinfo.me.PromoInfoMeActivity;
import ru.panmin.gtspro.ui.promoinfo.sv.PromoInfoSvActivity;
import ru.panmin.gtspro.ui.toolbar.ToolbarActivity;
import ru.panmin.gtspro.utils.Constants;

public class BlockActivity extends ToolbarActivity implements BlockMvpView, PromoMeAdapter.InfoClickListener, PromoSvAdapter.InfoClickListener {

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
    RecyclerView rvPromo;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @Inject
    BlockPresenter blockPresenter;


    PromoMeAdapter promoMeAdapter;
    PromoSvAdapter promoSvAdapter;
    String userRole;

    private OnTradePointBlockClickListener listener = null;
    private Map<BlockType.Type, Holder> tradePointBlockViews;

    private static final String INTENT_KEY_TRADE_POINT_ID = "trade.point.id";


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

    public interface OnTradePointBlockClickListener {
        void onTradePointBlockClick(BlockType.Type blockType);
    }

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
    protected int getDataView() {
        return R.layout.activity_blocks;
    }

    @Override
    protected void initToolbar() {
        setNavigationIcon(R.drawable.ic_arrow_back_black_24px);
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
    protected void initViews() {
        this.userRole = Constants.ROLE_MERCHANDISER;
        blockPresenter.getTradePoint(getIntent().getStringExtra(INTENT_KEY_TRADE_POINT_ID));

        tradePointBlockViews = new HashMap<>();
        tradePointBlockViews.put(BlockType.Type.CLAIMS, new Holder(btnClaims, tCounterClaims));
        tradePointBlockViews.put(BlockType.Type.PROMO, new Holder(btnPromo, tCounterPromo));
        tradePointBlockViews.put(BlockType.Type.PHOTO_REPORT, new Holder(btnPhotoReport, tCounterPhotoReport));
        tradePointBlockViews.put(BlockType.Type.REPORT, new Holder(btnReport, tCounterReport));
        tradePointBlockViews.put(BlockType.Type.SKU, new Holder(btnSku, tCounterSku));
        tradePointBlockViews.put(BlockType.Type.PLANOGRAM, new Holder(btnPlanogram, tCounterPlanogram));
        tradePointBlockViews.put(BlockType.Type.HOT_LINE, new Holder(btnHotLine, tCounterHotLine));
        tradePointBlockViews.put(BlockType.Type.STATISTICS, new Holder(btnStatistics, tCounterStatistics));

        for (Map.Entry<BlockType.Type, Holder> entry:
                tradePointBlockViews.entrySet()) {
            entry.getValue().btn.setOnClickListener(view -> blockPresenter.onTradePointBlockClick(entry.getKey()));

        }

        BlockViewModel blockViewModel = new BlockViewModel();
        blockViewModel.loadData("0");
        initBlocks(blockViewModel.getBlocks());

    }


    public void initBlocks(BlocksModel model) {
        for (Block block: model.getBlocks()
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

        tvTitle.setText("Промо");
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
            default:
                startActivity(PromoInfoMeActivity.getStartIntent(this, promo.getId()));
                break;
        }
    }

    @Override
    public void setTradePoint(TradePoint tradePoint) {
        setTitle(tradePoint.getSignboard().toString(this));
        rvPromo.setLayoutManager(new LinearLayoutManager(this));

        switch (this.userRole) {
            case Constants.ROLE_MERCHANDISER:
                promoMeAdapter = new PromoMeAdapter();
                promoMeAdapter.setInfoClickListener(this);
                promoMeAdapter.setData(tradePoint.getPromos());
                rvPromo.setAdapter(promoMeAdapter);
                break;
            case Constants.ROLE_SUPERVISOR:
                promoSvAdapter = new PromoSvAdapter();
                promoSvAdapter.setInfoClickListener(this);
                promoSvAdapter.setData(tradePoint.getPromos());
                rvPromo.setAdapter(promoSvAdapter);
                break;
            default:
                promoMeAdapter = new PromoMeAdapter();
                rvPromo.setLayoutManager(new LinearLayoutManager(this));
                promoMeAdapter.setInfoClickListener(this);
                promoMeAdapter.setData(tradePoint.getPromos());
                rvPromo.setAdapter(promoMeAdapter);
                break;
        }
        setStateData();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
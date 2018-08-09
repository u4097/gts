package ru.panmin.gtspro.injection.component;

import dagger.Subcomponent;
import ru.panmin.gtspro.injection.PerActivity;
import ru.panmin.gtspro.injection.module.ActivityModule;
import ru.panmin.gtspro.ui.blocks.BlockActivity;
import ru.panmin.gtspro.ui.blocks.filter.BlockFilter;
import ru.panmin.gtspro.ui.claiminfo.sv.ClaimInfoSvActivity;
import ru.panmin.gtspro.ui.login.LoginActivity;
import ru.panmin.gtspro.ui.claiminfo.me.ClaimInfoMeActivity;
import ru.panmin.gtspro.ui.promoinfo.me.PromoInfoMeActivity;
import ru.panmin.gtspro.ui.promoinfo.sv.PromoInfoSvActivity;
import ru.panmin.gtspro.ui.splash.SplashActivity;
import ru.panmin.gtspro.ui.tradepointinfo.me.TradePointInfoMeActivity;
import ru.panmin.gtspro.ui.tradepointinfo.sv.TradePointInfoSvActivity;
import ru.panmin.gtspro.ui.tradepointinfo.sv.merchandiser.MerchandiserActivity;
import ru.panmin.gtspro.ui.tredpoints.TradePointActivity;
import ru.panmin.gtspro.ui.tredpoints.filter.BottomSheetFilter;
import ru.panmin.gtspro.ui.tredpoints.map.MapFragment;
import ru.panmin.gtspro.ui.tredpoints.me.MeTradePointFragment;
import ru.panmin.gtspro.ui.tredpoints.sv.SwTradePointFragment;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    //активити
    void inject(SplashActivity splashActivity);
    void inject(LoginActivity loginActivity);
    void inject(TradePointActivity tradePointActivity);
    void inject(TradePointInfoMeActivity tradePointInfoMeActivity);
    void inject(TradePointInfoSvActivity tradePointInfoSvActivity);
    void inject(MerchandiserActivity merchandiserActivity);
    void inject(BlockActivity blockActivity);
    void inject(PromoInfoMeActivity promoInfoMeActivity);
    void inject(PromoInfoSvActivity promoInfoSvActivity);
    void inject(ClaimInfoMeActivity claimInfoMeActivity);
    void inject(ClaimInfoSvActivity claimInfoSvActivity);

    //fragment
    void inject(MeTradePointFragment meTradePointFragment);
    void inject(MapFragment mapFragment);
    void inject(SwTradePointFragment swTradePointFragment);
    void inject(BottomSheetFilter bottomSheetFilter);
    void inject(BlockFilter blockFilter);
}
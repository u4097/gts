package ru.panmin.gtspro.injection.component;

import dagger.Subcomponent;
import ru.panmin.gtspro.injection.PerActivity;
import ru.panmin.gtspro.injection.module.ActivityModule;
import ru.panmin.gtspro.ui.blocks.BlockActivity;
import ru.panmin.gtspro.ui.login.LoginActivity;
import ru.panmin.gtspro.ui.splash.SplashActivity;
import ru.panmin.gtspro.ui.tradepointinfo.me.TradePointInfoMeActivity;
import ru.panmin.gtspro.ui.tradepointinfo.sv.TradePointInfoSvActivity;
import ru.panmin.gtspro.ui.tradepointinfo.sv.merchandiser.MerchandiserActivity;
import ru.panmin.gtspro.ui.tredpoint.TradePointActivity;
import ru.panmin.gtspro.ui.tredpoint.map.MapFragment;
import ru.panmin.gtspro.ui.tredpoint.merchandise_trade_point.MeTradePointFragment;
import ru.panmin.gtspro.ui.tredpoint.supervision_trade_point.SwTradePointFragment;

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

    //fragment
    void inject(MeTradePointFragment meTradePointFragment);
    void inject(MapFragment mapFragment);
    void inject(SwTradePointFragment swTradePointFragment);

}
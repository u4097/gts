package ru.panmin.gtspro.injection.component;

import dagger.Subcomponent;
import ru.panmin.gtspro.injection.PerActivity;
import ru.panmin.gtspro.injection.module.ActivityModule;
import ru.panmin.gtspro.ui.login.LoginActivity;
import ru.panmin.gtspro.ui.main.MainActivity;
import ru.panmin.gtspro.ui.splash.SplashActivity;
import ru.panmin.gtspro.ui.tredpoint.TradePointActivity;
import ru.panmin.gtspro.ui.tredpoint.merchandise_trade_point.MeTradePointFragment;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    //активити
    void inject(SplashActivity splashActivity);
    void inject(LoginActivity loginActivity);
    void inject(MainActivity mainActivity);
    void inject(TradePointActivity tradePointActivity);

    //fragment
    void inject(MeTradePointFragment meTradePointFragment);

}
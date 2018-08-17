package ru.panmin.gtspro.injection.component;

import dagger.Subcomponent;
import ru.panmin.gtspro.injection.PerActivity;
import ru.panmin.gtspro.injection.module.ActivityModule;
import ru.panmin.gtspro.ui.blocks.BlockActivity;
import ru.panmin.gtspro.ui.blocks.filter.BlockFilter;
import ru.panmin.gtspro.ui.claiminfo.me.ChatActivity;
import ru.panmin.gtspro.ui.claiminfo.me.ClaimInfoMeActivity;
import ru.panmin.gtspro.ui.claiminfo.sv.ClaimInfoSvActivity;
import ru.panmin.gtspro.ui.forms.FormsActivity;
import ru.panmin.gtspro.ui.fullscreenimage.FullscreenImageActivity;
import ru.panmin.gtspro.ui.hotline.me.HotlineMeActivity;
import ru.panmin.gtspro.ui.hotline.sw.HotlineSvActivity;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.MessageHotLineSwActivity;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.ChoiseSkuActivity;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_all_sku.ChoiceSkuAllListFragment;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_choice_grop_sku.ChoiceSkuGroupListFragment;
import ru.panmin.gtspro.ui.hotline.sw.messege_sw.choice_sku.fragment_selected_sku.SelectedSkuListFragment;
import ru.panmin.gtspro.ui.login.LoginActivity;
import ru.panmin.gtspro.ui.promoinfo.me.PromoInfoMeActivity;
import ru.panmin.gtspro.ui.promoinfo.sv.PromoInfoSvActivity;
import ru.panmin.gtspro.ui.questiongroups.QuestionGroupsActivity;
import ru.panmin.gtspro.ui.questionnaire.QuestionnaireActivity;
import ru.panmin.gtspro.ui.report.me.ReportMeActivity;
import ru.panmin.gtspro.ui.report.sv.ReportSvActivity;
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
    void inject(HotlineMeActivity hotlineMeActivity);
    void inject(HotlineSvActivity hotlineSvActivity);
    void inject(MessageHotLineSwActivity messageHotLineSwActivity);
    void inject(ChoiseSkuActivity choiseSkuActivity);
    void inject(PromoInfoMeActivity promoInfoMeActivity);
    void inject(PromoInfoSvActivity promoInfoSvActivity);
    void inject(ClaimInfoMeActivity claimInfoMeActivity);
    void inject(ClaimInfoSvActivity claimInfoSvActivity);
    void inject(FormsActivity formsActivity);
    void inject(QuestionGroupsActivity questionGroupsActivity);
    void inject(ReportMeActivity reportMeActivity);
    void inject(ReportSvActivity reportSvActivity);
    void inject(QuestionnaireActivity questionnaireActivity);
    void inject(FullscreenImageActivity fullscreenImageActivity);
    void inject(ChatActivity reportActivity);

    //fragment
    void inject(MeTradePointFragment meTradePointFragment);
    void inject(MapFragment mapFragment);
    void inject(SwTradePointFragment swTradePointFragment);
    void inject(BottomSheetFilter bottomSheetFilter);
    void inject(ChoiceSkuGroupListFragment choiceSkuGroupListFragment);
    void inject(ChoiceSkuAllListFragment choiceSkuAllListFragment);
    void inject(SelectedSkuListFragment selectedSkuListFragment);
    void inject(BlockFilter blockFilter);

}
package ru.panmin.gtspro.ui.tredpoints.filter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.base.BottomSheetFragment;
import ru.panmin.gtspro.ui.tredpoints.TradePointMvpView;
import ru.panmin.gtspro.utils.Constants;

public class BottomSheetFilter extends BottomSheetFragment implements BottomSheetFilterMvpView {

    @Inject
    BottomSheetFilterPresenter presenter;

    @BindView(R.id.close_textView)
    AppCompatTextView close;
    @BindView(R.id.radio_group_filter)
    RadioGroup radioGroup;
    @BindView(R.id.rbByVisitTime)
    RadioButton rbByVisitTime;
    @BindView(R.id.rbByDistance)
    RadioButton rbByDistance;
    @BindView(R.id.rbAlphabetically)
    RadioButton rbAlphabetically;

    private TradePointMvpView tradePointMvpView;

    public BottomSheetFilter() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        tradePointMvpView = (TradePointMvpView) context;
    }

    @Override
    protected int getLayout() {
        return R.layout.filter_bottom_sheet;
    }

    @Override
    protected void inject() {
        ((BaseActivity) Objects.requireNonNull(getActivity())).activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        presenter.attachView(this);
    }

    @Override
    protected void initViews() {
        presenter.initViews();
        close.setOnClickListener(view -> dismiss());
    }

    @Override
    protected void detachView() {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rbByVisitTime:
                presenter.detachView(Constants.SORT_TYPE_TIME);
                break;
            case R.id.rbByDistance:
                presenter.detachView(Constants.SORT_TYPE_DISTANCE);
                break;
            case R.id.rbAlphabetically:
                presenter.detachView(Constants.SORT_TYPE_ALPHABET);
                break;
        }
        tradePointMvpView = null;
    }

    @Override
    public void showError(String error) {
    }

    @Override
    public void showUnknownServerError() {
    }

    @Override
    public void showUnknownError() {
    }

    @Override
    public void afterViews(String sortType) {
        switch (sortType) {
            case Constants.SORT_TYPE_TIME:
                rbByVisitTime.setChecked(true);
                break;
            case Constants.SORT_TYPE_DISTANCE:
                rbByDistance.setChecked(true);
                break;
            case Constants.SORT_TYPE_ALPHABET:
                rbAlphabetically.setChecked(true);
                break;
        }
    }

    @Override
    public void selectNewSortType(String sortType) {
        tradePointMvpView.selectNewSortType(sortType);
    }

}
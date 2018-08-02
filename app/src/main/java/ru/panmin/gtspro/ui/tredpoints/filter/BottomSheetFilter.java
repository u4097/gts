package ru.panmin.gtspro.ui.tredpoints.filter;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.base.BottomSheetFragment;

public class BottomSheetFilter extends BottomSheetFragment implements BottomSheetFilterMvpView {

    @Inject
    BottomSheetFilterPresenter presenter;
    @BindView(R.id.close_textView)
    AppCompatTextView close;
    @BindView(R.id.radio_group_filter)
    RadioGroup radioGroup;

    @SuppressLint("ValidFragment")
    private BottomSheetFilter() {
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
        initBottom();
        initRadioGroup();
    }

    private void initRadioGroup() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbByVisitTime:
                        showToast("По времени посещения");
                    case R.id.rbByDistance:
                        showToast("По дистанции");
                    case R.id.rbAlphabetically:
                        showToast("По алфавиту");
                }
            }
        });
    }

    private void showToast(String teg) {
        Toast.makeText(getActivity(), teg, Toast.LENGTH_SHORT).show();
    }


    private void initBottom() {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeBottomShet();
            }
        });
    }

    private void closeBottomShet() {
        dismiss();
    }

    @Override
    protected void detachView() {
        presenter.detachView();
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

}
package ru.panmin.gtspro.ui.progress;

import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ContentFrameLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.ui.base.BaseActivity;

public abstract class ProgressActivity extends BaseActivity implements ProgressMvpView {

    protected ContentFrameLayout viewContent;

    @Nullable
    @BindView(R.id.viewFlipperState)
    protected ViewFlipper viewFlipperState;

    @Nullable
    @BindView(R.id.imageEmpty)
    protected ImageView imageEmpty;

    @Nullable
    @BindView(R.id.textEmptyTitle)
    protected AppCompatTextView textEmptyTitle;

    @Nullable
    @BindView(R.id.textEmptyDescription)
    protected AppCompatTextView textEmptyDescription;

    @Nullable
    @BindView(R.id.buttonEmpty)
    protected AppCompatButton buttonEmpty;

    @Nullable
    @BindView(R.id.imageError)
    protected ImageView imageError;

    @Nullable
    @BindView(R.id.textErrorTitle)
    protected AppCompatTextView textErrorTitle;

    @Nullable
    @BindView(R.id.textErrorDescription)
    protected AppCompatTextView textErrorDescription;

    @Nullable
    @BindView(R.id.buttonError)
    protected AppCompatButton buttonError;

    @Override
    protected int getLayout() {
        return R.layout.activity_progress;
    }

    @Override
    protected void inflateView() {
        viewContent = findViewById(R.id.viewContent);
        if (viewContent != null) {
            @LayoutRes int layoutRes = getDataView();
            if (layoutRes != 0) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                if (layoutInflater != null) {
                    viewContent.addView(layoutInflater.inflate(layoutRes, null));
                }
            }
        }
    }

    @LayoutRes
    protected abstract int getDataView();

    protected abstract EmptyBundle getEmptyBundle();

    protected abstract void emptyButtonClick();

    protected abstract void errorButtonClick();

    public void initStateEmpty(EmptyBundle emptyBundle) {
        if (emptyBundle != null) {
            if (imageEmpty != null) {
                if (emptyBundle.getImage() == null) {
                    imageEmpty.setVisibility(View.GONE);
                } else {
                    imageEmpty.setImageResource(emptyBundle.getImage());
                    imageEmpty.setVisibility(View.VISIBLE);
                }
            }

            if (textEmptyTitle != null) {
                if (TextUtils.isEmpty(emptyBundle.getTextTitle())) {
                    textEmptyTitle.setVisibility(View.GONE);
                } else {
                    textEmptyTitle.setText(emptyBundle.getTextTitle());
                    textEmptyTitle.setVisibility(View.VISIBLE);
                }
            }

            if (textEmptyDescription != null) {
                if (TextUtils.isEmpty(emptyBundle.getTextDescription())) {
                    textEmptyDescription.setVisibility(View.GONE);
                } else {
                    textEmptyDescription.setText(emptyBundle.getTextDescription());
                    textEmptyDescription.setVisibility(View.VISIBLE);
                }
            }

            if (buttonEmpty != null) {
                if (TextUtils.isEmpty(emptyBundle.getTextButton())) {
                    buttonEmpty.setVisibility(View.GONE);
                    buttonEmpty.setOnClickListener(null);
                } else {
                    buttonEmpty.setText(emptyBundle.getTextButton());
                    buttonEmpty.setOnClickListener(v -> emptyButtonClick());
                    buttonEmpty.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void initStateEmpty() {
        initStateEmpty(getEmptyBundle());
    }

    @Override
    public void setStateLoading() {
        if (viewFlipperState != null && viewFlipperState.getDisplayedChild() != ProgressMvpView.STATE_LOADING) {
            viewFlipperState.setDisplayedChild(ProgressMvpView.STATE_LOADING);
        }
    }

    @Override
    public void setStateData() {
        if (viewFlipperState != null && viewFlipperState.getDisplayedChild() != ProgressMvpView.STATE_DATA) {
            viewFlipperState.setDisplayedChild(ProgressMvpView.STATE_DATA);
        }
    }

    @Override
    public void setStateEmpty() {
        initStateEmpty();
        if (viewFlipperState != null && viewFlipperState.getDisplayedChild() != ProgressMvpView.STATE_EMPTY) {
            viewFlipperState.setDisplayedChild(ProgressMvpView.STATE_EMPTY);
        }
    }

    @Override
    public void setStateInternetError() {
        setStateError(
                R.mipmap.ic_launcher,
                getString(R.string.internet_error_title),
                getString(R.string.internet_error_message),
                getString(R.string.update)
        );
    }

    @Override
    public void setStateUnknownServerError() {
        setStateError(getString(R.string.unknown_server_error_title));
    }

    @Override
    public void setStateError(String textDescription) {
        setStateError(
                R.mipmap.ic_launcher,
                getString(R.string.error),
                textDescription,
                getString(R.string.update)
        );
    }

    @Override
    public void setStateError(@DrawableRes int image, String textTitle, String textDescription) {
        setStateError(image, textTitle, textDescription, null);
    }

    @Override
    public void setStateError(@DrawableRes int image, String textTitle, String textDescription, String textButton) {
        if (viewFlipperState != null && viewFlipperState.getDisplayedChild() != ProgressMvpView.STATE_ERROR) {
            viewFlipperState.setDisplayedChild(ProgressMvpView.STATE_ERROR);
        }
        if (imageError != null) {
            imageError.setImageResource(image);
        }
        if (textErrorTitle != null) {
            textErrorTitle.setText(textTitle);
        }
        if (textErrorDescription != null) {
            textErrorDescription.setText(textDescription);
        }
        if (buttonError != null) {
            if (!TextUtils.isEmpty(textButton)) {
                buttonError.setText(textButton);
                buttonError.setOnClickListener(v -> errorButtonClick());
                buttonError.setVisibility(View.VISIBLE);
            } else {
                buttonError.setOnClickListener(null);
                buttonError.setVisibility(View.GONE);
            }
        }
    }

    public int getCurrentState() {
        if (viewFlipperState != null) {
            return viewFlipperState.getDisplayedChild();
        } else {
            throw new IllegalStateException("viewFlipperState is null");
        }
    }

}
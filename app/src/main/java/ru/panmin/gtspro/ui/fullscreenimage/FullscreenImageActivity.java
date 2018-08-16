package ru.panmin.gtspro.ui.fullscreenimage;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Photo;
import ru.panmin.gtspro.ui.base.BaseActivity;
import ru.panmin.gtspro.ui.customviews.HackyViewPager;

public class FullscreenImageActivity extends BaseActivity implements FullscreenImageMvpView {

    @Inject FullscreenImagePresenter fullscreenImagePresenter;

    @BindView(R.id.viewPagerFullScreen) HackyViewPager viewPagerFullScreen;

    private static final String INTENT_KEY_ANSWER_ID = "answer.id";
    private static final String INTENT_KEY_START_POSITION = "start.position";

    private FullScreenImageAdapter fullScreenImageAdapter;

    public FullscreenImageActivity() {
    }

    public static Intent getStartIntent(Context context, String answerId, int startPosition) {
        Intent intent = new Intent(context, FullscreenImageActivity.class);
        intent.putExtra(INTENT_KEY_ANSWER_ID, answerId);
        intent.putExtra(INTENT_KEY_START_POSITION, startPosition);
        return intent;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_full_screen_image;
    }

    @Override
    protected void attachView() {
        fullscreenImagePresenter.attachView(this);
    }

    @Override
    protected void initViews() {
        fullScreenImageAdapter = new FullScreenImageAdapter();
        fullscreenImagePresenter.getAnswerById(getIntent().getStringExtra(INTENT_KEY_ANSWER_ID));
    }

    @Override
    protected void detachView() {
        fullscreenImagePresenter.detachView();
    }

    @Override
    public void setData(List<Photo> photos) {
        int startPosition = getIntent().getIntExtra(INTENT_KEY_START_POSITION, 0);
        fullScreenImageAdapter.setData(photos);
        int limit = fullScreenImageAdapter.getCount();

        viewPagerFullScreen.setOffscreenPageLimit(limit - 1);
        viewPagerFullScreen.setAdapter(fullScreenImageAdapter);
        viewPagerFullScreen.setCurrentItem(startPosition);

        viewPagerFullScreen.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }

}
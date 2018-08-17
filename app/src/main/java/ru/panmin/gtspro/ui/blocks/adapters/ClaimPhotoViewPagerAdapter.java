package ru.panmin.gtspro.ui.blocks.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import ru.panmin.gtspro.R;

public class ClaimPhotoViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<PhotoSliderHelper> photoList;
    private LayoutInflater inflater;

    public ClaimPhotoViewPagerAdapter(Context context, List<PhotoSliderHelper> list) {
        mContext = context;
        photoList = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ViewGroup imageLayout = (ViewGroup) inflater.inflate(R.layout.li_claim_photo_slider, collection, false);
        ((ImageView) imageLayout.findViewById(R.id.ivPhoto)).setImageResource(photoList.get(position).getResId());
        collection.addView(imageLayout);
        return imageLayout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}

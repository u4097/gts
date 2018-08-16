package ru.panmin.gtspro.ui.fullscreenimage;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

import ru.panmin.gtspro.R;
import ru.panmin.gtspro.data.models.Photo;

public class FullScreenImageAdapter extends PagerAdapter {

    private List<Photo> photos = new ArrayList<>();

    @Override
    public int getCount() {
        return (photos == null || photos.isEmpty()) ? 0 : photos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_fullscreen_image, container, false);
        PhotoView imageViewDisplay = view.findViewById(R.id.imageViewDisplay);
        Glide.with(container.getContext())
                .load(getItem(position).getUrl())
                .into(imageViewDisplay);
        container.addView(view, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        return view;
    }

    private Photo getItem(int position) {
        return photos.get(position);
    }

    public void setData(List<Photo> photos) {
        this.photos.clear();
        this.photos.addAll(photos);
        notifyDataSetChanged();
    }

}
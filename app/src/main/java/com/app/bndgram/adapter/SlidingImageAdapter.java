package com.app.bndgram.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.bndgram.R;
import com.app.bndgram.models.Image;
import com.app.bndgram.serviceCaller.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;
/**
 * Created by farzad on 3/3/18.
 */

public class SlidingImageAdapter extends PagerAdapter {


    Context context;
    List<Image> imageList;
    LayoutInflater inflater;
    public SlidingImageAdapter(Context context, List<Image> images){
        imageList=images;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }


    @Override
    public Object instantiateItem(ViewGroup view, final int position) {

        View pagerLayout=inflater.inflate(R.layout.layout_sliding,null);

        Image application=imageList.get(position);


        ImageView img_announcements=(ImageView)pagerLayout.findViewById(R.id.img_announcements);

        Picasso.get().load(ApiClient.BASEURL_IMAGES +application.getImg()).into(img_announcements);

        view.addView(pagerLayout,0);



        return pagerLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}

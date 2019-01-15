package com.app.bndgram.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.bndgram.R;
import com.app.bndgram.models.Image;
import com.app.bndgram.models.Posts;
import com.app.bndgram.serviceCaller.ApiClient;
import com.app.bndlibrary.ui.CircleTransform;
import com.app.bndlibrary.utils.Logger;
import com.app.bndlibrary.view.DroidTextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {


    List<Posts> postsList;
    Context context;

    public PostAdapter(Context context, List<Posts> posts) {
        postsList = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_row, null);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int i) {

        Posts posts = postsList.get(i);

        holder.txt_title.setText(posts.getUsername());

        Picasso.get().load(ApiClient.BASEURL_IMAGES+posts.getImageProfile())
                .transform(new CircleTransform())
                .into(holder.img_profile);

        String image = posts.getImages();

        Gson gson = new Gson();

        TypeToken<List<Image>> token = new TypeToken<List<Image>>() {};

        List<Image> imageList = gson.fromJson(image, token.getType());

        SlidingImageAdapter imageAdapter = new SlidingImageAdapter(context , imageList);
        holder.pager.setAdapter(imageAdapter);
        holder.indicator.setViewPager(holder.pager);

        Logger.Log("","");




    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.txt_title)
        DroidTextView txt_title;

        @BindView(R.id.img_profile)
        AppCompatImageView img_profile;

        @BindView(R.id.pager)
        ViewPager pager;

        @BindView(R.id.indicator)
        CirclePageIndicator indicator;


        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void addAll(List<Posts> posts){

        postsList.clear();
        postsList.addAll(posts);

        notifyDataSetChanged();

    }





}

package com.abed.rebtel.view.fragment;

/**
 * Created by mindvalley on 23/05/2016.
 */


import android.app.Fragment;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abed.rebtel.R;
import com.abed.rebtel.controller.SVG.SvgDecoder;
import com.abed.rebtel.controller.SVG.SvgDrawableTranscoder;
import com.abed.rebtel.controller.SVG.SvgSoftwareLayerSetter;
import com.abed.rebtel.model.Country;
import com.abed.rebtel.view.activity.CountryDetailsActivity;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;



public class CountryDetailsFragment extends Fragment {
    private Country country;

    @Bind(R.id.txt_area)
    TextView txt_area;

    @Bind(R.id.txt_region)
    TextView txt_region;

    @Bind(R.id.txt_sub_region)
    TextView txt_sub_region;

    @Bind(R.id.txt_capital)
    TextView txt_capital;

    @Bind(R.id.txt_name)
    TextView txt_name;

    @Bind(R.id.backdrop)
    ImageView backdropImg;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_country_details, container, false);
        ButterKnife.bind(this, rootView);
        country = getActivity().getIntent().getExtras().getParcelable(CountryDetailsActivity.COUNTRY);
        if (country != null) {
            loadBackdrop();
            collapsingToolbar.setTitle(country.name);
            txt_name.setText(country.name);
            txt_capital.setText(country.capital);
            txt_region.setText("Region: "+country.region);
            txt_sub_region.setText("Subregion: "+country.subregion);
            txt_area.setText("area: "+country.area);
        }
        return rootView;
    }

    private void loadBackdrop() {
        String img_url = "https://rawgit.com/hjnilsson/country-flags/master/svg/" + country.getAltSpellings().get(0).toLowerCase() + ".svg";

        GenericRequestBuilder requestBuilder = Glide.with(getActivity())
                .using(Glide.buildStreamModelLoader(Uri.class, getActivity()), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<>());

        Uri uri = Uri.parse(img_url);
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                // SVG cannot be serialized so it's not worth to cache it
                .load(uri)
                .into(backdropImg);

    }


}
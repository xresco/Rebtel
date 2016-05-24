package com.abed.rebtel.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abed.rebtel.R;
import com.abed.rebtel.model.Country;

import java.util.List;


public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CustomViewHolder> {
    private List<Country> countries;
    private ViewHolderClicks clicksListener;

    public CountriesAdapter(List<Country> countries,
                            ViewHolderClicks clicksListener) {
        this.countries = countries;
        this.clicksListener = clicksListener;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false);
        return new CustomViewHolder(view, clicksListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if (countries == null || countries.size() == 0) {
            return;
        }
        holder.setCountry(countries.get(position));

    }

    @Override
    public int getItemCount() {
        if (countries == null) {
            return 0;
        }

        return countries.size();
    }

    public void updateList(List<Country> itemsList) {
        this.countries = itemsList;
        notifyDataSetChanged();
    }

    public void addToList(List<Country> itemsList) {
        this.countries.addAll(itemsList);
        notifyDataSetChanged();
    }

    public void clear() {
        this.countries.clear();
        notifyDataSetChanged();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView txt_body;
        ViewHolderClicks clicksListener;
        Country Country;

        public CustomViewHolder(View view, ViewHolderClicks clicksListener) {
            super(view);
            this.clicksListener = clicksListener;
            txt_body = (TextView) view.findViewById(R.id.txt_body);
            view.setOnClickListener(this);
        }


        public void setCountry(Country Country) {
            this.Country = Country;
            txt_body.setText(Country.getName());
        }

        @Override
        public void onClick(View v) {
            if (clicksListener != null)
                this.clicksListener.onStorySelected(v, getLayoutPosition(), Country);
        }
    }


    public interface ViewHolderClicks {
        void onStorySelected(View view, int position, Country Country);
    }
}

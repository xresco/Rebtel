package com.abed.rebtel.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.abed.rebtel.R;
import com.abed.rebtel.controller.API_Utils;
import com.abed.rebtel.controller.RxBus;
import com.abed.rebtel.model.Country;
import com.abed.rebtel.model.eventBuses.CountriesLoadedEvent;
import com.abed.rebtel.view.adapter.CountriesAdapter;
import com.abed.rebtel.view.misc.VerticalEqualSpaceItemDecoration;

import java.util.LinkedList;
import java.util.List;

import rx.Subscription;

/**
 * The main activity where all the countries are listed
 */
public class MainActivity extends AppCompatActivity implements CountriesAdapter.ViewHolderClicks {
    private final String TAG = getClass().getName();

    private Subscription storiesLoadingBusSubscription;
    private List<Country> countries;


    private CountriesAdapter countriesAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadViews();
        initRx();
        API_Utils.loadCountries();
    }

    private void loadViews() {
        countriesAdapter = new CountriesAdapter(new LinkedList<>(), this);
        recyclerView = (RecyclerView) findViewById(R.id.rvItems);
        recyclerView.setAdapter(countriesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalEqualSpaceItemDecoration(2));


        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(() -> {
            countriesAdapter.clear();
            API_Utils.loadCountries();
        });

        swipeContainer.post(() -> swipeContainer.setRefreshing(true));


    }

    private void initRx() {
        storiesLoadingBusSubscription = RxBus.getInstance()
                .register(CountriesLoadedEvent.class,
                        event -> {
                            countries = event.getCountries();
                            countriesAdapter.updateList(countries);
                            swipeContainer.post(() -> swipeContainer.setRefreshing(false));

                        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        storiesLoadingBusSubscription.unsubscribe();
    }

    @Override
    public void onStorySelected(View view, int position, Country country) {
        CountryDetailsActivity.startActivity(this,country);
    }
}

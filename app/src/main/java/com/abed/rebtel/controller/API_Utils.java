package com.abed.rebtel.controller;


import com.abed.rebtel.BuildConfig;
import com.abed.rebtel.model.Country;
import com.abed.rebtel.model.eventBuses.CountriesLoadedEvent;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Utility class where all the API calls can be place
 * It makes it easier when any amendments on the APIs are needed
 */

public class API_Utils {
    private String TAG = getClass().getName();

    /**
     * Call the api that loads the countries and triggers a bus event to notifiy the UI
     */
    public static void loadCountries() {
        new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_BASE_URL)
                .build()
                .create(CountriesService.class)
                .getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Country>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof SocketTimeoutException) {
                            loadCountries();
                        }
                    }

                    @Override
                    public void onNext(List<Country> countries) {
                        RxBus.getInstance().postOnTheUiThread(new CountriesLoadedEvent(countries));
                    }
                });
    }


}
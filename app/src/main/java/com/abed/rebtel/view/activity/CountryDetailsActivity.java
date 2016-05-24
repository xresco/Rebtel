package com.abed.rebtel.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abed.rebtel.R;
import com.abed.rebtel.model.Country;

public class CountryDetailsActivity extends AppCompatActivity {

    public static final String COUNTRY = "com.abed.rebtel.view.activity.country";

    public static void startActivity(Context context, Country country) {
        Intent intent = new Intent(context, CountryDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(COUNTRY, country);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

}


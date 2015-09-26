package com.stockwaage.android;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stockwaage.android.dto.WeightDto;
import com.stockwaage.android.util.ToastHelper;
import com.stockwaage.android.util.rest.RestHelper;
import com.stockwaage.android.util.rest.StockwaageService;

public class MainActivity extends Activity {

    private final static String TAG = MainActivity.class.getName();

    private Context context;

    private TextView txtWeight;
    private Button btnRefresh;

    private double currentWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = this;

        txtWeight = (TextView) findViewById(R.id.txtWeight);

        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveCurrentWeightFromBackend();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        retrieveCurrentWeightFromBackend();
    }

    private void refreshWeightView() {
        txtWeight.setText(String.valueOf(currentWeight) + "kg");
    }

    private void retrieveCurrentWeightFromBackend() {
        new AsyncTask<Void, Void, WeightDto>() {

            @Override
            protected WeightDto doInBackground(Void... input) {
                Log.i(TAG, "Requesting current weight");

                StockwaageService stockwaageService = RestHelper.createOnCallRestAdapter().create(StockwaageService.class);
                try {
                    return stockwaageService.getWeight();
                }
                catch (Exception exc) {
                    Log.e(TAG, "Unable to retrieve current weight.", exc);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(WeightDto weight) {
                if (weight == null) {
                    ToastHelper.makeToast(context, "Could not retrieve weight.");
                    return;
                }

                currentWeight = weight.getValue();
                refreshWeightView();
            }
        }.execute();
    }
}

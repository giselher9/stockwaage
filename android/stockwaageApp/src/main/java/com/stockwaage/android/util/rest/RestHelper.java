package com.stockwaage.android.util.rest;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import android.util.Base64;

public class RestHelper {

    private static RequestInterceptor requestInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            String basicAuthCredentials = "u:p";
            request.addHeader("Authorization", "Basic " + Base64.encodeToString(basicAuthCredentials.getBytes(), Base64.NO_WRAP));
        }
    };

    public static RestAdapter createOnCallRestAdapter() {
        RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint("http://rpi207:9000/api/rest/")
//            .setClient(new OkClient(MySslTrust.trustcert(null)))
            .setRequestInterceptor(requestInterceptor)
            .build();

        return restAdapter;
    }
}

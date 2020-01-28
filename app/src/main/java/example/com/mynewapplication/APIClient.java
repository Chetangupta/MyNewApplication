package example.com.mynewapplication;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static final String URL_BASE = "https://restcountries.eu/rest/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(Context context) {
        if (retrofit == null) {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }


}

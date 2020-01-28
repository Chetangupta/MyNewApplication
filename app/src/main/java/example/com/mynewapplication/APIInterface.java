package example.com.mynewapplication;

import java.util.List;

import example.com.mynewapplication.model.Country;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("v2/all")
    Call<List<Country>> getData();

}

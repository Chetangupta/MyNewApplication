package example.com.mynewapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import example.com.mynewapplication.model.Country;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();


    }

    private void init() {
        progressDialog = (ProgressBar) findViewById(R.id.progress_circular);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
    }

    private void getData() {
        APIInterface apiRequest = APIClient.getRetrofitInstance(DetailActivity.this).create(APIInterface.class);

        Call<List<Country>> call = apiRequest.getData();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                progressDialog.setVisibility(View.GONE);
                DetailAdapter detailAdapter = new DetailAdapter(response.body());
                recyclerView.setAdapter(detailAdapter);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                progressDialog.setVisibility(View.GONE);
                Toast.makeText(DetailActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }


}

package josim74.github.com.retrofitjson;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import josim74.github.com.retrofitjson.adapter.DataAdapter;
import josim74.github.com.retrofitjson.model.Item;
import josim74.github.com.retrofitjson.model.RootObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ArrayList<Item> items;
    private DataAdapter adapter;
    ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize views...
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Please wait...");
        mDialog.show();

        //initialize retrofit...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/services/feeds/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Api call...
        ApiService apiService = retrofit.create(ApiService.class);
        Call<RootObject> call = apiService.getData();

        call.enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                Log.d(TAG, "onResponse: Server Response: "+response.toString());
                Log.d(TAG, "onResponse: Received Information: "+response.body().toString());

                mDialog.dismiss();

                items = response.body().getItems();

                //instantiate recyclerview...
                adapter = new DataAdapter(items, MainActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<RootObject> call, Throwable t) {
                Log.d(TAG, "onFailure: Something went wrong: "+t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package android.myapplication.mywallpaper;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<srcClass> arraylist;
    private RecyclerView recyclerview;
    Adapter adapter;
    CardView mSunset;
    CardView mNature;
    CardView mSpace;
    CardView mCars;
    EditText edittext;
    ImageButton img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerview= findViewById(R.id.recyclerview);
        mNature=findViewById(R.id.cardview1);
        mSpace=findViewById(R.id.cardview2);
        mSunset=findViewById(R.id.cardview3);
        mCars=findViewById(R.id.cardview4);
        edittext = findViewById(R.id.edittext);
        img = findViewById(R.id.search);
        arraylist = new ArrayList<>();
        recyclerview.setLayoutManager(new GridLayoutManager(this,2));
        recyclerview.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(), arraylist);
        recyclerview.setAdapter(adapter);
        findphotos();
        mSunset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "Sunset";
                getSearchimage(query);
            }
        });
        mNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "Nature";
                getSearchimage(query);
            }
        });
        mSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "Space";
                getSearchimage(query);
            }
        });
        mCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "Cars";
                getSearchimage(query);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = edittext.getText().toString().trim().toLowerCase();
                if(query.isEmpty()){

                    Toast.makeText(getApplicationContext(), "Please Enter something", Toast.LENGTH_SHORT).show();
                }
                else{
                    getSearchimage(query);
                }
            }
        });



    }

    private void getSearchimage(String query) {
        APIutilities.getAPIinterface().getImage(query,1,80).enqueue(new Callback<photosClass>() {
            @Override
            public void onResponse(Call<photosClass> call, Response<photosClass> response) {
                arraylist.clear();
                if(response.isSuccessful()){
                    arraylist.addAll(response.body().getPhotos());
                    recyclerview.getRecycledViewPool().clear();
                    adapter.notifyDataSetChanged();
                }
                else{
                    Log.d(TAG, Integer.toString(response.code()));
                    Toast.makeText(getApplicationContext(), "Couldn't get", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<photosClass> call, Throwable t) {

            }
        });
    }

    private void findphotos() {
        APIutilities.getAPIinterface().getImage(1,80).enqueue(new Callback<photosClass>() {
            @Override
            public void onResponse(Call<photosClass> call, Response<photosClass> response) {
                arraylist.clear();
                if(response.isSuccessful()){
                    arraylist.addAll(response.body().getPhotos());
                    recyclerview.getRecycledViewPool().clear();
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Couldn't get", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<photosClass> call, Throwable t) {

            }
        });
    }
}
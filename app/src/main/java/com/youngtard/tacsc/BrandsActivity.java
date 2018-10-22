package com.youngtard.tacsc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.youngtard.tacsc.adapter.BrandsRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrandsActivity extends AppCompatActivity {
    private RecyclerView brandsRecyclerView;
    private BrandsRecyclerAdapter brandsRecyclerAdapter;



//    //Put into string resource?
//    String[] brandsArray = new String[]{"Panasonic", "LG", "Sony", "Playstation"};
//    ArrayList<String> brands = new ArrayList<>(Arrays.asList(brandsArray)) ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);

            //Put into string resource?
        String[] brandsArray = new String[]{"LG", "Samsung", "Haier Thermocool", "Panasonic", "Sharp", "Chigo", "Hisense", "Scanfrost", "Polystar",
                                            "Midea", "Newclime", "Airflow", "Kingsmen", "Whirlpool", "Amana", "General Electric (GE)", "Other..." };
//        ArrayList<String> brands = new ArrayList<>(Arrays.asList(brandsArray)) ;

        ArrayList<String> brands = new ArrayList<>(Arrays.asList(brandsArray)) ;

        brandsRecyclerView = findViewById(R.id.rv_brands);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        brandsRecyclerAdapter = new BrandsRecyclerAdapter(this, brands);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        brandsRecyclerView.addItemDecoration((dividerItemDecoration));



        brandsRecyclerView.setAdapter(brandsRecyclerAdapter);
        brandsRecyclerView.setLayoutManager(layoutManager);
    }
}

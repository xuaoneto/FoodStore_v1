package com.example.foodstore;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.foodstore.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = new Intent(MainActivity.this, TelaInicialActivity.class);
        startActivity(intent);




        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ProgressBar loading = findViewById(R.id.loading);
        loading.setVisibility(View.INVISIBLE);

        //SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        //ViewPager viewPager = findViewById(R.id.view_pager);
        //viewPager.setAdapter(sectionsPagerAdapter);
        //TabLayout tabs = binding.tabs;
        //tabs.setupWithViewPager(viewPager);


        FloatingActionButton fab = binding.fab;
        Intent secondScreen = new Intent(this, CartActivity.class);

        TextView textView = findViewById(R.id.texto);
        ImageView imageView = findViewById(R.id.jsonImage);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://ig-food-menus.herokuapp.com/burgers";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Burger> burgerArray = new ArrayList();
                        if (response != null) {
                            for (int i = 0; i < response.length(); i++){
                                try {
                                    JSONObject currItem = response.getJSONObject(i);
                                    Burger currentBurger = new Burger(
                                            currItem.optString("id"),
                                            currItem.optString("name"),
                                            currItem.optInt("price"),
                                            currItem.optInt("rate"),
                                            currItem.optString("dsc"),
                                            currItem.optString("img"),
                                            currItem.optString("country")
                                            );
                                    burgerArray.add(currentBurger);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        Burger burger = burgerArray.get(0);

                        textView.setText(burger.getCountry()+"/n "
                                        +burger.getName()+"/n "
                                        +burger.getDsc()+"/n "
                                        +burger.getPrice());

                        imageView.setImg();burger.getImg();

                        loading.setVisibility(View.INVISIBLE);
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Deu ruim!");
                        loading.setVisibility(View.INVISIBLE);
                    }
                });



        FloatingActionButton botaoteste = findViewById(R.id.botaoPteste);
        botaoteste.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                queue.add(jsonObjectRequest);
                loading.setVisibility(View.VISIBLE);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(secondScreen);
            }
        });
    }

}
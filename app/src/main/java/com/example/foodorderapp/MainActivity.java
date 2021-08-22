package com.example.foodorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodorderapp.Adapters.MainAdapter;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //code without use of findViewById to get rid of many different id names to be remainded.
    //changes to be made in build gradle Module(line 17)
    ActivityMainBinding binding ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //advantage here is to use a single recyclerView.
        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger ,"Burger" , "5", "Burger With Extra Cheese"));
        list.add(new MainModel(R.drawable.pizza ,"Pizza" , "0", "The Offer to Download the coupons ends Thursday Aug 15"));
        list.add(new MainModel(R.drawable.portobello_mushroom ,"Portobello Mushroom" , "12", "Meaty portbello mushrooms make the perfect vegetarian burger!"));
        list.add(new MainModel(R.drawable.pizza_burger ,"Pizza Burger" , "10", "Special Pizza Burger available to satiate your hunger"));
        list.add(new MainModel(R.drawable.chicken_burger ,"Chicken Burger" , "5", "Chicken Burger With Extra Cheese"));

        MainAdapter adapter = new MainAdapter(list , this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // order menu "MY ORDERS"

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrdersActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
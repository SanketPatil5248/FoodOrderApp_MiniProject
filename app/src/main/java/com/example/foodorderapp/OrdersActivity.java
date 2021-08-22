package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.foodorderapp.Adapters.OrdersAdapter;
import com.example.foodorderapp.Models.OrdersModel;
import com.example.foodorderapp.databinding.ActivityOrderBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list = helper.getOrders();
        /*list.add((new OrdersModel(R.drawable.chicken_burger, "Chicken Burger", "10", "123445354")));
        list.add((new OrdersModel(R.drawable.pizza, "Pizza", "4", "993432554")));
        list.add((new OrdersModel(R.drawable.pizza_burger, "Pizza Burger", "6", "123456554")));
        list.add((new OrdersModel(R.drawable.portobello_mushroom, "Portobello Mushroom", "9", "123433454")));
        list.add((new OrdersModel(R.drawable.french_fries, "French Fries", "4", "127682554")));
        list.add((new OrdersModel(R.drawable.fresh_veggie_spring_rolls, "Fresh Veggie Spring Rolls", "4", "175432554")));
        list.add((new OrdersModel(R.drawable.fried_chicken, "Fried Chicken", "6", "123488554")));
        list.add((new OrdersModel(R.drawable.shahi_paneer, "Shahi Paneer", "3", "123432444")));
        list.add((new OrdersModel(R.drawable.misal_pav, "Misal Pav", "5", "123432675")));
         */

        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.ordersRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.ordersRecyclerView.setLayoutManager(layoutManager);


    }
}
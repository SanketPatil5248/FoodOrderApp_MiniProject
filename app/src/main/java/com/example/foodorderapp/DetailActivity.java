package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodorderapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding; // binding used instead of findViewById function.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this); //calling DBHelper from MainActivity.

        if(getIntent().getIntExtra("type",0)==1){

        final int image = getIntent().getIntExtra("image", 0);// data get/fetch from main adapter
        final int price = Integer.parseInt(getIntent().getStringExtra("price"));
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("desc");

        binding.detailImage.setImageResource(image);
        binding.pricelbl.setText(String.format("%d", price));
        binding.namelbl.setText(name);
        binding.detailDescription.setText(description);



        binding.insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //insertOrder function from DBHelper.

                boolean isInserted = helper.insertOrder(
                        binding.nameBox.getText().toString(),
                        binding.phoneBox.getText().toString(),
                        price,
                        image,
                        name,
                        description,
                        Integer.parseInt(binding.quantity.getText().toString())
                );
                if (isInserted)
                    Toast.makeText(DetailActivity.this, "Data Success.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DetailActivity.this, "Error.", Toast.LENGTH_SHORT).show();

            }
        });
        } else{     // regarding update
            int id = getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getOrderById(id);
            int image = cursor.getInt(4);

            binding.detailImage.setImageResource(image);
            binding.pricelbl.setText(String.format("%d", cursor.getInt(3)));
            binding.namelbl.setText(cursor.getString(6));
            binding.detailDescription.setText(cursor.getString(5));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertBtn.setText("Update Now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    boolean isUpdated = helper.updateOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.pricelbl.getText().toString()),
                            image,
                            binding.detailDescription.getText().toString(),
                            binding.namelbl.getText().toString(),
                            1,
                            id
                            );

                    if(isUpdated )
                        Toast.makeText(DetailActivity.this, "Updated.", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Failed.", Toast.LENGTH_SHORT).show();
                }
            });

        }
        }

}
package com.example.foodorderapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.DetailActivity;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewholder>{

    ArrayList<MainModel> list ;
    Context context ;

    public MainAdapter(ArrayList<MainModel> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sample_mainfood , parent , false);
        return new viewholder(view);    //layout created or inflated
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, int position) {
    // to bide data here

        final MainModel model = list.get(position);
        holder.foodimage.setImageResource(model.getImage());
        holder.mainName.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.description.setText(model.getDescription());

        /*this block used to open detail activity or used for opening other UI i.e details UI
        //sending data from one activity to another activity.
        */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class); //changing page from , to using intent
                intent.putExtra("image", model.getImage()); // data sent from main activity to detail activity.
                intent.putExtra("price", model.getPrice());
                intent.putExtra("desc", model.getDescription());
                intent.putExtra("name", model.getName());
                intent.putExtra("type", 1);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size(); //here size of recyclerView is been showed, whatever adds to Arraylist will be the size of RecyclerView.
    }

    //here we use viewholder
    public class viewholder extends RecyclerView.ViewHolder {

        ImageView foodimage ;
        TextView mainName, price, description;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            foodimage = itemView.findViewById(R.id.imageView);
            mainName = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.orderPrice);
            description = itemView.findViewById(R.id.description);
        }
    }
}

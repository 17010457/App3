package com.example.derrick.app3.ViewHolder;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.derrick.app3.ItemClickListener;
import com.example.derrick.app3.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txtMenuName;
    public TextView txtMenuPrice;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);
        txtMenuName = itemView.findViewById(R.id.menu_name);
        txtMenuPrice = itemView.findViewById(R.id.menu_price);
        imageView = itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);

    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}

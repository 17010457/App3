package com.example.derrick.app3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.example.derrick.app3.Model.Catalog;
import com.example.derrick.app3.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class CatalogActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference catalog;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layManager;

    FirebaseRecyclerAdapter<Catalog, MenuViewHolder> adapter;

    String menuId ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        catalog = database.getReference("Catalog");

        recycler_menu = findViewById(R.id.recycler_menu);
        layManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layManager);

        if(getIntent() != null){
            menuId = getIntent().getStringExtra("id");
            if(!menuId.isEmpty() && menuId != null){
                loadListItem(menuId);
            }
        }
    }

    private void loadListItem(String menuId) {
        adapter = new FirebaseRecyclerAdapter<Catalog, MenuViewHolder>(Catalog.class, R.layout.catalog_item, MenuViewHolder.class, catalog.orderByChild("MenuId").equalTo(menuId)) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Catalog model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                viewHolder.txtMenuPrice.setText(model.getPrice());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);

                final Catalog clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Toast.makeText(CatalogActivity.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CatalogActivity.this,ItemDetail.class);
                        intent.putExtra("ItemId", adapter.getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }
        };
        recycler_menu.setAdapter(adapter);
    }
}

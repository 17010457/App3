package com.example.derrick.app3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.derrick.app3.Database.Database;
import com.example.derrick.app3.Model.Catalog;
import com.example.derrick.app3.Model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ItemDetail extends AppCompatActivity {

    TextView item_name, item_price, item_desc;
    EditText user_size, user_height, user_chest, user_shoulder, user_sleeve;
    ImageView item_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnfab;
    ElegantNumberButton numberButton;
    Catalog currentItem;

    String itemId = "";

    FirebaseDatabase database;
    DatabaseReference catalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        //Firebase
        database = FirebaseDatabase.getInstance();
        catalog = database.getReference("Catalog");

        //Init view
        numberButton = findViewById(R.id.number_button);
        btnfab = findViewById(R.id.btnCart);

        item_desc = findViewById(R.id.item_desc);
        item_name = findViewById(R.id.item_name);
        item_price = findViewById(R.id.item_price);
        item_image = findViewById(R.id.img_item_detail);

        user_size = findViewById(R.id.size);

        user_height = findViewById(R.id.height);
        user_chest = findViewById(R.id.chestlength);
        user_shoulder = findViewById(R.id.shoulderlength);
        user_sleeve = findViewById(R.id.sleevelength);

        // Store user's custom sizes
        String customSizes[] = {user_height.getText().toString(),
                user_chest.getText().toString(),
                user_shoulder.getText().toString(),
                user_sleeve.getText().toString()};

        collapsingToolbarLayout = findViewById(R.id.collapse);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapedAppbar);


        //Get Item Id from Intent
        if(getIntent() != null){
            itemId = getIntent().getStringExtra("ItemId");
            if (!itemId.isEmpty()){
                getDetailItem(itemId);
            }
        }

        btnfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        itemId,
                        currentItem.getName(),
                        numberButton.getNumber(),
                        currentItem.getPrice(),
                        currentItem.getDiscount()
                ));
                Toast.makeText(ItemDetail.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDetailItem(String itemId) {
        catalog.child(itemId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentItem = dataSnapshot.getValue(Catalog.class);

                //Set Image
                Picasso.with(getBaseContext()).load(currentItem.getImage()).into(item_image);
                //collapsingToolbarLayout.setTitle(item.getName()); - Looks ugly

                item_price.setText(currentItem.getPrice());
                item_name.setText(currentItem.getName());
                item_desc.setText(currentItem.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

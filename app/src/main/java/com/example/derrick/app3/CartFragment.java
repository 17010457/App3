package com.example.derrick.app3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.example.derrick.app3.Database.Database;
import com.example.derrick.app3.Model.Order;
import com.example.derrick.app3.Model.Request;
import com.example.derrick.app3.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layManager;

    FirebaseDatabase database;
    DatabaseReference requests;
    
    TextView txtTotalPrice;
    Button btnCheckout;

    List<Order> cart = new ArrayList<>();

    CartAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        final View v = inflater.inflate(R.layout.fragment_cart, container, false);

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        //Init
        recyclerView = v.findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layManager);
        
        txtTotalPrice = v.findViewById(R.id.total);
        btnCheckout = v.findViewById(R.id.checkOut);

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
        loadListFood();
        return v;
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Enter your address");

        final EditText edtAddress = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress);
        alertDialog.setIcon(R.drawable.ic_shopping_basket_black_24dp);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Request request = new Request(
                        edtAddress.getText().toString(), txtTotalPrice.getText().toString(),cart
                );
                requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);
                //Delete cart
                new Database(getActivity().getBaseContext()).cleanCart();
                Toast.makeText(getActivity(), "Thank you, Order Placed", Toast.LENGTH_SHORT).show();
                recyclerView.setVisibility(View.INVISIBLE);
                txtTotalPrice.setText("0.00");

            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }


    private void loadListFood() {
        cart = new Database(getActivity()).getCarts();
        adapter = new CartAdapter(cart,getActivity());
        recyclerView.setAdapter(adapter);

        //Calculate total price
        double total = 0;
        for(Order order:cart)
            total += (Double.parseDouble(order.getPrice()))*(Double.parseDouble(order.getQuantity()));
        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtTotalPrice.setText(fmt.format(total));
    }

}

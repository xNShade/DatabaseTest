package com.example.ryanx.databasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView idView;
    TextView itemID;
    EditText itemName;
    TextView itemShelf;
    TextView itemDesc;
    TextView itemQuantity;
    TextView itemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemID = findViewById(R.id.productID);
        itemName = findViewById(R.id.productName);
        itemShelf = findViewById(R.id.productShelf);
        itemDesc = findViewById(R.id.productDesc);
        itemQuantity = findViewById(R.id.productQuantity);
        itemPrice = findViewById(R.id.productPrice);
        idView = findViewById(R.id.txtView);
    }

    public void newProduct (View view) {
        DBHandler dbHandler = new DBHandler(this);

        int ID = Integer.parseInt(itemID.getText().toString());
        int shelf = Integer.parseInt(itemShelf.getText().toString());
        int quantity = Integer.parseInt(itemQuantity.getText().toString());
        double price = Double.parseDouble(itemPrice.getText().toString());
        //id name shelf storeDesc price quantity
        Store product = new Store(ID, itemName.getText().toString(),
                shelf, itemDesc.getText().toString(), price, quantity);

        dbHandler.addStore(product);
        itemID.setText("");
        itemShelf.setText("");
        itemQuantity.setText("");
        itemPrice.setText("");
        itemName.setText("");
        itemDesc.setText("");
    }

    public void lookupProduct (View view) {
        DBHandler dbHandler = new DBHandler(this);

        Store product = dbHandler.findProduct(itemName.getText().toString());

        if (product != null) {
            idView.setText(String.valueOf(product.getId()));
            System.out.printf(String.valueOf(product.getId()));
            itemID.setText(String.valueOf(product.getId()));
            itemShelf.setText(String.valueOf(product.getShelf()));
            itemQuantity.setText(String.valueOf(product.getQuantity()));
            itemPrice.setText(String.valueOf(product.getPrice()));
            itemName.setText(String.valueOf(product.getName()));
            itemDesc.setText(String.valueOf(product.getStoreDesc()));
        } else {
            idView.setText("No Match Found");
        }
    }

    public void removeProduct (View view) {
        DBHandler dbHandler = new DBHandler(this);

        boolean result = dbHandler.deleteProduct(itemName.getText().toString());

        if (result)
        {
            idView.setText("Record Deleted");
            itemID.setText("");
            itemShelf.setText("");
            itemQuantity.setText("");
            itemPrice.setText("");
            itemName.setText("");
            itemDesc.setText("");
        }
        else
            idView.setText("No Match Found");
    }
}

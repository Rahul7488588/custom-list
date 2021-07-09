package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        int price=calculatePrice();
        EditText editedText=(EditText)findViewById(R.id.edt1);
        String name=editedText.getText().toString();
        CheckBox whippedCreamCheckbox=(CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean addwhippedcream=whippedCreamCheckbox.isChecked();
        Log.v("MainActivity","It has the whipped cream"+addwhippedcream);
        CheckBox chocolateToppingCheckbox=(CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        boolean addchocolatetopping=chocolateToppingCheckbox.isChecked();
        //Log.v("MainActivity","It has the chocolate topping"+addchocolatetopping);
        String priceMessage=createOrderSummary(price,addwhippedcream,addchocolatetopping,name);

        Intent in=new Intent(Intent.ACTION_SENDTO);
        in.setData(Uri.parse("mailto:"));
        in.putExtra(in.EXTRA_TEXT,priceMessage);
        in.putExtra(in.EXTRA_SUBJECT,"Mujhe coffee chaiye");
        if(in.resolveActivity(getPackageManager())!=null){
            startActivity(in);
        }
    }
    private String createOrderSummary(int price,boolean addwhippedcream,boolean addchocolatetopping,String name){
       String priceMessage="Name:"+name;
        priceMessage+="\n whipped cream is added ?"+addwhippedcream;
        priceMessage+="\n chocolate topping is added ?"+addchocolatetopping;
        priceMessage+="\nQuantity "+quantity;
        priceMessage+="\nTotal $: "+price;
        priceMessage+="\nThankYou";
        return priceMessage;
    }
private int calculatePrice(){
    int price = quantity * 5;
    return price;
}
    public void increament(View view) {
        if(quantity==100){
            Toast.makeText(this,"100 coffee sey koi zyada nahi hai !!",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decreament(View view) {
        if(quantity==1){
            Toast.makeText(this,"1 coffee sey koi kam nahi hai !!",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}

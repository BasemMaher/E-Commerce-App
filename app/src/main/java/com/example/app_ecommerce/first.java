package com.example.app_ecommerce;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_ecommerce.adapter.productAdapter;
import com.example.app_ecommerce.model.Products;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.QRCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class first extends AppCompatActivity  {
    Animation top,butom;
    RecyclerView productCatRecycler, productItemrecycler;
    productAdapter productAdapter;
    Database db = new Database(this);
    EditText txt;
    ConstraintLayout val;
    public static EditText se;
    public static TextView loca_txt;
    private int requestCode;
    private int resultCode;
    private Intent data;
    private ImageView google_map;
    private String[] o_arr;
    private int  order_cou=0;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        TextView item_name=(TextView) findViewById(R.id.textView11);
        item_name.setText("Press to show ");
        o_arr=new String [100];
        top= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        butom= AnimationUtils.loadAnimation(this,R.anim.butom_anim);
        ConstraintLayout con2=(ConstraintLayout)findViewById(R.id.constraintLayout2);
        con2.setAnimation(top);
        ImageView Ima=(ImageView)findViewById(R.id.imageView2);
        Ima.setAnimation(top);
        TextView txt7=(TextView)findViewById(R.id.textView7);
        txt7.setAnimation(top);
        ConstraintLayout con1=(ConstraintLayout)findViewById(R.id.con);
        con1.setVisibility(View.VISIBLE);
        con1.setAnimation(top);
        String pro= "";
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        pro = preferences.getString("Name", "");
        TextView prof=(TextView)findViewById(R.id.textView6);
        prof.setText("Welcome "+pro+" ^_^");
        prof.setAnimation(top);
        loca_txt=(TextView) findViewById(R.id.textView10);
        se=(EditText)findViewById(R.id.editTextTextPersonName);
        google_map=(ImageView)findViewById(R.id.imageView17);
        val=(ConstraintLayout) findViewById(R.id.cst);
        val.setVisibility(View.INVISIBLE);
        ImageView add = (ImageView) findViewById(R.id.add);
        TextView phone = (TextView) findViewById(R.id.txt_phone);
        TextView game = (TextView) findViewById(R.id.txt_game);
        TextView lab = (TextView) findViewById(R.id.txt_labs);
        RecyclerView re = (RecyclerView) findViewById(R.id.product_recycler);
        txt=(EditText)findViewById(R.id.editTextTextPersonName);
        filldata();
        game_data();
        re.setAnimation(butom);
        game.setTextColor(R.color.teal_700);
        lab.setTextColor(R.color.black);
        phone.setTextColor(R.color.black);
        phone.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                re.removeAllViews();
                phone_data();
                re.setAnimation(butom);
                phone.setTextColor(R.color.teal_700);
                lab.setTextColor(R.color.black);
                game.setTextColor(R.color.black);
            }
        });
        game.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                re.removeAllViews();
                game_data();
                re.setAnimation(butom);
                game.setTextColor(R.color.teal_700);
                lab.setTextColor(R.color.black);
                phone.setTextColor(R.color.black);
            }
        });
        lab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                re.removeAllViews();
                lab_data();
                re.setAnimation(butom);
                lab.setTextColor(R.color.teal_700);
                game.setTextColor(R.color.black);
                phone.setTextColor(R.color.black);
            }
        });
    }
    private void setproductItemrecycle(List<Products> productsList) {
        productItemrecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productItemrecycler.setLayoutManager(layoutManager);
        productAdapter = new productAdapter(this, productsList);
        productItemrecycler.setAdapter(productAdapter);
    }

    public void filldata() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (!preferences.getBoolean("rep", false)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("rep", true);
            editor.apply();
            db.add_category("Lab", 1);
            db.add_category("Phone", 2);
            db.add_category("Game", 3);
            db.add_Item("samsung", "Samsung Galaxy S21 Altra 5G Ram 12 GB ", "256 GB", 20999, 2, R.drawable.phone2);
            db.add_Item("honer", "HonerX 9Pro Ram 6 Giga Byte Blue", "256 GB", 3699, 2, R.drawable.phone3);
            db.add_Item("samsung", "Samsung Galaxy  Altra 5G Ram 12 GB ", "256 GB", 20999, 2, R.drawable.phone2);
            db.add_Item("huwawi", "Huwawi Nova SE7 Ram 8 Giga Byte ", "128 GB", 4299, 2, R.drawable.phone1);
            db.add_Item("dell", "Dell Ram 4GB Intel Core i3-1005G1 Processor CPU Frequency: 3.40 GHz ", "One T", 6898, 1, R.drawable.lab1);
            db.add_Item("lenovo", "Lenovo 81W80083ED ideapad S145-15IIL Laptop - Intel Core i3-1005G1 4 GB RAM", "One T", 8498, 1, R.drawable.lab2);
            db.add_Item("asus", "Asus ROG Strix G G731GV-EV234T Laptop,Intel Core i7-9750H, 16 GB RAM, 6GB NVIDIA", "128 GB", 25999, 1, R.drawable.lab3);
            db.add_Item("spiderman", "Marvel Spiderman Video Game (PS4)", "4 Region", 429, 3, R.drawable.game1);
            db.add_Item("pes", "PES 2018 PRO EVOLUTION SOCCER PREMIUM EDITION WITH ARABIC", "2-Play \n Station4", 395, 3, R.drawable.game2);
            db.add_Item("needforspeed", "Need For Speed PayBack By EA ", "2-Play \n Station4", 534, 3, R.drawable.game3);
        }
    }

    public void phone_data() {
        RecyclerView sh=(RecyclerView)findViewById(R.id.product_recycler);
        sh.setVisibility(View.VISIBLE);
        ConstraintLayout co=(ConstraintLayout)findViewById(R.id.cst);
        co.setVisibility(View.INVISIBLE);
        List<Products> productsList = new ArrayList<>();
        Cursor cursor = db.getAllItems();
        do {
            if(cursor.getCount()<=0||cursor==null)break;
            String name, title,storage;
            Integer url, item_id,price, catid;
            name = cursor.getString(cursor.getColumnIndex("Name"));
            title = cursor.getString(cursor.getColumnIndex("Title"));
            storage = cursor.getString(cursor.getColumnIndex("Storage"));
            price = cursor.getInt(cursor.getColumnIndex("Price"));
            catid = cursor.getInt(cursor.getColumnIndex("Cat_ID"));
            item_id = cursor.getInt(cursor.getColumnIndex("ItemID"));
            url = cursor.getInt(cursor.getColumnIndex("url"));
            if (catid == 2)
                productsList.add(new Products(item_id, name, title, price, storage, url));
        } while (cursor.moveToNext());
        db.close();
        setproductItemrecycle(productsList);
    }

    public void game_data() {
        RecyclerView sh=(RecyclerView)findViewById(R.id.product_recycler);
        sh.setVisibility(View.VISIBLE);
        ConstraintLayout co=(ConstraintLayout)findViewById(R.id.cst);
        co.setVisibility(View.INVISIBLE);
        List<Products> productsList = new ArrayList<>();
        Cursor cursor = db.getAllItems();
        do {
            if(cursor.getCount()<=0||cursor==null)break;
            String name, title,storage;
            Integer url, item_id,price, catid;
            name = cursor.getString(cursor.getColumnIndex("Name"));
            title = cursor.getString(cursor.getColumnIndex("Title"));
            storage = cursor.getString(cursor.getColumnIndex("Storage"));
            price = cursor.getInt(cursor.getColumnIndex("Price"));
            catid = cursor.getInt(cursor.getColumnIndex("Cat_ID"));
            item_id = cursor.getInt(cursor.getColumnIndex("ItemID"));
            url = cursor.getInt(cursor.getColumnIndex("url"));
            if (catid == 3)
                productsList.add(new Products(item_id, name, title, price, storage, url));
        } while (cursor.moveToNext());
        db.close();
        setproductItemrecycle(productsList);
    }

    public void lab_data() {
        RecyclerView sh=(RecyclerView)findViewById(R.id.product_recycler);
        sh.setVisibility(View.VISIBLE);
        ConstraintLayout co=(ConstraintLayout)findViewById(R.id.cst);
        co.setVisibility(View.INVISIBLE);
        List<Products> productsList = new ArrayList<>();
        Cursor cursor = db.getAllItems();
        do {
            if(cursor.getCount()<=0||cursor==null)break;
            String name, title,storage;
            Integer url, item_id,price, catid;
            name = cursor.getString(cursor.getColumnIndex("Name"));
            title = cursor.getString(cursor.getColumnIndex("Title"));
            storage = cursor.getString(cursor.getColumnIndex("Storage"));
            price = cursor.getInt(cursor.getColumnIndex("Price"));
            catid = cursor.getInt(cursor.getColumnIndex("Cat_ID"));
            item_id = cursor.getInt(cursor.getColumnIndex("ItemID"));
            url = cursor.getInt(cursor.getColumnIndex("url"));
            if (catid == 1)
                productsList.add(new Products(item_id, name, title, price, storage, url));
        } while (cursor.moveToNext());
        db.close();
        setproductItemrecycle(productsList);
    }

    @SuppressLint("ResourceType")
    boolean a = false;

    public void Like(View view) {
        ImageView like = (ImageView) findViewById(R.id.like_image);
        if (!a) {
            like.setImageResource(R.drawable.heartq);
            a = true;
        } else {
            like.setImageResource(R.drawable.heart);
            a = false;
        }
    }


    public void Logout(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        Intent intent = new Intent(getApplicationContext(), Rigster_fragment.class);
        startActivity(intent);
        finish();
    }

    public void Search(View view) {
        EditText txt = (EditText) findViewById(R.id.editTextTextPersonName);
        if (txt.getText().toString().toLowerCase().equals("")) {
            Toast.makeText(this, "Enter name of itemmm !!!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        RecyclerView re = (RecyclerView) findViewById(R.id.product_recycler);
        Cursor cursor = db.getAllItems();
        List<Products> productsList = new ArrayList<>();
        int c = 0;
        do {
            if(cursor.getCount()<=0||cursor==null)break;
            String name, title, storage;
            Integer url, item_id, catid,price;
            name = cursor.getString(cursor.getColumnIndex("Name"));
            title = cursor.getString(cursor.getColumnIndex("Title"));
            storage = cursor.getString(cursor.getColumnIndex("Storage"));
            price = cursor.getInt(cursor.getColumnIndex("Price"));
            catid = cursor.getInt(cursor.getColumnIndex("Cat_ID"));
            item_id = cursor.getInt(cursor.getColumnIndex("ItemID"));
            url = cursor.getInt(cursor.getColumnIndex("url"));
            if (name.contains(txt.getText().toString().toLowerCase())) {
                c++;
                productsList.add(new Products(item_id, name, title, price, storage, url));
            }
        } while (cursor.moveToNext());
        db.close();
        if (c == 0) {
            Toast.makeText(this, "Item Not Found !!!!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        re.removeAllViews();
        setproductItemrecycle(productsList);
        txt.setText("");
    }
    public void Speach(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi Speak Some Thing To Search");
        try {
            startActivityForResult(intent, 1);
        }
        catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == -1 && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                txt.setText(result.get(0));
            }
        }
    }

    public void Plus(View view) {
        TextView txt=(TextView)findViewById(R.id.numitem);
        Integer num=Integer.parseInt(txt.getText().toString());
        num++;
        txt.setText(String.valueOf(num));
    }

    public void Mins(View view) {
       final TextView txt=(TextView)findViewById(R.id.numitem);
        Integer num=Integer.parseInt(txt.getText().toString());
        if(num>0)num--;
        txt.setText(String.valueOf(num));
    }

    public void GoDown(View view) {
        AutoCompleteTextView txt=(AutoCompleteTextView)findViewById(R.id.textView4);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,Items);
        txt.setAdapter(adapter);
        txt.showDropDown();

    }
private  static final String []Items=new String[]{"1","2","3","4","5","6","7","8","9","10"};

    @SuppressLint("ResourceAsColor")
    public void Order(View view) {
        RecyclerView sh=(RecyclerView)findViewById(R.id.product_recycler);
        sh.setVisibility(View.INVISIBLE);
        sh.setAnimation(butom);
        TextView ord=(TextView)findViewById(R.id.order);
        int ye=1;
        int c=ord.getCurrentTextColor();
        if(c==Color.BLACK)
            ye=0;
        if(ye==0){
            val.setEnabled(true);
            sh.setVisibility(View.INVISIBLE);
            sh.setAnimation(butom);
            val.setVisibility(View.VISIBLE);
            val.setAnimation(butom);
           ord.setTextColor(Color.RED);
        }
        else {
            val.setEnabled(false);
            sh.setVisibility(View.VISIBLE);
            sh.setAnimation(butom);
            ord.setTextColor(Color.BLACK);
            val.setVisibility(View.INVISIBLE);
        }
    }

    public void BarCode(View view) {
     startActivity(new Intent(getApplicationContext(),scanner_view.class));

    }
private  Integer pay=0;
    public void add_itemPrice(View view) {
        AutoCompleteTextView txt=(AutoCompleteTextView)findViewById(R.id.textView4);
        TextView number_item=(TextView)findViewById(R.id.numitem);
        TextView totalprice=(TextView)findViewById(R.id.textView8);
        Integer num_item=Integer.parseInt(number_item.getText().toString());
        if(txt.getText().toString().equals("")){
            Toast.makeText(this, "Please Choose Id!!!!", Toast.LENGTH_SHORT).show();
        }
        else {

            if (num_item<=0) {
                Toast.makeText(this, "Number of Items shold be > 0!!!!", Toast.LENGTH_SHORT).show();
            } else {
                     Cursor cursor;
                     cursor=db.getAllItems();
                     do{
                         if(cursor.equals(null)||cursor.getCount()<=0)break;
                         if(cursor.getInt(cursor.getColumnIndex("ItemID"))==Integer.parseInt(txt.getText().toString())){
                             o_arr[order_cou]=cursor.getString(cursor.getColumnIndex("Name")).toString();
                             order_cou++;
                             Integer ItemPrice=cursor.getInt(cursor.getColumnIndex("Price"));
                             Integer pRice=num_item*ItemPrice;
                             pay=pay+pRice;
                             totalprice.setText(String.valueOf(pay));
                             txt.setText("");
                             number_item.setText("0");
                             break;
                         }
                     }while (cursor.moveToNext());
            }

        }
    }
    public void Payment(View view) {
        if(loca_txt.getText().toString().equals("")){
            Toast.makeText(this, "Enter Your Location !!!", Toast.LENGTH_LONG).show();
        }
        else {
            Integer id = null;
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            id = preferences.getInt("id", 0);
            db.add_Cart(pay, id,loca_txt.getText().toString());
            TextView totalprice = (TextView) findViewById(R.id.textView8);
            AutoCompleteTextView txt = (AutoCompleteTextView) findViewById(R.id.textView4);
            TextView number_item = (TextView) findViewById(R.id.numitem);
            totalprice.setText("Total Price");
            number_item.setText("0");
            txt.setText("");
            Toast.makeText(this, "Success Order thank you ^^d", Toast.LENGTH_SHORT).show();
            Order(view);
        }
    }

    public void Location(View view) {
        int id = view.getId();
        if(id == R.id.imageView17){
            Intent i = new Intent(first.this,Map.class);
            startActivity(i);
        }
    }
 int cn=0,cn1=0;
    public void CatMe(View view) {
        cn++;
        ConstraintLayout con2=(ConstraintLayout)findViewById(R.id.constraintLayout2);
        if(cn%2!=0){
            con2.setVisibility(View.VISIBLE);
            con2.setAnimation(top);
        }
        else {
            con2.setVisibility(View.INVISIBLE);
            con2.setAnimation(butom);
        }
    }

    public void Srch(View view) {

    }

    public void view_items(View view) {
        val.setVisibility(View.INVISIBLE);
        ConstraintLayout con1=(ConstraintLayout)findViewById(R.id.o_view);
        con1.setVisibility(View.VISIBLE);

    }
    int index=0;

    public void last_item(View view) {
        TextView item_name=(TextView) findViewById(R.id.textView11);
        if(index>0){
            index--;
            item_name.setText(o_arr[index]);
        }
        else {
            Toast.makeText(getApplicationContext(), "No item before this item !!", Toast.LENGTH_SHORT).show();
        }
    }

    public void next_item(View view) {
        TextView item_name=(TextView) findViewById(R.id.textView11);
        if(index<order_cou-1){
            index++;
            item_name.setText(o_arr[index]);
        }
        else {
            Toast.makeText(getApplicationContext(), "No item after this item !!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Back(View view) {
        ConstraintLayout con1=(ConstraintLayout)findViewById(R.id.o_view);
        con1.setVisibility(View.INVISIBLE);
        val.setVisibility(View.VISIBLE);
    }
}
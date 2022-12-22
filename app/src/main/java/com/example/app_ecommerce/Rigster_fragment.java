            package com.example.app_ecommerce;

            import android.annotation.SuppressLint;
            import android.app.AlertDialog;
            import android.app.DatePickerDialog;
            import android.content.DialogInterface;
            import android.content.Intent;
            import android.content.SharedPreferences;
            import android.database.Cursor;
            import android.os.Bundle;
            import android.preference.PreferenceManager;
            import android.service.autofill.RegexValidator;
            import android.util.Log;
            import android.view.View;
            import android.view.animation.Animation;
            import android.view.animation.AnimationUtils;
            import android.widget.DatePicker;
            import android.widget.EditText;
            import android.widget.ImageView;
            import android.widget.Switch;
            import android.widget.TextView;
            import android.widget.Toast;

            import androidx.annotation.NonNull;
            import androidx.appcompat.app.AppCompatActivity;
            import androidx.constraintlayout.widget.ConstraintLayout;
            import androidx.fragment.app.DialogFragment;
            import androidx.viewpager.widget.ViewPager;

            import com.example.app_ecommerce.ui.main.SectionsPagerAdapter;
            import com.google.android.gms.tasks.OnCompleteListener;
            import com.google.android.gms.tasks.OnFailureListener;
            import com.google.android.gms.tasks.OnSuccessListener;
            import com.google.android.gms.tasks.Task;
            import com.google.android.material.tabs.TabLayout;
            import com.google.firebase.auth.AuthResult;
            import com.google.firebase.auth.FirebaseAuth;

            import java.text.DateFormat;
            import java.util.Calendar;
            import java.util.regex.Pattern;


            public class Rigster_fragment<DatabaseRef> extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {
                Database db = new Database(this);
                Animation top,butom;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rigster_fragment);
                top= AnimationUtils.loadAnimation(this,R.anim.top_anim);
                butom= AnimationUtils.loadAnimation(this,R.anim.butom_anim);
                TextView hel=(TextView)findViewById(R.id.hello);
                TextView ti=(TextView)findViewById(R.id.title);
                ImageView im=(ImageView)findViewById(R.id.imageView11);
                ConstraintLayout head1=(ConstraintLayout)findViewById(R.id.head1);
                hel.setAnimation(butom);
                ti.setAnimation(butom);
                im.setAnimation(butom);
                head1.setAnimation(butom);
            SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
            ViewPager viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            TabLayout tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
            viewPager.setAnimation(butom);
            }
            public void SignGo(View view) {
            EditText e_mail=(EditText)findViewById(R.id.signemail);
            EditText pass1 =(EditText)findViewById(R.id.signpassword);
            EditText pass2 =(EditText)findViewById(R.id.signconfirmpassword);
            EditText date  =(EditText)findViewById(R.id.date);
            TextView txt1  =(TextView)findViewById(R.id.txt1);
            TextView txt2  =(TextView)findViewById(R.id.txt2);
            TextView txt3  =(TextView)findViewById(R.id.txt3);
            TextView txt4  =(TextView)findViewById(R.id.txt4);

            if(!e_mail.getText().toString().isEmpty()){
            txt1.setText("");
            }
            if(!pass1.getText().toString().isEmpty()){
            txt2.setText("");
            }
            if(!pass2.getText().toString().isEmpty()){
            txt3.setText("");
            }
            if(!date.getText().toString().isEmpty()) {
            txt4.setText("");
            }

            if(e_mail.getText().toString().isEmpty()){
            txt1.setText("Reqired Field ! ");
            }
            else if(pass1.getText().toString().isEmpty()){
            txt2.setText("Reqired Field ! ");
            }
            else if(pass2.getText().toString().isEmpty()){
            txt3.setText("Reqired Field ! ");
            }
            else if(date.getText().toString().isEmpty()){
            txt4.setText("Reqired Field ! ");
            }
            else {
            String a=pass1.getText().toString(),b=pass2.getText().toString();
             if(!a.equals(b)){
                 txt3.setText("passwords dosen't match !");
                 pass2.setText("");
             }
             else {
                 String m=e_mail.getText().toString(),p=pass1.getText().toString(),d=date.getText().toString();
                 boolean f=false;
                 Cursor cursor;
                 cursor=db.getAllUser();
                 Integer x=cursor.getCount();
                 do{
                     if(x<=0||cursor==null)break;
                     String na=cursor.getString(cursor.getColumnIndex("Name"));
                     if(na.equals(m)){
                         f=true;
                         break;
                     }
                 }while (cursor.moveToNext());
                 if(f) {
                     e_mail.setText("");
                     Toast.makeText(Rigster_fragment.this, "Name is found!!!!", Toast.LENGTH_SHORT).show();
                 }
                 else {

                     db.add_User(m,p,d);
                     Toast.makeText(Rigster_fragment.this, "Sign up sucess", Toast.LENGTH_SHORT).show();
                     e_mail.setText("");
                     pass1.setText("");
                     pass2.setText("");
                     date.setText("");
                     Intent intent = new Intent(Rigster_fragment.this, Rigster_fragment.class);
                     startActivity(intent);
                     finish();
                 }
             }
            }
            }
            public void LogGo(View view) {
            EditText logmail=(EditText)findViewById(R.id.loginemail);
            EditText logpass=(EditText)findViewById(R.id.loginpassword);
            TextView txt_1=(TextView)findViewById(R.id.txt_1);
            TextView txt_2=(TextView)findViewById(R.id.txt_2);

            if(!logmail.getText().toString().isEmpty()){
            txt_1.setText("");
            }
            if(!logpass.getText().toString().isEmpty()) {
            txt_2.setText("");
            }

            if(logmail.getText().toString().isEmpty()){
            txt_1.setText("Reqired Field ! ");
            }
            else if(logpass.getText().toString().isEmpty()){
            txt_2.setText("Reqired Field ! ");
            }
            else{
            Switch re=(Switch)findViewById(R.id.rememberme);
            boolean f=false;
                Cursor cursor;
                Integer id = null;
                String Name_user=logmail.getText().toString();
                cursor=db.getAllUser();
                do{
                    if(cursor.getCount()<=0||cursor==null)break;
                    String na=cursor.getString(cursor.getColumnIndex("Name")),pa=cursor.getString(cursor.getColumnIndex("Password"));
                   String a=logmail.getText().toString(),b=logpass.getText().toString();
                    if(na.equals(a)&&pa.equals(b)){
                        f=true;
                        id=cursor.getInt(cursor.getColumnIndex("userid"));
                        break;
                    }
                }while (cursor.moveToNext());
            if(f){
                    Toast.makeText(Rigster_fragment.this, "Login Succefully", Toast.LENGTH_SHORT).show();
                    if(re.isChecked()){
                        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putBoolean("remember",true);
                        editor.putInt("id",id);
                        editor.putString("Name",Name_user);
                        editor.apply();
                    }
                    else {
                        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putBoolean("remember",false);
                        editor.putInt("id",id);
                        editor.putString("Name",Name_user);
                        editor.apply();
                    }
                    Intent intent = new Intent(getApplicationContext(), first.class);
                    startActivity(intent);
                    finish();
                logmail.setText("");

                }
            else {
                Toast.makeText(Rigster_fragment.this, "Password or email not Valid !!!!!!", Toast.LENGTH_SHORT).show();
            }
                logpass.setText("");
            }
            }
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
            Calendar c= Calendar.getInstance();
            c.set(Calendar.YEAR,year);
            c.set(Calendar.MONTH,month);
            c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            String currentdatestring = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
            EditText txt=(EditText)findViewById(R.id.date);
            txt.setText(currentdatestring);
            }
            public void Calender(View view) {
            DialogFragment datapicker=new data();
            datapicker.show(getSupportFragmentManager(),"data picker");
            }
            public void Remember(View view) {
            EditText logmail=(EditText)findViewById(R.id.loginemail);
            EditText logpass=(EditText)findViewById(R.id.loginpassword);
            }
            @SuppressLint("ResourceAsColor")
            public void Forget(View view) {
            EditText resetmail=new EditText(view.getContext()),resetpass=new EditText(view.getContext()),resetdate=new EditText(view.getContext());
            resetmail.setHint("E-mail");
            resetmail.setHintTextColor(R.color.black);
            AlertDialog.Builder passwordResetDialog=new AlertDialog.Builder(view.getContext());
            passwordResetDialog.setTitle("Reset Password ?");
            passwordResetDialog.setMessage("Enter Your Email To reset password ");
            passwordResetDialog.setView(resetmail);
            passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                            Cursor cursor;
                            cursor=db.getAllUser();
                    boolean f=false;
                    do{
                        if(cursor.getCount()<=0||cursor==null)break;
                        String na=cursor.getString(cursor.getColumnIndex("Name"));
                        if(na.equals(resetmail.getText().toString())){
                            resetdate.setText(cursor.getString(cursor.getColumnIndex("bithdate")));
                            f=true;
                            break;
                        }
                    }while (cursor.moveToNext());
                    if(f){

                        resetpass.setHint("New Password");
                        resetpass.setHintTextColor(R.color.black);
                        AlertDialog.Builder passwordResetDialog1=new AlertDialog.Builder(view.getContext());
                        passwordResetDialog1.setTitle("Reset Password ?");
                        passwordResetDialog1.setMessage("Enter Your New password ");
                        passwordResetDialog1.setView(resetpass);

                        passwordResetDialog1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               db.delete_User(resetmail.getText().toString());
                               db.add_User(resetmail.getText().toString(),resetpass.getText().toString(),resetdate.getText().toString());
                            }
                        });
                        passwordResetDialog1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        passwordResetDialog1.create().show();
                    }
                    else {
                        Toast.makeText(Rigster_fragment.this, "Email Not Valid", Toast.LENGTH_SHORT).show();
                    }
                }

            });
            passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });
            passwordResetDialog.create().show();
            }

            }
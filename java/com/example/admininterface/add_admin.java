package com.example.admininterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class add_admin extends AppCompatActivity {
    EditText Admin_name, Admin_Email, Admin_Password, Admin_Field, Admin_Depart, Admin_DOB, Admin_phone;
    Button b1;
    Calendar mycalendar = Calendar.getInstance();

    ProgressDialog pd;
    FirebaseAuth auth;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        auth = FirebaseAuth.getInstance();
        Admin_phone = findViewById(R.id.Admin_phone);
        Admin_DOB = findViewById(R.id.Admin_DOB);
        Admin_Email = findViewById(R.id.Admin_Email);
        Admin_Depart = findViewById(R.id.AdminDepart);
        Admin_Field = findViewById(R.id.Admin_field);
        Admin_name = findViewById(R.id.Admin_name);
        Admin_Password = findViewById(R.id.Admin_Password);
        b1 = findViewById(R.id.Admin_summit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(add_admin.this);
                pd.setMessage("Please wait...");
                pd.show();

                String str_name = Admin_name.getText().toString();
                String str_email = Admin_Email.getText().toString();
                String str_number = Admin_phone.getText().toString();
                String str_field = Admin_Field.getText().toString();
                String str_depart = Admin_Depart.getText().toString();
                String str_date = Admin_DOB.getText().toString();
                String str_password = Admin_Password.getText().toString();
                if (TextUtils.isEmpty(str_name) || TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_number) || TextUtils.isEmpty(str_password)
                        || TextUtils.isEmpty(str_field) || TextUtils.isEmpty(str_date) || TextUtils.isEmpty(str_depart)) {
                    Toast.makeText(add_admin.this, "Please fill all the field above", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else if (str_password.length() < 8) {
                    pd.dismiss();
                    Toast.makeText(add_admin.this, "Password required minimum eight codes", Toast.LENGTH_SHORT).show();
                } else {
                    register(str_name, str_email, str_number, str_password, str_field, str_date, str_depart);
                }
            }
        });
    }

    private void register(String name, String email, String number, String password, String field, String date, String depart) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser;
                    firebaseUser = auth.getCurrentUser();
                    String str = firebaseUser.getUid();
                    reference = FirebaseDatabase.getInstance().getReference("Admin").child(str);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("Id", email);
                    hashMap.put("Name", name.toLowerCase());
                    hashMap.put("Password", password);
                    hashMap.put("Number", number);
                    hashMap.put("Field", field);
                    hashMap.put("DateofBirth", date);
                    hashMap.put("Department", depart);
                    hashMap.put("imageURL", "https://firebasestorage.googleapis.com/v0/b/alumini-database-64f3d.appspot.com/o/user%20(1).png?alt=media&token=77fa9edf-e651-41d3-a8f4-6755808b3165");
                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                pd.dismiss();
                                Intent intent = new Intent(add_admin.this, admin_home_page.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    });
                } else {
                    pd.dismiss();
                    Toast.makeText(add_admin.this, "Can't Signin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
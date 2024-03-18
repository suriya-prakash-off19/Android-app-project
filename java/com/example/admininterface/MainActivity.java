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

import com.example.admininterface.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText email,password;
    public FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.use_id);
        password=findViewById(R.id.use_Password);
        button=findViewById(R.id.LoginButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        auth=FirebaseAuth.getInstance();
        String str="admin";
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Please wait...");
                pd.show();
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();
                if (TextUtils.isEmpty(str_password) || TextUtils.isEmpty(str_email)) {
                    Toast.makeText(MainActivity.this, "Fill all the fields!", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else {
                    auth.signInWithEmailAndPassword(str_email, str_password).addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(auth.getCurrentUser().getUid());
                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        User str1=snapshot.getValue(User.class);
                                        if( str1.getRegisterAs().toString().equals(str)) {
                                            pd.dismiss();
                                            Intent intent = new Intent(MainActivity.this, admin_home_page.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            pd.dismiss();
                                            Toast.makeText(MainActivity.this, "Authenication Error!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        pd.dismiss();
                                    }
                                });
                            }else{
                                pd.dismiss();
                                Toast.makeText(MainActivity.this, "Authenication Error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null){

            startActivity(new Intent(MainActivity.this, admin_home_page.class));
            finish();
        }

    }
}
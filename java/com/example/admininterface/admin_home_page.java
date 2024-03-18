package com.example.admininterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.admininterface.fragment.admin_home;
import com.example.admininterface.fragment.admin_meeting;
import com.example.admininterface.fragment.notification_fragment;
import com.example.admininterface.fragment.postDetail_fragment;
import com.example.admininterface.fragment.profile_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class admin_home_page extends AppCompatActivity {
    View view;
    TextView textView,textView2;
    Fragment selectedFragment=null;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        textView=findViewById(R.id.fragment_name);

        bottomNavigationView =findViewById(R.id.admin_bottomNavigation);

        bottomNavigationView.setItemIconTintList(null);

//        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        Bundle intent=getIntent().getExtras();
        if(intent!=null){
//            String publisher=intent.getString("publisherid");
//            SharedPreferences.Editor editor=getSharedPreferences("PREFS",MODE_PRIVATE).edit();
//            editor.putString("profileid",publisher);
//            editor.apply();
//            getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragmentContainer,new profile_fragment()).commit();

        }else {
//            getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragmentContainer,new profile_fragment()).commit();

        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener(){

        String str;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.admin_home_button:
                    str="Home";
                    selectedFragment=new admin_home();
                    break;
//                case R.id.admin_gallary_button:
//                    selectedFragment=new Admin_gallaryFragment();
//                    break;
                case R.id.admin_meet_button:
                    str="Meeting";
                    selectedFragment=new admin_meeting();
                    break;
                case R.id.admin_more_button:
//                    SharedPreferences.Editor editor=getSharedPreferences("PREFS",MODE_PRIVATE).edit();
//                    editor.putString("profileid", FirebaseAuth.getInstance().getCurrentUser().getUid());
//                    editor.apply();
                    str="Profile";
                    selectedFragment =new profile_fragment();
                    break;
                case R.id.admin_notification_button:
                    str="Notification";
                    selectedFragment=new notification_fragment();
                    break;
                case R.id.admin_members_button:
                    str="Aluminies";
                    selectedFragment=new postDetail_fragment();
                    break;
            }
            if(selectedFragment!=null){
                getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragmentContainer,selectedFragment).addToBackStack(null).commit();
                textView.setText(str);
            }

            return true;
        }
    };
    public  void post(View v){
        Intent intent=new Intent(admin_home_page.this, post.class);
        startActivity(intent);
    }
    public void meeting(View v){
        startActivity(new Intent(admin_home_page.this, add_meeting.class));
    }
//    public void addNew(View v){
//        startActivity(new Intent(admin_home_page.this,signup_activity.class));
//    }
//    public void Password(View v){
//        startActivity(new Intent(admin_home_page.this,NewPasswordActivity.class));
//    }
//    public void share(View v){
//        startActivity(new Intent(Admin_homePage.this,signup_activity.class));
//    }
//    public void AddAdmin(View v){
//        getSupportFragmentManager().beginTransaction().replace(R.id.admin_fragmentContainer,Admin_profileFragment).commit();
//    }


    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

}
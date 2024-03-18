package com.example.admininterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admininterface.Model.meet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class add_meeting extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    DatabaseReference storageReference;
//    StorageTask uploadTask;
//    FirebaseUser firebaseUser;
//    FirebaseAuth auth;

    Button b1;
    //    private int CalendarHour, CalendarMinute;
//    String format;
//    Calendar calendar;
//    TimePickerDialog timepickerdialog;
    EditText Date,time,name,depart,host,guest,description,venue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        Date=(EditText) findViewById(R.id.meeting_DOB);
        time=findViewById(R.id.meeting_time);
        depart=(EditText) findViewById(R.id.Department);
        name=(EditText) findViewById(R.id.meeting_name);
        host=(EditText) findViewById(R.id.meeting_host);
        guest=(EditText) findViewById(R.id.Guests);
        description=(EditText) findViewById(R.id.meeting_description);
        venue=(EditText) findViewById(R.id.meetin_venue);
        b1=(Button) findViewById(R.id.meeting_summit);
//        time.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                 TODO Auto-generated method stub
//                calendar = Calendar.getInstance();
//                CalendarHour = calendar.get(Calendar.HOUR_OF_DAY);
//                CalendarMinute = calendar.get(Calendar.MINUTE);
//
//
//                TimePickerDialog timePickerDialog = new TimePickerDialog(Add_meeting_activity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
//                        time.setText(hourOfDay + ":" + minutes);
//                    }
//                }, CalendarHour, CalendarMinute, false);
//                timePickerDialog.show();
//                timePickerDialog.isShowing();
//
//
//            }
//        });
        DatePickerDialog.OnDateSetListener Dates =new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadmeeting();
//                Toast.makeText(Add_meeting_activity.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });
        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(add_meeting.this,Dates,myCalendar.get(Calendar.YEAR)
                        ,myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel(){

        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        Date.setText(dateFormat.format(myCalendar.getTime()));
    }
    //    public  void summit(View v){
//            uploadmeeting();
//    }
    private void uploadmeeting(){
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Posting");
        progressDialog.show();
        String str_name=name.getText().toString();
        String str_time=time.getText().toString();
        String str_depart=depart.getText().toString();
        String str_date=Date.getText().toString();
        String str_host=host.getText().toString();
        String str_guest=guest.getText().toString();
        String str_des=description.getText().toString();
        String str_venue=venue.getText().toString();
        if(TextUtils.isEmpty(str_name)||TextUtils.isEmpty(str_time)||TextUtils.isEmpty(str_venue)||TextUtils.isEmpty(str_host)||TextUtils.isEmpty(str_guest)||TextUtils.isEmpty(str_date)||TextUtils.isEmpty(str_depart)){
            progressDialog.dismiss();
            Toast.makeText(this, "Please Fill the above Fields", Toast.LENGTH_SHORT).show();
        }else{
            storageReference= FirebaseDatabase.getInstance().getReference().child("meeting");
            String postID=storageReference.push().getKey();
            meet meet=new meet(str_name,str_venue,str_date,str_depart,str_guest,str_host,str_time,postID,str_des);
            storageReference.push().setValue(meet).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    startActivity(new Intent(add_meeting.this, admin_home_page.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK));
                    Toast.makeText(add_meeting.this, "Meeting Uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }
}
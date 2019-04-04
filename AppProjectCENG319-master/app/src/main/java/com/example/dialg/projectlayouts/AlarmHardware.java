package com.example.dialg.projectlayouts;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.database.DatabaseReference;

public class AlarmHardware extends AppCompatActivity {

    private EditText mHour_editTxt;
    private EditText mMinute_editTxt;
    private Button mSelect_btn;
    private Button mCancel_btn;
    private Spinner mHour_spinner;
    private Spinner mMinute_spinner;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_hardware);

        mHour_spinner = (Spinner) findViewById(R.id.hourspinner);
        mMinute_spinner = (Spinner) findViewById(R.id.minutespinner);

        mSelect_btn = (Button) findViewById(R.id.select_btn);
        mCancel_btn = (Button) findViewById(R.id.back_btn);
        getDatabase();

        mSelect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mHour = mHour_spinner.getSelectedItem().toString();
                myRef.child("Hour").setValue(mHour);

                String mMinute = mMinute_spinner.getSelectedItem().toString();
                myRef.child("Minute").setValue(mMinute);

                Toast.makeText(getApplicationContext(), "Time is now selected!", Toast.LENGTH_LONG).show();



            }
        });

        mCancel_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                String mHour = mHour_spinner.getSelectedItem().toString();
                myRef.child("Hour").setValue(0);

                String mMinute = mMinute_spinner.getSelectedItem().toString();
                myRef.child("Minute").setValue(0);

                Toast.makeText(getApplicationContext(), "Cancelling...", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getDatabase(){

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Time");

    }

    }


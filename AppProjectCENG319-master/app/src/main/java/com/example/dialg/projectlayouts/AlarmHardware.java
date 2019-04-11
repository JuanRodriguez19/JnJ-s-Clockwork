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
import android.widget.SeekBar;

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
    private SeekBar mVolume_bar;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    SeekBar volumebar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_hardware);

        mHour_spinner = (Spinner) findViewById(R.id.hourspinner);
        mMinute_spinner = (Spinner) findViewById(R.id.minutespinner);
        //mVolume_bar = (SeekBar) findViewById(R.id.volumeBar);

        mSelect_btn = (Button) findViewById(R.id.select_btn);
        mCancel_btn = (Button) findViewById(R.id.back_btn);
        mVolume_bar = (SeekBar) findViewById(R.id.volumeBar);
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

        mCancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String mHour = mHour_spinner.getSelectedItem().toString();
                myRef.child("Hour").setValue(-1);

                String mMinute = mMinute_spinner.getSelectedItem().toString();
                myRef.child("Minute").setValue(-1);

                Toast.makeText(getApplicationContext(), "Cancelling...", Toast.LENGTH_LONG).show();
            }
        });



        /*mVolume_bar.setOnClickListener(new View.OnSeekBarChangeListener() {
            @Override
            public void onClick(View v) {
                String mVolume = mVolume_bar.get. toString();
                myRef.child("Volume").setValue(mVolume);

            }
        });*/

            mVolume_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int volume_value;
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    //Toast.makeText(getApplicationContext(),"Volume: "+progress, Toast.LENGTH_SHORT).show();
                    volume_value = progress;
                    myRef.child("Volume").setValue(volume_value);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
    }


    private void getDatabase(){

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Time");

    }


}


package com.example.dialg.projectlayouts;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import android.widget.TextView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class TempActivity extends AppCompatActivity {
        private Button btnTemp;
        private TextView temperature;
        private FirebaseDatabase database;
        private DatabaseReference myRef,mySensorRef;

        DataStructure mdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        //Back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Temperature Readings");

        //Database Functions
        findAllViews();
        getDatabase();
        retrieveData();

        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData((Editable) temperature.getText());
            }
        });
    }
    private void findAllViews(){
        btnTemp = findViewById(R.id.btnTemp);
        temperature = findViewById(R.id.temperature);
    }
    private void getDatabase(){
        
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("data");
        mySensorRef = database.getReference().child("SensorData");
    }

    //reading the temperature sensor
    private void retrieveData(){
        mySensorRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               SensorStructure ss = dataSnapshot.getValue(SensorStructure.class);
                temperature.setText("" +ss.getStemperature()+"oC");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                SensorStructure ss = dataSnapshot.getValue(SensorStructure.class);
                temperature.setText("" +ss.getStemperature()+"oC");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mySensorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<SensorStructure> arraylist = new ArrayList<>();

                if (dataSnapshot != null && dataSnapshot.getValue() != null) {

                    for (DataSnapshot a : dataSnapshot.getChildren()) {
                        SensorStructure sensorStructure = new SensorStructure();
                        sensorStructure.setStemperature(a.getValue(SensorStructure.class).getStemperature());


                        arraylist.add(sensorStructure);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("MapleLeaf", "Data Loading Cancelled/Failed.", databaseError.toException());
            }

        });

    }

    //writing to Firebase with UID
    private DataStructure createData(Editable temperature){
        
        Long time = System.currentTimeMillis()/1000;
        String timestamp = time.toString();
        return new DataStructure(String.valueOf(temperature),timestamp);

    }
    private void writeData(Editable temperature) {

        DataStructure mData = createData(temperature);
        myRef.push().setValue(mData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Temperature Saved ", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Temperature Failed to Save", Toast.LENGTH_LONG).show();
            }
        });
    }

    

}


package com.example.dialg.projectlayouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;

public class FirebaseDataHelper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_data_helper);
    }

    /*public void addBook(Book book, final DataStatus dataStatus ) {
        String key = mReferenceBooks.push().getKey();
        mReferenceBooks.child(key) .setValue(book)
                .addOnSuccessListener(new onSuccessListener<Void> () {
                    @Override
                    public void onSuccess(Void aVoid {
                        dataStatus.DataIsInserted();
                    }
                } );*/
}

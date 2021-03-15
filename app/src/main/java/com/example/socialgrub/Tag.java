package com.example.socialgrub;

import android.provider.ContactsContract;
import android.renderscript.Sampler;

import androidx.annotation.NonNull;

import com.google.android.gms.common.data.DataBuffer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcel;

@Parcel
public class Tag {
    public String tagName;
    private int tagID;
    //private DatabaseReference dbRef;

    public Tag()
    {

    }
    public Tag(String tagName)
    {
        this.tagName = tagName;
    }
    public Tag(String tagName, int tagID)
    {
        this.tagName = tagName;
        this.tagID = tagID;
    }
    public Tag (int tagID)
    {
        this.tagID = tagID;
        //this.tagName = getTagNameGivenID(tagID);
    }
    public String getTagString()
    {
        return tagName;
    }

    /*public String getTagNameGivenID(int ID)
    {
        dbRef = getDBRef();

        Query query = dbRef.orderByChild("id").equalTo(ID);
        query.addListenerForSingleValueEvent(valueEventListener);
        String tagString = query.get();
        return tagString;
    }*/
    /*public int getTagIDGivenName(String tagString)
    {
        dbRef = getDBRef();

    }*/
    /*private DatabaseReference getDBRef()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://social-grub-default-rtdb.firebaseio.com/");
        DatabaseReference storePoint = db.getReference("1jtXaB0m7-Hd5RcigSl1XRFoJCt5DNDMfgagA8tMOWxo/Sheet1");
        return storePoint;
    }*/


}

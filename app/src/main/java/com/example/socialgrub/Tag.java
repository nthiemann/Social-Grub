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
public class Tag implements Comparable<Tag>{
    public String tagName;
    public int tagID;
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
    public String getTagName()
    {
        return tagName;
    }

    public int getTagID() {
        return tagID;
    }

    @Override
    public int compareTo(Tag otherTag){

        if (this.tagID < otherTag.getTagID())
            return -1;
        else if (this.tagID > otherTag.getTagID())
            return 1;
        else
            return 0;
    }

}

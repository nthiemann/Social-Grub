package com.example.socialgrub;

import org.parceler.Parcel;

@Parcel
public class Tag {
    public String tagName;
    private int tagID;

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
    public String getTagString()
    {
        return tagName;
    }
}

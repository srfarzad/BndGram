package com.app.bndgram.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserData extends RealmObject {

    @PrimaryKey
    public int id;
    public String title;
    public String description;

}

package com.app.bndgram.di;

import com.app.bndgram.di.UserModule;

import java.util.List;

public interface IUserDb {

    public long insert(UserModel user);
    public long update(UserModel user);
    public long delete(int id);
    public long deleteAll();
    public int login(UserModel user);

    public List<UserModel> userslist();

}

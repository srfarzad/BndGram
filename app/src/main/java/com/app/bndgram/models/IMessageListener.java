package com.app.bndgram.models;

import java.util.List;

public interface IMessageListener<T> {


    public void onSuccess(List<T> list);

    public void onSuccessObject(T item);

    public void onError(String errorMessage);
}

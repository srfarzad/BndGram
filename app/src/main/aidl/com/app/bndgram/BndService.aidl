// BndService.aidl
package com.app.bndgram;

import com.app.bndgram.RequestCallBack;

// Declare any non-default types here with import statements

interface BndService {

    void login();
    void register(RequestCallBack callBack);
    void onAccept(String message);

}

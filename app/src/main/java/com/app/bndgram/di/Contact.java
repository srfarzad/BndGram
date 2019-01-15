package com.app.bndgram.di;

public class Contact {
    private String mail;

    public Contact() {
        this.setMail("test@google.com");
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void deleteMail() {
        this.mail = "";
    }


}

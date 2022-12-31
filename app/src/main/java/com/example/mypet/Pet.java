package com.example.mypet;

import android.media.Image;

import java.io.Serializable;

public class Pet implements Serializable {
    public String uid;
    public String userId;
    public String email;
    public String hewan;
    public String jenis;
    public String ras;
    public Image foto;

    public Pet(){

    }

    public Pet(String userId,String email, String hewan, String jenis, String ras){
        this.userId=userId;
        this.email=email;
        this.hewan=hewan;
        this.jenis=jenis;
        this.ras=ras;
    }
}

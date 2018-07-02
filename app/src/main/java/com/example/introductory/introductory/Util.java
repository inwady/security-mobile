package com.example.introductory.introductory;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class Util {
    public static String fromBase64(String base64String) throws UnsupportedEncodingException {
        byte[] data = Base64.decode(base64String, Base64.DEFAULT);
        return new String(data, "UTF-8");
    }
}

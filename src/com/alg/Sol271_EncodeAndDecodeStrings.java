package com.alg;

import java.util.ArrayList;
import java.util.List;

public class Sol271_EncodeAndDecodeStrings {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        String encoded = "";
        for(String s:strs) {
            encoded += s+Character.toString((char)257);
        }
        return encoded.substring(0,encoded.length()-1);
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        String del=Character.toString((char)257);
        String[] ans = s.split(del,-1);
        List<String> res = new ArrayList<>();
        for(String i:ans){
            res.add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        Sol271_EncodeAndDecodeStrings codec = new Sol271_EncodeAndDecodeStrings();
        codec.decode(codec.encode(strs));
    }
}

package com.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HAU on 1/19/2018.
 */
/*TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be
encoded to a tiny URL and the tiny URL can be decoded to the original URL.*/
public class Sol535_EncodeAndDecodeTinyURL {

    // using counter
    Map<Integer,String> map = new HashMap<>();
    int i = 0;
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        map.put(i, longUrl);
        return "http://tinyurl.com/" +  i++;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/","")));
    }
    // method 2, list
    public static List<String> urls = new ArrayList<>();
    public static String encode2(String longurl){
        urls.add(longurl);
        return String.valueOf(urls.size() - 1);
    }
    public static String decode2(String shorturl){
        int index = Integer.valueOf(shorturl);
        if (index < urls.size()){
            return urls.get(index);
        }
        return "";
    }
}

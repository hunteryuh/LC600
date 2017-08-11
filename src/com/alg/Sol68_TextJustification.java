package com.alg;

import java.util.ArrayList;

/**
 * Created by HAU on 8/10/2017.
 */
public class Sol68_TextJustification {
    public static ArrayList<String> fullJustify(String[] words, int maxWidth) {
        ArrayList<String> res = new ArrayList<>();
        int L = maxWidth;
        if (words == null || words.length == 0) return res;
        int count = 0; // length of word[i-1]
        int last = 0;

        for (int i = 0; i < words.length ; i++){
            if ( count + words[i].length() + i - last > L){
                int spacenum = 0;
                int extraspace = 0;
                if ( i - last -1 > 0){
                    spacenum = (L - count )/(i - last - 1);
                    extraspace = ( L-count)%(i - last - 1);
                }
                StringBuilder sb = new StringBuilder();
                for ( int j = last; j < i; j++){
                    sb.append(words[j]);
                    if ( j< i-1){
                        for( int k = 0; k < spacenum; k++){
                            sb.append(" ");
                        }
                        if (extraspace > 0){
                            sb.append(" ");
                            extraspace--;
                        }
                    }
                }
                // if at most one word in the line
                for (int j = sb.length(); j<L; j++){
                    sb.append(" ");
                }
                res.add(sb.toString());
                count = 0;
                last = i;
            }
            count += words[i].length();

        }
        StringBuilder ll = new StringBuilder();
        for (int i = last; i < words.length; i++){
            ll.append(words[i]);
            if (ll.length() < L){
                ll.append(" ");
            }
        }
        for ( int i =ll.length() ; i < L; i++){
            ll.append(" "); // needed?
        }
        res.add(ll.toString());
        return res;

    }

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int t = 16;
        ArrayList<String> result = fullJustify(words,t);
        for (String s: result){
            System.out.println(s);
        }
    }
}

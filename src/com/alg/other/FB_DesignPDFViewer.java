package com.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FB_DesignPDFViewer {
    public int designerPdfViewer(List<Integer> h, String word) {
        int length = word.length();
        int height = 0;
        for (char c : word.toCharArray()) {
            if (h.get(c - 'a') > height) {
                height = h.get(c - 'a');
            }
        }
        return length * height;
    }

    public static void main(String[] args) {
        List<Integer> h = new ArrayList<>(Arrays.asList(1,3,1,6));
        FB_DesignPDFViewer ff = new FB_DesignPDFViewer();
        String word = "cba";
        System.out.println(ff.designerPdfViewer(h, word)); // 9
    }

}

package com.alg;

/**
 * Created by HAU on 11/30/2017.
 */
/* The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file. */
public class Sol157_ReadNCharacterGivenRead4 {
    public int read(char[] buf, int n ){
        // time O(n), space O(1)
        for ( int i = 0; i < n; i+=4){
            char[] tmp = new char[4];
            int len = read4(tmp);
            //// 将临时数组拷贝至buf数组，这里拷贝的长度是本次读到的个数和剩余所需个数中较小的
            System.arraycopy(tmp,0,buf,i,Math.min(len,n-i));
            // 如果读不满4个，说明已经读完了，返回总所需长度和目前已经读到的长度的较小的
            if ( len < 4) return Math.min(i+len, n);
        }

        // 如果循环内没有返回，说明读取的字符是4的倍数
        return n;
    }

    private int read4(char[] tmp) {
        return tmp.length;
    }
}

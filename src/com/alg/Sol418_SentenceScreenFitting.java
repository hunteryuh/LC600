package com.alg;

/**
 * Created by HAU on 12/6/2017.
 */
/*Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

    A word cannot be split into two lines.
    The order of words in the sentence must remain unchanged.
    Two consecutive words in a line must be separated by a single space.
    Total words in the sentence won't exceed 100.
    Length of each word is greater than 0 and won't exceed 10.
    1 ≤ rows, cols ≤ 20,000.

 Example 3:

Input:
rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

Output:
1

Explanation:
I-had
apple
pie-I
had--

The character '-' signifies an empty space on the screen.

*/
public class Sol418_SentenceScreenFitting {
    // https://leetcode.com/problems/sentence-screen-fitting/solutions/90845/21ms-18-lines-java-solution/
    public static int wordsTyping(String[] sentence, int rows, int cols) {
        /*句子的总长度的求法时要在每个单词后面加上一个空格(包括最后一个单词)，我们遍历屏幕的每一行，然后每次start都加上宽度，
        然后看all[start%len]是否为空格，是的话就start加1，这样做的好处是可以处理末尾是没有空格的情况，比如宽度为1，只有一个单词a，
        那么我们都知道是这样放的 a ，start变为1，len是2，all[start%len]是空格，所以start自增1，变成2，
        这样我们用start/len就知道能放下几个了。对于all[start%len]不为空格的情况，
        如果all[(start-1)%len]也不为空格，那么start就自减1，进行while循环，直至其为空格为止。*/
        String s = String.join(" ",sentence)+" ";
        int start = 0;
        int len = s.length();
        for (int i = 0; i<rows; i++){
            start += cols;
            if(s.charAt( start % len) == ' '){
                start++;
            }else{
                while( start > 0 && s.charAt(( start-1)%len) !=' '){
                    start--;
                }
            }
            System.out.println(start);
        }
        return start /len;
    }

    public static void main(String[] args) {
        String[] sentence = {"I","had","apple","pie"};
        String[] s2 = {"I","had","apple"};
        String[] s3 = {"abc","de","f"};
        //System.out.println(wordsTyping(sentence,4,5));
//        System.out.println(wordsTyping(s2,4,5));
        System.out.println(wordsTyping(s3,4,6));

    }
    // method 2
    public static int wordTypeScreen(String[] strings, int rows,int cols){
        /*需要统计加空格的句子总长度，然后遍历每一行，初始化colsRemaining为cols，然后还需要一个变量idx，
        来记录当前单词的位置，如果colsRemaining大于0，就进行while循环，如果当前单词的长度小于等于colsRemaining，说明可以放下该单词，
        那么就减去该单词的长度就是剩余的空间，然后如果此时colsRemaining仍然大于0，则减去空格的长度1，然后idx自增1，
        如果idx此时超过单词个数的范围了，说明一整句可以放下，那么就有可能出现宽度远大于句子长度的情况，所以我们加上之前放好的一句之外，
        还要加上colsRemaining/len的个数，然后colsRemaining%len是剩余的位置，此时idx重置为0，*/
        String s = String.join(" ",strings)+" ";
        int res = 0;
        int idx = 0;
        int n = strings.length;
        int len = s.length();
        for (int i = 0; i < rows; i++){
            int colsRemain = cols;
            while (colsRemain > 0){
                if(strings[idx].length() <= colsRemain){
                    colsRemain -= strings[idx].length();
                    if(colsRemain  >0){
                        colsRemain -= 1;
                    }
                    if(++idx >= n){
                        res +=  1 + colsRemain /len;
                        colsRemain %= len;
                        idx = 0;
                    }
                }else break;
            }
        }
        return res;
    }
}

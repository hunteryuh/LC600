package com.alg;
/*
You own a Goal Parser that can interpret a string command. The command consists of an alphabet of "G", "()" and/or "(al)" in some order. The Goal Parser will interpret "G" as the string "G", "()" as the string "o", and "(al)" as the string "al". The interpreted strings are then concatenated in the original order.

Given the string command, return the Goal Parser's interpretation of command.



Example 1:

Input: command = "G()(al)"
Output: "Goal"
Explanation: The Goal Parser interprets the command as follows:
G -> G
() -> o
(al) -> al
The final concatenated result is "Goal".
Example 2:

Input: command = "G()()()()(al)"
Output: "Gooooal"
Example 3:

Input: command = "(al)G(al)()()G"
Output: "alGalooG"


Constraints:

1 <= command.length <= 100
command consists of "G", "()", and/or "(al)" in some order.
 */
public class Sol1678_GoalParser {

    public static String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < command.length()) {
            char c = command.charAt(i);
            if (c == 'G') {
                sb.append('G');
                i++;
            } else if (command.charAt(i + 1) == ')') {
                sb.append('o');
                i += 2;
            } else {
                sb.append("al");
                i+= 4;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String command = "(al)G(al)()()G";
        System.out.println(interpret(command));
        System.out.println(interpret1(command));
    }

    public static String interpret1(String command) {
//        return command.replaceAll("\\(\\)", "o").replaceAll("\\(al\\)", "al");
        return command.replace("()", "o").replace("(al)", "al");
        // https://stackoverflow.com/questions/10827872/difference-between-string-replace-and-replaceall
        /*
        For example, for simple substitutions like you mentioned, it is better to use:

replace('.', '\\');
instead of:

replaceAll("\\.", "\\\\");
Note: the above conversion method arguments are system-dependent.
         */
//        return command.replaceAll("[(][)]", "o").replaceAll("[(]al[)]", "al");
    }
}

package com.alg.other;

import java.util.Stack;

/*
// # TextEditor实现以下功能：append, backspace, undo, redo, select, bold. input长这样：String[][] input
// # append: 在当前字符串结尾append或将选中的字符串replace e.g. ["1", "APPEND", "hello"]
// # backspace: 删掉当前字符串最后一位或选中的字符串 e.g. ["2", "BACKSPACE"]
// # undo: 撤销上一步操作 e.g. ["3", "UNDO"]
// # redo: 把上一步被撤销的操作重做 e.g. ["4", "REDO"]
// # select: 选中当前字符串的某一部分, 给定了[start, end)  e.g. ["5", "SELECT", 3, 6]
// # bold: 将选中的字符串bold e.g. ["6", "BOLD"]

题目: texteditor实现六个功能，append, backspace, undo, redo, select, bold, 其中select与append或backspace联用.append: 就是往string里append一个string ['append', 'nihao']backspace: 删掉最后一个character e.g. ['backspace']undo: 相当于 cmd-z, 就是前一个执行的操作给取消掉 e.g. ['undo']redo: 相当于 cmd-shift-z, 也就是把取消掉的操作再重做 e.g. ['redo']select: 给你两个index, 要把这substring标记成 selected, 标记完了需要能够chain其他的操作. 比如 当前string='nihaoma' inputs=[ ['select', 2, 4], ['backspace'] ] 最后得到 string='nima' (手动狗头)bold: 参考markdown的加粗方式. 就是在选择的substring两边加上*号
题目还是挺麻烦. 因为只给75分钟. 6个部分越后面越复杂先把前四个部分讲一讲. 因为最后两部分没写出来... 最后分享下思路吧.. 前四个部分看完是不是有点思路? 需要有顺序的又存又取, 是不是肯定stack或者queue我的做法: 两个stack一个stack用来存已经执行过的指令以及他们的arguments: 每次undo, 把stack头部的指令拿下来 -》执行反向操作(append变成删除, 或者删除变成append) -》把执行的反向操作放到第二个stack里另一个stack用来存undo过的指令: 和上面相似, 把undo stack的头部拿下来, 执行反向操作, 然后再放回第‍‍‌‍‍‌‍‌‌‌‌‍‍‍‌‍一个stack
https://codesandbox.io/s/q8w5m?file=/src/index.js:24-442
https://codesandbox.io/s/48r5m?file=/src/index.js


https://www.geeksforgeeks.org/implement-undo-and-redo-features-of-a-text-editor/

https://www.geeksforgeeks.org/implement-undo-and-redo-features-of-a-text-editor/


need 2 stacks for redo and undo
possible 1 stack for result string
https://leetcode.com/discuss/interview-question/770704/Amazon-or-Onsite-or-Text-Editor/902897


11/7/2022 was asking to implement a banking system
create, deposit, transfer,
topk sender (transerred out)
schedule payment/cancel payment, started but not finished
more..

 */
public class Dropbox_TextEditor {

    // Stores the history of all
    // the queries that have been
    // processed on the document
    static Stack<Character> Undo = new Stack<Character>();

    // Stores the elements
    // of REDO query
    static Stack<Character> Redo = new Stack<Character>();

    // Function to perform
    // "WRITE X" operation
    static void WRITE(Stack<Character> Undo, char X)
    {

        // Push an element to
        // the top of stack
        Undo.push(X);
    }

    // Function to perform
    // "UNDO" operation
    static void UNDO(Stack<Character> Undo, Stack<Character> Redo)
    {

        // Stores top element
        // of the stack
        char X = Undo.peek();

        // Erase top element
        // of the stack
        Undo.pop();

        // Push an element to
        // the top of stack
        Redo.push(X);
    }

    // Function to perform
    // "REDO" operation
    static void REDO(Stack<Character> Undo, Stack<Character> Redo)
    {

        // Stores the top element
        // of the stack
        char X = Redo.peek();

        // Erase the top element
        // of the stack
        Redo.pop();

        // Push an element to
        // the top of the stack
        Undo.push(X);
    }

    // Function to perform
    // "READ" operation
    static void READ(Stack<Character> Undo)
    {

        // Store elements of stack
        // in reverse order
        Stack<Character> revOrder = new Stack<Character>();

        // Traverse Undo stack
        while (Undo.size() > 0)
        {

            // Push an element to
            // the top of stack
            revOrder.push(Undo.peek());

            // Erase top element
            // of stack
            Undo.pop();
        }
        // bottom: b top: a

        System.out.println("READ");
        revOrder.stream().forEach(System.out::println);

        while (revOrder.size() > 0)
        {

            // Print the top element
            // of the stack
            System.out.print(revOrder.peek());
            Undo.push(revOrder.peek());

            // Erase the top element
            // of the stack
            revOrder.pop();
        }

        System.out.println(" ");
    }

    // Function to perform the
    // queries on the document
    static void QUERY(String[] Q)
    {
        // Stores total count
        // of queries
        int N = Q.length;

        // Traverse all the query
        for (int i = 0; i < N; i++)
        {
            if(Q[i] == "UNDO")
            {
                UNDO(Undo, Redo);
            }
            else if(Q[i] == "REDO")
            {
                REDO(Undo, Redo);
            }
            else if(Q[i] == "READ")
            {
                READ(Undo);
            }
            else
            {
                WRITE(Undo, Q[i].charAt(6));
            }
        }
    }
    public static void main(String[] args) {
        String[] Q = { "WRITE A", "WRITE B", "WRITE C", "UNDO", "READ", "REDO", "WRITE" };
        QUERY(Q);
    }

    /*
    class TextEditor {
  constructor(input) {
    this.input = input;
    this.output = [];
    this.history = [];
    this.forUndo = [];
    this.select = []; // start, end
  }

  textEditor() {
    let sortedInstrcutors = this.input.sort((a, b) => a[0] - b[0]);
    for (let instructor of sortedInstrcutors) {
      //  Append
      if (instructor[1] === "APPEND") {
        if (this.select.length && this.output.length > 0) {
          let last = this.output.pop();
          let newLast =
            last.slice(0, this.select[0]) +
            instructor[2] +
            last.slice(this.select[1]);
          this.output.push(newLast);
        } else {
          this.output.push(instructor[2]);
          this.forUndo.push([instructor[2], "APPEND"]);
          this.history = [];
        }
      } else if (instructor[1] === "SELECT") {
        let start = instructor[2],
          end = instructor[3];
        this.select = [start, end];
      } else if (instructor[1] === "BACKSPACE") {
        if (this.output.length) {
          let last = this.output.pop();
          this.forUndo.push([last, "BACKSPACE"]);
          if (this.select.length) {
            let [start, end] = this.select;
            let newLast = last.slice(0, start) + last.slice(end);
            this.output.push(newLast);
          } else {
            let newLast = last.slice(0, last.length - 1);
            if (newLast.length) this.output.push(newLast);
          }
        }
      } else if (instructor[1] === "UNDO") {
        // BUG
        if (this.forUndo.length) {
          let curUndo = this.forUndo.pop();
          if (curUndo[1] === "APPEND") {
            this.output.pop();
            this.history.push(curUndo);
          } else if (curUndo[1] === "BACKSPACE") {
            if (this.output.length > 0) {
              let saveHistory = this.output.pop();
              this.output.push(curUndo[0]);
              curUndo[0] = saveHistory;
              this.history.push(curUndo);
            }
          }
        }
      } else if (instructor[1] === "REDO") {
        if (this.history.length) {
          let curRedo = this.history.pop();
          if (curRedo[1] === "APPEND") {
            this.output.push(curRedo[0]);
          } else if (curRedo[1] === "BACKSPACE") {
            if (this.output.length > 0) {
              this.output.pop();
              this.output.push(curRedo[0]);
            }
          }
        }
      } else if (instructor[1] === "BOLD") {
        if (this.select.length) {
          let cur = this.output.pop();
          let [start, end] = this.select;
          let newCur =
            cur.slice(0, start) +
            "*" +
            cur.slice(start, end) +
            "*" +
            cur.slice(end);
          this.output.push(newCur);
        }
      }
    }
    return this.output.join(" ");
  }
}

let input = [
  ["0", "APPEND", "HEY YOU ff"],
  ["1", "BACKSPACE"],
  ["2", "BACKSPACE"]
];

let t = new TextEditor(input);

console.log(t.textEditor());
     */
}

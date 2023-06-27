package com.github.exercise.date20230627.day5;

import java.io.*;
import java.util.Scanner;

public class Tree {
    // StreamTokenizer类接受一个输入流并将其解析为“令牌”，允许每次读取一个令牌, 解析过程由一个表和许多可以设置为各种状态的标志控制。
    static StreamTokenizer cin = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    // 加速输出 将格式化的对象表示形式打印到文本输出流。这个类实现了在PrintStream中找到的所有打印方法。
    // 它不包含用于写入原始字节的方法，对于这种方法，程序应该使用未编码的字节流。
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    static final int N = (int) (5e5 + 100);
    static int []c = new int[N];
    static int n, m;

    static void add(int x, int val) {
        for(; x <= n; x += x&-x) {
            c[x] += val;
        }
    }

    static int query(int x) {
        int res = 0;
        for(; x > 0; x -= x&-x) {
            res += c[x];
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        cin.nextToken();
        n = (int) cin.nval;
        cin.nextToken();
        m = (int) cin.nval;
        for(int i = 1; i <= n; i ++) {
            cin.nextToken();
            int x = (int) cin.nval;
            add(i, x);
        }
        for(; m > 0; m --) {
            int op, x, y;
            cin.nextToken(); op = (int) cin.nval;
            cin.nextToken(); x = (int) cin.nval;
            cin.nextToken(); y = (int) cin.nval;
            if(op == 1) {
                add(x, y);
            } else {
                out.println(query(y) - query(x - 1));
            }
        }
        out.flush();
    }
}

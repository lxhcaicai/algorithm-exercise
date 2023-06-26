package com.github.exercise.date20230625.day4;

import java.io.*;

public class SegmentTree {
    // StreamTokenizer类接受一个输入流并将其解析为“令牌”，允许每次读取一个令牌, 解析过程由一个表和许多可以设置为各种状态的标志控制。
    static StreamTokenizer cin = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    // 加速输出 将格式化的对象表示形式打印到文本输出流。这个类实现了在PrintStream中找到的所有打印方法。
    // 它不包含用于写入原始字节的方法，对于这种方法，程序应该使用未编码的字节流。
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

    final static int N = (int) (1e5 + 100);

    static int a[] = new int[N];

    static  class Segment{
        int l, r;
        long dat, lz;
    };

    static Segment t[] = new Segment[N << 2];

    static void pushup(int p) {
        int lc = p << 1;
        int rc = p << 1 | 1;
        t[p].dat = t[lc].dat + t[rc].dat;
    }

    static  void build(int p, int l, int r) {
        t[p]=new Segment();
        t[p].l = l;
        t[p].r = r;
        //  System.out.println(l + " " + r + " " + p );
        if (l == r) {
            t[p].dat = a[l];
            return;
        }
        int lc = p << 1;
        int rc = p << 1 | 1;
        int mid = (t[p].l + t[p].r) >> 1;

        build(lc, l, mid);
        build(rc, mid + 1, r);
        pushup(p);
    }

    static void spread(int p) {
        if (t[p].lz == 0) {
            return;
        }
        int lc = p << 1;
        int rc = p << 1 | 1;
        t[lc].dat += (t[lc].r - t[lc].l + 1) * t[p].lz;
        t[rc].dat += (t[rc].r - t[rc].l + 1) * t[p].lz;
        t[lc].lz += t[p].lz;
        t[rc].lz += t[p].lz;
        t[p].lz = 0;

    }

    static  void  update(int p, int l, int r, int val) {
        if (l <= t[p].l && t[p].r <= r) {
            t[p].dat += (t[p].r - t[p].l + 1) * val;
            t[p].lz += val;
            return;
        }
        spread(p);
        int lc = p << 1;
        int rc = p << 1 | 1;
        int mid = (t[p].l + t[p].r) >> 1;
        if (l <= mid) {
            update(lc, l, r, val);
        }
        if (r > mid) {
            update(rc, l, r, val);
        }
        pushup(p);
    }

    static long query(int p, int l, int r) {
        if (l <= t[p].l && t[p].r <= r) {
            return t[p].dat;
        }
        spread(p);
        long val = 0;
        int lc = p << 1;
        int rc = p << 1 | 1;
        int mid = (t[p].l + t[p].r) >> 1;

        if (l <= mid) {val += query(lc, l, r);}
        if (r > mid) {val += query(rc, l, r); }
        return val;
    }

    public static void main(String[] args) throws IOException {
        cin.nextToken(); int n=(int)cin.nval;
        cin.nextToken(); int m=(int)cin.nval;

        for(int i = 1; i <= n; i ++) {
            cin.nextToken(); a[i]=(int)cin.nval;
        }
        build(1, 1, n);

        for(; m > 0; m --) {
            cin.nextToken(); int op =(int)cin.nval;
            cin.nextToken(); int x =(int)cin.nval;
            cin.nextToken(); int y = (int)cin.nval;

            if (op == 1) {
                cin.nextToken(); int k = (int)cin.nval;
                update(1, x, y, k);
            } else {
                out.println(query(1, x, y));
            }
        }
        out.flush();
    }
}

package dev.wpei;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArrayDeque {
    private ArrayList<Integer> list;

    public ArrayDeque() {
        list = new ArrayList<>();
    };

    public void push(Integer e) {
        list.add(e);
    }

    public void print() {
        StringBuffer str = new StringBuffer("Queued: ");
        for(Integer i : list) {
            str.append(i + ",");
        }
        str.deleteCharAt(str.length()-1);
        str.append("\n");
        System.out.println(str);
    }

}

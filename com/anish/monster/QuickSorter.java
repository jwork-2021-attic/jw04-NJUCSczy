package com.anish.monster;


import java.util.LinkedList;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {

    private T[] a;

    @Override
    public void load(T[] a) {
        this.a = a;
    }

    private void swap(int i, int j) {
        T temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        LinkedList<int[]> stack = new LinkedList<>();
        stack.addFirst(new int[]{0, a.length - 1});
        while (!stack.isEmpty()) {
            int[] tuple = stack.removeFirst();
            int start = tuple[0], end = tuple[1], i = tuple[0], j = tuple[1];
            while (j > i) {
                while (j > i && a[j].compareTo(a[start]) > 0) {
                    j--;
                }
                while (j > i && a[i].compareTo(a[start]) <= 0) {
                    i++;
                }
                if (j > i) {
                    swap(i, j);
                }
            }
            if (start != i) {
                swap(i,start);
            }
    
            if (i - 1 > start) {
                stack.addFirst(new int[]{0, i - 1});
            }
            if (j + 1 < end) {
                stack.addFirst(new int[]{j + 1, end});
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }



}

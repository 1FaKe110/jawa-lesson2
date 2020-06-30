package com.company.HW;

import javax.lang.model.type.NullType;
import javax.print.attribute.standard.MediaSize;
import java.util.Arrays;

public class Array {

    private int arr[];
    private int size;
    private boolean isSorted;

    private Array() {
        this.isSorted = false;
    }

    public Array(int capacity) {
        arr = new int[capacity];
        this.size = 0;
    }

    public Array(int... args) {
        this.size = args.length;
        this.arr = args;
    }

    @Override
    public String toString() {

        if (arr == null) return "null";
        int iMax = size - 1;
        if (iMax == -1) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        int i = 0;

        while (true) {
            b.append(arr[i]);
            if (i == iMax) return b.append(']').toString();
            b.append(", ");
            i++;
        }
    }

    private int length() {
        return size;
    }

    public int get(int index) {
        if (index >= size || index <= 0) throw new ArrayIndexOutOfBoundsException();
        return arr[index];
    }

    public void set(int index, int value) {
        if (index >= size || index <= 0) throw new ArrayIndexOutOfBoundsException();
        arr[index] = value;
    }

    private void increaseCapacity() {

        int[] temp = arr;
        arr = new int[size * 2];
        System.arraycopy(temp, 0, arr, 0, size);

    }

    public void append(int value) {
        isSorted = false;
        if (size >= arr.length) increaseCapacity();
        arr[size++] = value;
    }

    public int find(int value) {
        /* returns index */
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) return i;
        }
        return -1;
    }

    public int findAll(int value) {
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (value == arr[i]) counter += 1;
        }
        return counter;
    }

    public boolean hasValue(int value) {
        if (!isSorted) throw new RuntimeException("try find, or sort it before using this function");

        int l = 0;
        int r = size;
        int m;

        while (l < r) {
            m = (l + r) / 2;
            if (value == arr[m]) return true;
            else if (value < arr[m]) r = m;
            else l = m + 1;
        }
        return false;

    }

    private void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public void sortBubble() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
        isSorted = true;
    }

    public void sortSelect() {
        for (int flag = 0; flag < size; flag++) {
            int cMin = flag;
            for (int rem = flag + 1; rem < size; rem++) {
                if (arr[rem] < arr[cMin]) cMin = rem;
            }
            swap(flag, cMin);
        }
        isSorted = true;
    }

    public void sortInsert() {
        for (int out = 0; out < size; out++) {
            int temp = arr[out];
            int in = out;
            while (in > 0 && arr[in - 1] >= temp) {
                arr[in] = arr[in - 1];
                in--;
            }
            arr[in] = temp;
        }
        isSorted = true;
    }

    public int deleteLast() {
        if (size == 0) throw new ArrayIndexOutOfBoundsException(-1);
        return arr[--size];
    }

    public void deleteValue(int value) {
        // удаляет первое вхождение искомого числа
        isSorted = false;
        int ind = find(value);

        for (int i = ind; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        deleteLast();
    }

    public void deleteAll(int value) {
        int elems = findAll(value);

        for (int i = 0; i < elems; i++) {
            deleteValue(value);
        }
    }

    public void deleteIndex(int index) {
        isSorted = false;
        if (index >= size) throw new ArrayIndexOutOfBoundsException();

        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        deleteLast();
    }

    public void sortCounting() {
        int[] check_list = new int[1];

        int cMin;

        for (int i = 0; i < size; i++) {
            cMin = arr[i];
            for (int j = 0; j < size; j++) {
                if (arr[i] < cMin) {
                    cMin = arr[i];
                    System.out.println(cMin);
                }
            }
            boolean check = false;
            for (int j = 0; j < size; j++) {
                if (check_list[j] == cMin) {
                    check = false;
                    break;
                } else {
                    check = true;
                }
            }
            if (check) check_list[i] = cMin;
        }
        System.out.println(Arrays.toString(check_list));
        isSorted = true;
    }

    // доделать
    // insert(int index, int value);
    // sortCounting()
}

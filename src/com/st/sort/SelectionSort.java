package com.st.sort;

import java.util.Arrays;

public class SelectionSort {
    public static void sort(int[] array){
        int temp;
        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[i]>array[j]){
                    temp=array[i];
                    array[i]=array[j];
                    array[j]=temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a={1,3,4,2,5,7,9,22,5,8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}

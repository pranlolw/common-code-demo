package com.st.sort;

import java.util.Arrays;

public class QieFenSort {
    public static void sort(int[] nums){
        sort(nums,0,nums.length-1);
    }
    public static void sort(int[] nums,int startindex,int endindex){
        int qiefen=startindex;
        while(true){
            if(nums[startindex]>nums[qiefen]){
                int a=nums[startindex];
                nums[startindex]=nums[qiefen];
                nums[qiefen]=a;
            }
            startindex++;
            if(nums[endindex]<nums[qiefen]){
                int a=nums[endindex];
                nums[endindex]=nums[qiefen];
                nums[qiefen]=a;
            }
            endindex--;
            if(startindex>=endindex){
                break;
            }
                sort(nums,startindex,qiefen);
                sort(nums,qiefen,endindex);

        }

    }
    public static void main(String[] args) {
        int[] a={1,3,4,2,5,7,9,22,5,8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}

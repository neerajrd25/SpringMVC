package com.example.neeraj;

import java.util.Arrays;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// HAsh Code Remain Consistent 
		Fruit f = new Fruit();
		f.i=10;
		System.out.println(f);

		System.out.println(f.hashCode());
		// Array Sort
		int[] arr = {13, 7, 6, 45, 21, 9, 101, 102};
		System.out.println(Arrays.toString(arr));
		// dual pivot quicksort
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		int [] fillArr ={10,20,30};
		//Arrays.fill(arr, 500); //fill all the value with 500
		Arrays.fill(arr, 2, 4, 500);
		System.out.println(Arrays.toString(arr));
		// check two arrays equal
		System.out.println(Arrays.equals(arr, fillArr));
		
		
	}

}

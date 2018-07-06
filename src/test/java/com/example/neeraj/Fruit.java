package com.example.neeraj;

public class Fruit {
	int i;
	public String toString() {
	    System.out.println(hashCode());
		return getClass().getName() + "@" + Integer.toHexString(hashCode());
	}
}

package com.Algos.TwoFour;

import java.util.Comparator;
//Included for needed comparator
public class DefaultComparator<T> implements Comparator<T> {
	@SuppressWarnings("unchecked")
	public int compare(T a, T b) {
		return ((Comparable<T>)a).compareTo(b);
	}
}
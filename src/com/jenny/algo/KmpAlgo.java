package com.jenny.algo;

public class KmpAlgo {
	// return the start index of the first matched substring
	public int searchMatchedString(String str, String pattern){
		
		// build Next array:
		int i = 0, j = -1;
		int pl = pattern.length(), sl = str.length();
		int[] next = new int[pl];
		next[0] = -1;
		while(i < pl - 1){ //Note: needs to be pl - 1
			if(j == -1 || pattern.charAt(i) == pattern.charAt(j)){
				++i;
				++j;
				next[i] = j;
			} else {
				j = next[j];
			}
		}
		
		// search matched string:
		i = 0;
		j = 0;
		while(i < sl && j < pl){
			if(j == -1 || str.charAt(i) == pattern.charAt(j)){
				++i;
				++j;
			} else {
				j = next[j];
			}
		}
		return j == pl ? i - j : -1;		
	}
}

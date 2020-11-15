package com.jenny;

import com.jenny.algo.KmpAlgo;

public class Main {

	public static void main(String[] args) {
		KmpAlgo ka = new KmpAlgo();
		System.out.println(ka.searchMatchedString("ababababca", "abababca"));
		
	}

}

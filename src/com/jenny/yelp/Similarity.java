package com.jenny.yelp;

public class Similarity {
	
	public double getCharSimilarity(String str1, String str2) {
		if (str1.length() == 0 || str2.length() == 0) return 0;
		int commonCharsLength = getLongestCommonChars(str1, str2).length();
		double charSimilarity = (double)commonCharsLength / (str1.length() + str2.length() - commonCharsLength);
		return charSimilarity;
	}
	
	public double getWordSimilarity(String str1, String str2) {
		if (str1.length() == 0 || str2.length() == 0) return 0;
		String[] strArr1 = str1.split(" ");
		String[] strArr2 = str2.split(" ");
		int commonWordsLength = getLongestCommonWords(strArr1, strArr2);
		double wordSimilarity = (double)commonWordsLength / (strArr1.length + strArr2.length - commonWordsLength);
		return wordSimilarity;
	}
	
	private int getLongestCommonWords(String[] strArr1, String[] strArr2) {
		int m = strArr1.length, n = strArr2.length;
		int[][] dp = new int[m + 1][n + 1];
		int maxLength = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (strArr1[i].equals(strArr2[j])) dp[i + 1][j + 1] = dp[i][j] + 1;
				if (dp[i + 1][j + 1] > maxLength) {
					maxLength = dp[i + 1][j + 1];
				}
			}
		}
		return maxLength;
	}
	
	private String getLongestCommonChars(String str1, String str2) {
		int m = str1.length(), n = str2.length();
		int[][] dp = new int[m + 1][n + 1];
		int maxLengthStartIndex = 0;
		int maxLength = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (str1.charAt(i) == str2.charAt(j)) dp[i + 1][j + 1] = dp[i][j] + 1;
				if (dp[i + 1][j + 1] > maxLength) {
					maxLength = dp[i + 1][j + 1];
					maxLengthStartIndex = i - maxLength + 1;
				}
			}
		}
		return str1.substring(maxLengthStartIndex, maxLengthStartIndex + maxLength);
	}
}

package com.jenny.yelp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrefixAndNonPrefix {
	// return strings with given prefix (string with space split also counts)
	public List<String> searchPrefix(String[] inputStrings, String prefix){
		
		// construct input dictionary: 
		// key: words split by space
		// value: original string
		Map<String, List<String>> inputDic = new HashMap<>();
		for(String origStr: inputStrings){
			String[] words = origStr.toLowerCase().split(" ");
			for(String word: words){
				inputDic.putIfAbsent(word, new ArrayList<>());
				inputDic.get(word).add(origStr);
			}
		}
		
		// build Trie:
		TrieNode root = new TrieNode();
		for(String key: inputDic.keySet()){
			TrieNode node = root;
			for(char c: key.toCharArray()){
				node.children.putIfAbsent(c, new TrieNode());
				node = node.children.get(c);
				node.origStrings.addAll(inputDic.get(key));
			}
		}
		
		// Search prefix:
		List<String> result = new ArrayList<>();
		TrieNode cur = root;
		prefix = prefix.toLowerCase();
		for(int i = 0; i < prefix.length(); i++){
			if(!cur.children.containsKey(prefix.charAt(i))) return result;
			cur = cur.children.get(prefix.charAt(i));
		}
		result.addAll(cur.origStrings);
		
		// add non prefix strings(KMP):
		result.clear();
		searchMatchedString(inputStrings, prefix, result); // O(N)=M*N, M is the number of inputStrings, N is the average length of each inputString
		
		return result;
	}
	
	private void searchMatchedString(String[] inputStrings, String prefix, List<String> result) {
		for (String str: inputStrings) {
			int index = getFirstMatchedIndex(str.toLowerCase(), prefix);
			if (index < 0) continue;
			if (index == 0 || str.charAt(index - 1) == ' ') {
				result.add(0, str);
			} else {
				result.add(str);
			}
		}
	}
	
	private int getFirstMatchedIndex(String str, String pattern) {
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
	
	private class TrieNode {
		private Map<Character, TrieNode> children;
		private Set<String> origStrings;
		public TrieNode(){
			children = new HashMap<>();
			origStrings = new HashSet<>();
		}
	}
}

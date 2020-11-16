package com.jenny.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Trie {
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
		return result;
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

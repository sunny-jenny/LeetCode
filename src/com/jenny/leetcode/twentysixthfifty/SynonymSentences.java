package com.jenny.leetcode.twentysixthfifty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/*
Given a list of pairs of equivalent words synonyms and a sentence text, Return all possible synonymous sentences sorted lexicographically.
 
Example 1:
Input:
	synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]],
	text = "I am happy today but was sad yesterday"
Output:
	["I am cheerful today but was sad yesterday",
	"I am cheerful today but was sorrow yesterday",
	"I am happy today but was sad yesterday",
	"I am happy today but was sorrow yesterday",
	"I am joy today but was sad yesterday",
	"I am joy today but was sorrow yesterday"]
 
Constraints:
	0 <= synonyms.length <= 10
	synonyms[i].length == 2
	synonyms[0] != synonyms[1]
	All words consist of at most 10 English letters only.
	text is a single space separated sentence of at most 10 words.
 */
public class SynonymSentences {
	public List<String> generateSentences(List<List<String>> synonyms, String text) {
		Map<String, Set<String>> map = new HashMap<>();
		for (List<String> s: synonyms) {
			map.putIfAbsent(s.get(0), new HashSet<>());
			map.putIfAbsent(s.get(1), new HashSet<>());
			map.get(s.get(0)).add(s.get(1));
			map.get(s.get(1)).add(s.get(0));			
		}

		// group all the synonyms in a map 
		String[] words = text.split(" ");
		Map<String, TreeSet<String>> group = new HashMap<>();
		for (String word: words) {
			TreeSet<String> set = new TreeSet<>();
			findSameGroupNodes(word, set, map);
			group.put(word, set);
		}
		
		// DFS: replace word in text
		List<String> result = new ArrayList<>();
		dfs(group, words, "", 0, result);
		return result;
	}
	
	private void dfs(Map<String, TreeSet<String>> group, String[] words, String temp, int index, List<String> result) {
		if (index == words.length) {
			result.add(temp.trim());
			return;
		}
		for (String s: group.get(words[index])) {
			dfs(group, words, temp + s + " ", index + 1, result);
		}
	}
	
	private void findSameGroupNodes(String word, Set<String> set, Map<String, Set<String>> map) {
		Queue<String> queue = new LinkedList<>();
		queue.add(word);
		set.add(word);
		while (!queue.isEmpty()) {
			String cur = queue.poll();
			if (map.containsKey(cur)) {
				for (String neighbor: map.get(cur)) {
					map.get(neighbor).remove(cur);
					set.add(neighbor);
					queue.offer(neighbor);
				}
			}
		}
	}
}

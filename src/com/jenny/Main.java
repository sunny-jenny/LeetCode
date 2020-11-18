package com.jenny;

import java.util.List;

import com.jenny.leetcode.fifthfifty.WordSearchII;

public class Main {

	public static void main(String[] args) {
//		KmpAlgo ka = new KmpAlgo();
//		System.out.println(ka.searchMatchedString("ababababca", "abababca"));
		
//		KnuthShuffle ks = new KnuthShuffle();
//		Player[] players = {ks.new Player("A", "1"), ks.new Player("B", "1"), ks.new Player("C", "2"),
//				ks.new Player("D", "2"), ks.new Player("E", "3"), ks.new Player("F", "3"), ks.new Player("G", "4")};
//		try {
//			ks.randomPair(players);
//			for (Player p: players) {
//				System.out.println(p.getTeam() + p.getName());
//			}
//		} catch (InvalidShuffleException e) {
//			e.printStackTrace();
//		}
		
//		Trie t = new Trie();
//		List<String> res = t.searchPrefix(new String[] {"Burger King", "Good burger", "Walburger", "sde burger wed", "Burr king"}, "bur");
//		for(String r: res){
//			System.out.println(r);
//		}
		
//		TopKFrequentWords tk = new TopKFrequentWords();
//		List<String> res = tk.topKFrequent(new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4);
//		for(String r: res){
//			System.out.println(r);
//		}
		
		WordSearchII ws = new WordSearchII();
		char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
		List<String> res = ws.findWords(board, new String[] {"oath","pea","eat","rain"});
		for(String r: res){
			System.out.println(r);
		}
	}

}

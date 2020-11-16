package com.jenny;

import com.jenny.algo.KnuthShuffle;
import com.jenny.algo.KnuthShuffle.InvalidShuffleException;
import com.jenny.algo.KnuthShuffle.Player;

public class Main {

	public static void main(String[] args) {
//		KmpAlgo ka = new KmpAlgo();
//		System.out.println(ka.searchMatchedString("ababababca", "abababca"));
		
		KnuthShuffle ks = new KnuthShuffle();
		Player[] players = {ks.new Player("A", "1"), ks.new Player("B", "1"), ks.new Player("C", "2"),
				ks.new Player("D", "2"), ks.new Player("E", "3"), ks.new Player("F", "3"), ks.new Player("G", "4")};
		try {
			ks.randomPair(players);
			for (Player p: players) {
				System.out.println(p.getTeam() + p.getName());
			}
		} catch (InvalidShuffleException e) {
			e.printStackTrace();
		}
	}

}

package com.jenny.leetcode.fifthfifty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearchII {
	public List<String> findWords(char[][] board, String[] words) {
        int row = board.length;
        int col = board[0].length;
        
        TrieNode root = buildTrie(words);
        
        List<String> res = new ArrayList<>();
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                dfs(board, root, i, j, res);
            }
        }
        return res;
    }
    
    private void dfs(char[][] board, TrieNode root, int i, int j, List<String> res) {
    	int row = board.length;
        int col = board[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] == '#' || !root.children.containsKey(board[i][j])) return;

        char c = board[i][j];
        TrieNode cur = root;
        cur = cur.children.get(board[i][j]);
        if (cur.word != null) {
            res.add(cur.word);
            cur.word = null;
             return;
        }
        
        board[i][j] = '#';
        dfs(board, cur, i - 1, j, res);
        dfs(board, cur, i + 1, j, res);
        dfs(board, cur, i, j - 1, res);
        dfs(board, cur, i, j + 1, res);
        board[i][j] = c;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w: words) {
            TrieNode node = root;
            for (char c: w.toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
            }
            node.word = w;
        }
        return root;
    }
    
    private class TrieNode {
        private Map<Character, TrieNode> children;
        private String word;
        public TrieNode() {
            children = new HashMap<>();
            word = null;
        }
    }
}

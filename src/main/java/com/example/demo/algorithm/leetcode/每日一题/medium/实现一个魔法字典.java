package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.HashMap;
import java.util.Map;

public class 实现一个魔法字典 {
    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "hallo", "leetcode"});
        System.out.println(magicDictionary.search("hello"));
    }
}

class MagicDictionary {

    TrieNode root;

    public MagicDictionary() {
        root = new TrieNode();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            TrieNode cur= root;
            for (int i = 0; i < word.length(); i++) {
                int id = word.charAt(i) - 'a';
                if (cur.children[id] == null) {
                    cur.children[id] = new TrieNode();
                }
                cur = cur.children[id];
            }
            cur.isFinished = true;
        }
    }

    public boolean search(String searchWord) {
        return dfs(searchWord, root, 0, false);

    }

    private boolean dfs(String searchWord, TrieNode node, int pos, boolean modified) {
        if (pos == searchWord.length()) {
            return modified && node.isFinished;
        }
        int id = searchWord.charAt(pos) - 'a';
        if (node.children[id] != null) {
            if (dfs(searchWord, node.children[id], pos + 1, modified)) {
                return true;
            }
        }
        // 一次修改机会
        if (!modified) {
            for (int i = 0; i < 26; i++) {
                if (i != id && node.children[i] != null) {
                    if (dfs(searchWord, node.children[i], pos + 1, true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

class TrieNode {
    boolean isFinished;
    TrieNode[] children;

    public TrieNode() {
        isFinished = false;
        children = new TrieNode[26];
    }
}

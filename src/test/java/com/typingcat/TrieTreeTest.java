package com.typingcat;

import java.util.ArrayList;
import java.util.List;

public class TrieTreeTest {

    public static class TrieNode {
        char c;
        private TrieNode[] slot = new TrieNode[26];
        int count;
        int prefix;

    }

    public static void insert(TrieNode root, String words) {
        char[] chars = words.toCharArray();
        for (char aChar : chars) {
            int charIndex = aChar - 'a';
            if (root.slot[charIndex] == null) {
                root.slot[charIndex] = new TrieNode();
            }

            root = root.slot[charIndex];
            root.c = aChar;
            root.prefix++;
        }
        root.count++;
    }

    public static int search(TrieNode root, String words) {
        char[] chars = words.toCharArray();
        for (char aChar : chars) {
            int charIndex = aChar - 'a';
            if (root.slot[charIndex] == null) {
                return -1;
            }
            root = root.slot[charIndex];
        }
        return root.count;
    }

    public static int countBySearchPrefix(TrieNode root, String words) {
        char[] chars = words.toCharArray();
        for (char aChar : chars) {
            int charIndex = aChar - 'a';
            if (root.slot[charIndex] == null) {
                return -1;
            }
            root = root.slot[charIndex];
        }
        return root.prefix;
    }


    public static void collect(TrieNode node, String pre, List<String> queue) {
        if (node.count == 1) {
            queue.add(pre);
            return;
        }

        for (int i = 0; i < node.slot.length; i++) {
            char c = (char) ('a' + i);
            collect(node.slot[i], pre + c, queue);
        }

    }


    public static ArrayList<String> searchPrefix(TrieNode root, String words) {



        char[] chars = words.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (char aChar : chars) {
            int charIndex = aChar - 'a';
            if (root.slot[charIndex] == null) {
                return null;
            }
            sb.append(aChar);
            root = root.slot[charIndex];
        }

        ArrayList<String> result = new ArrayList<>();
        if (root.prefix != 0) {
            for (int i = 0; i < root.slot.length; i++) {
                if (root.slot[i] != null) {
//                     recycFind(result,new StringBuilder(sb.toString()), root);
//                    if (s != null) {
//                        result.add(s);
//                    }
                }

            }

        }

        return result;
    }

    public static String recycFind(StringBuilder sb, TrieNode root) {

        TrieNode[] slot = root.slot;
        for (int i = 0; i < slot.length; i++) {
            TrieNode trieNode = slot[i];
            if (trieNode != null) {
                char c = (char) (i + 'a');
                sb.append(c);
                return recycFind(sb, trieNode);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        TrieNode trieNode = new TrieNode();
        insert(trieNode, "hello");
        insert(trieNode, "help");
//        System.out.println(search(trieNode, "h"));
//        System.out.println((searchPrefix(trieNode, "he")));
        ArrayList<String> queue = new ArrayList<>();
        collect(trieNode, "he", queue);

    }

}

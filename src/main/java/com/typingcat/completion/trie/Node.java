package com.typingcat.completion.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {
    public char c;
    public Node[] slot = new Node[26];
    public int count;
    public int prefix;
    public String word;
    public String explain;
    public static int counter = 0;


    public static List<Node> searchPrefix(Node root, String words, int resultLimit) {

        char[] chars = words.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (char aChar : chars) {
            int charIndex = aChar - 'a';
            if (charIndex > root.slot.length || charIndex < 0 || root.slot[charIndex] == null) {
                return Collections.emptyList();
            }
            sb.append(aChar);
            root = root.slot[charIndex];
        }


        ArrayList<Node> result = new ArrayList<>();
        if (root.prefix != 0) {
            for (int i = 0; i < root.slot.length; i++) {
                if (root.slot[i] != null) {
                    char c = (char) (i + 'a');
                    collect(root.slot[i], new StringBuilder(sb).append(c).toString(), result, resultLimit);
                    if (result.size() >= resultLimit) {
                        return result;
                    }
                }

            }

        }

        return result;
    }


    public static void collect(Node node, String pre, List<Node> queue, int resultLimit) {
        if (node == null) {
            return;
        }

        if (node.count > 0) {
            node.word = pre;
            queue.add(node);
            if (queue.size() >= resultLimit) {
                return;
            }
        }

        for (int i = 0; i < node.slot.length; i++) {
            char c = (char) ('a' + i);
            if (node.slot[i] != null) {
                collect(node.slot[i], pre + c, queue, resultLimit);
            }

        }

    }

    public static void insert(Node root, String words, String explain) {
        char[] chars = words.toCharArray();
        for (char aChar : chars) {
            int charIndex = aChar - 'a';
            if (root.slot[charIndex] == null) {
                root.slot[charIndex] = new Node();
            }
            root = root.slot[charIndex];
            root.c = aChar;
            root.prefix++;
        }

        root.explain = explain;
        root.count++;
        if (root.count == 1) {
            counter++;
        }
    }

}

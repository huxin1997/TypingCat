package com.typingcat;

import java.util.LinkedList;

public class SearchTest {

    static class Node {
        private String words;
        private LinkedList<Node> lastNodes;
    }

    private static void insert(Node node, String words) {
        if (words.startsWith(words)) {
            if (node.lastNodes == null) {
                node.lastNodes = new LinkedList<>();
            }
            Node child = new Node();
            child.words = words;
            node.lastNodes.add(child);
        }
    }

    public static void main(String[] args) {


    }





}

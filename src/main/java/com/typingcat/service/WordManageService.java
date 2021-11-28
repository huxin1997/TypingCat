package com.typingcat.service;

import com.intellij.openapi.components.Service;
import com.typingcat.completion.trie.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huxin
 */

@Service
public final class WordManageService {

    private final Node wordsTree = new Node();

    private final Pattern compile = Pattern.compile("^[A-Za-z]+$");

    public WordManageService() {
        loadDictionary();
    }

    private void loadDictionary() {
//        ProgressManager.getInstance().run(new Task.Backgroundable(myProject, "TypingCat") {
//            @Override
//            public void run(@NotNull ProgressIndicator indicator) {
//                indicator.setText("正在加载词典");
//                indicator.setFraction(0.5);
////                indicator.setIndeterminate(true);
//            }
//        });
        loadFile("/dictionary/simple.txt");
        loadFile("/dictionary/java.txt");
        loadFile("/dictionary/gre.txt");
        loadFile("/dictionary/collins-lv5.txt");
        loadFile("/dictionary/cet4.txt");
        System.out.println("单词总数：" + Node.counter);
    }

    public Node getWordsTree() {
        return wordsTree;
    }


    private void loadFile(String path) {
        try {
            String line = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path), StandardCharsets.UTF_8));

            while ((line = br.readLine()) != null) {
                String[] wordInfo = line.split("#");
                if (!compile.matcher(wordInfo[0]).matches()) {
                    System.out.println(wordInfo[0]);
                    continue;
                }
                simplifyExplanations(wordInfo);
                Node.insert(wordsTree, wordInfo[0].toLowerCase().trim(), wordInfo.length == 2 ? wordInfo[1] : "");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final Pattern explainPattern = Pattern.compile("(\\w{1,3}\\.)(.*?)(?=\\w{1,3}\\.|$)");


    private void simplifyExplanations(String[] wordInfo) {
        wordInfo[0] = wordInfo[0].toLowerCase().trim();
        StringBuilder sb = new StringBuilder();
        if (wordInfo.length == 2) {
            Matcher matcher = explainPattern.matcher(wordInfo[1]);
            boolean hasMatch = matcher.find();
            String singleExplation = wordInfo[1];
            do {
                if (hasMatch) {
                    singleExplation = matcher.group(2);
                    sb.append(matcher.group(1));
                }

                sb.append(Arrays.stream(singleExplation.split("[；;,，]")).min(Comparator.comparingInt(String::length)).get());
                wordInfo[1] = sb.toString();
            } while (hasMatch = matcher.find());
        }

    }

}

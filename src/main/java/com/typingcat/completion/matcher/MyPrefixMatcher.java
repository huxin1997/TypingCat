package com.typingcat.completion.matcher;

import com.intellij.codeInsight.completion.PrefixMatcher;
import com.intellij.codeInsight.completion.impl.CamelHumpMatcher;
import com.intellij.psi.codeStyle.MinusculeMatcher;
import com.intellij.psi.codeStyle.NameUtil;
import org.jetbrains.annotations.NotNull;

/**
 * @author huxin
 */
public class MyPrefixMatcher extends PrefixMatcher {

    private final MinusculeMatcher matcher;

    public MyPrefixMatcher(String prefix) {
        super(prefix);
        matcher = NameUtil.buildMatcher(CamelHumpMatcher.applyMiddleMatching(getPrefix())).typoTolerant().build();
    }

    @Override
    public boolean prefixMatches(@NotNull String name) {
        return matcher.isStartMatch(name);
    }


    @Override
    public @NotNull
    PrefixMatcher cloneWithPrefix(@NotNull String prefix) {
        return new MyPrefixMatcher(prefix);
    }

}
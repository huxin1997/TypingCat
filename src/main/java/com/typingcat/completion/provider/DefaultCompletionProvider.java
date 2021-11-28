package com.typingcat.completion.provider;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.completion.impl.BetterPrefixMatcher;
import com.intellij.codeInsight.completion.impl.CamelHumpMatcher;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.NameUtil;
import com.intellij.util.ProcessingContext;
import com.typingcat.completion.trie.Node;
import com.typingcat.service.WordManageService;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author huxin
 */
public class DefaultCompletionProvider extends CompletionProvider<CompletionParameters> {


    private final WordManageService wordManageService;

    public DefaultCompletionProvider(WordManageService wordManageService) {
        this.wordManageService = wordManageService;
    }

    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                  @NotNull CompletionResultSet result) {
        PsiElement psiElement = parameters.getPosition();
        System.out.println(psiElement.getText());
        PrefixMatcher prefixMatcher = result.getPrefixMatcher();

        String prefix = prefixMatcher.getPrefix();

        boolean hasUpperCaseChar = StringUtil.hasUpperCaseChar(prefix);
        if (hasUpperCaseChar) {
            List<String> wordList = NameUtil.nameToWordsLowerCase(prefix);
            if (wordList.isEmpty()) {
                return;
            }
            prefix = wordList.get(wordList.size() - 1);
        }
        List<Node> list = Node.searchPrefix(wordManageService.getWordsTree(), prefix);
        if (!list.isEmpty()) {
            for (Node node : list) {
                LookupElementBuilder element = LookupElementBuilder
                    .create(hasUpperCaseChar ? StringUtil.capitalize(node.word) : node.word)
                    .withPresentableText(node.word)
                    .withIcon(AllIcons.Actions.WordsSelected)
                    .withBoldness(false)
//                    .withTailText("v.", true)
                    .withTypeText(node.explain)
                    .bold();
                NameUtil.MatcherBuilder matcherBuilder = NameUtil.buildMatcher(prefix);
                CamelHumpMatcher.applyMiddleMatching(prefix);
//                prefixMatcher.
//                CompletionResult completionResult = CompletionResult.wrap(element, prefixMatcher, CompletionSorter.emptySorter());
//                CompletionResultSet completionResultSet =
                CompletionResultSet completionResultSet =
                    result.withPrefixMatcher(new BetterPrefixMatcher(new PlainPrefixMatcher(prefix, false), 0));
//                completionResultSet.addElement(element);
                completionResultSet.addElement(element);
            }
        }
    }
}

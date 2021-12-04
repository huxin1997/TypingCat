package com.typingcat.completion.provider;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.PrefixMatcher;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.codeStyle.NameUtil;
import com.intellij.util.ProcessingContext;
import com.typingcat.completion.matcher.MyPrefixMatcher;
import com.typingcat.completion.trie.Node;
import com.typingcat.service.WordManageService;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

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

            List<LookupElementBuilder> collect = list.stream().map(node -> LookupElementBuilder
                    .create(hasUpperCaseChar ? StringUtil.capitalize(node.word) : node.word)
                    .withPresentableText(node.word)
                    .withIcon(AllIcons.Actions.WordsSelected)
                    .withBoldness(false)
                    .withTypeText(node.explain)
                    .bold())
                .collect(Collectors.toList());
            CompletionResultSet completionResultSet =
                result.withPrefixMatcher(new MyPrefixMatcher(prefix));
            completionResultSet.addAllElements(collect);
        }
    }
}

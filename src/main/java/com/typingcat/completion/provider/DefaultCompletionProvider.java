package com.typingcat.completion.provider;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.PrefixMatcher;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.util.ProcessingContext;
import com.typingcat.completion.matcher.MyPrefixMatcher;
import com.typingcat.completion.trie.Node;
import com.typingcat.service.WordManageService;
import com.typingcat.util.NameUtils;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

/**
 * @author huxin
 */
public class DefaultCompletionProvider extends CompletionProvider<CompletionParameters> {


    private final WordManageService wordManageService;

    private final InsertHandler<LookupElement> insertHandler = new DefaultInsertHandler();

    public DefaultCompletionProvider(WordManageService wordManageService) {
        this.wordManageService = wordManageService;
    }

    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                  @NotNull CompletionResultSet result) {
        PrefixMatcher prefixMatcher = result.getPrefixMatcher();
        String prefix = prefixMatcher.getPrefix();
        if (NameUtils.isCompositeName(prefix)) {
            prefix = NameUtils.getLastWord(prefix).toLowerCase(Locale.ROOT);
        }
        List<Node> list = Node.searchPrefix(wordManageService.getWordsTree(), prefix);

        if (list.isEmpty()) {
            result.restartCompletionOnAnyPrefixChange();
            return;
        }

        List<LookupElementBuilder> collect = list.stream().map(node -> LookupElementBuilder
                .create(node.word)
                .withPresentableText(node.word)
                .withIcon(AllIcons.Actions.Words)
                .withBoldness(false)
                .withTypeText(node.explain)
                .bold()
                .withInsertHandler(insertHandler))
            .collect(Collectors.toList());
        CompletionResultSet resultSet = result.withPrefixMatcher(new MyPrefixMatcher(prefix));
        resultSet.addAllElements(collect);
        resultSet.addLookupAdvertisement("单词数量" + list.size());
    }
}

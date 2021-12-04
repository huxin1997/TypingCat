package com.typingcat.completion.provider;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

/**
 * @author huxin
 */
public class DefaultInsertHandler implements InsertHandler<LookupElement> {
    @Override
    public void handleInsert(@NotNull InsertionContext context, @NotNull LookupElement item) {
        String text = context.getDocument().getText(new TextRange(context.getStartOffset() - 1, context.getStartOffset()));
        if (StringUtil.isEmpty(text)) {
            return;
        }
        char symbol = text.charAt(0);
        String insertText = item.getLookupString();
        if (Character.isLowerCase(symbol)) {
            insertText = StringUtil.capitalize(item.getLookupString());
        }
        context.getDocument().replaceString(context.getStartOffset(), context.getTailOffset(), insertText);
    }
}

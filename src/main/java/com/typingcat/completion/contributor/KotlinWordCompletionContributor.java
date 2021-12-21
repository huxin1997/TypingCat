package com.typingcat.completion.contributor;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.ElementType;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl;
import com.intellij.psi.tree.IElementType;
import com.typingcat.completion.provider.DefaultCompletionProvider;
import com.typingcat.service.WordManageService;
import org.jetbrains.kotlin.psi.KtParameter;
import org.jetbrains.kotlin.psi.KtParameterList;
import org.jetbrains.kotlin.psi.KtProperty;

import static com.intellij.patterns.PlatformPatterns.psiElement;

/**
 * @author huxin
 */
public class KotlinWordCompletionContributor extends CompletionContributor {

    protected CompletionProvider<CompletionParameters> provider;
    protected WordManageService wordManageService;

    public KotlinWordCompletionContributor() {
        init();
        extend(CompletionType.BASIC, psiElement(LeafPsiElement.class).withParents(KtParameter.class), provider);
        extend(CompletionType.BASIC, psiElement(LeafPsiElement.class)
                .beforeLeafSkipping(psiElement(ElementType.WHITE_SPACE), psiElement(ElementType.VAR_KEYWORD)), provider);

    }

    protected void init() {
        wordManageService = ApplicationManager.getApplication().getService(WordManageService.class);
        provider = new DefaultCompletionProvider(wordManageService);
    }

}

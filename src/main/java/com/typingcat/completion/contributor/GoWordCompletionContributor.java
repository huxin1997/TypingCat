package com.typingcat.completion.contributor;

import com.goide.psi.*;
import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.typingcat.completion.provider.DefaultCompletionProvider;
import com.typingcat.service.WordManageService;

import static com.intellij.patterns.PlatformPatterns.psiElement;

/**
 * @author huxin
 */
public class GoWordCompletionContributor extends CompletionContributor {

    protected CompletionProvider<CompletionParameters> provider;
    protected WordManageService wordManageService;

    public GoWordCompletionContributor() {
        init();
        extend(CompletionType.BASIC, psiElement(LeafPsiElement.class).withParent(GoFunctionDeclaration.class), provider);
        extend(CompletionType.BASIC, psiElement(LeafPsiElement.class).withParent(GoVarDefinition.class), provider);
        extend(CompletionType.BASIC, psiElement(LeafPsiElement.class).withParent(GoConstDefinition.class), provider);
        extend(CompletionType.BASIC, psiElement(LeafPsiElement.class).withParent(GoParamDefinition.class), provider);
        extend(CompletionType.BASIC, psiElement(LeafPsiElement.class).withParents(GoMethodSpec.class, GoInterfaceType.class), provider);
    }


    protected void init() {
        wordManageService = ApplicationManager.getApplication().getService(WordManageService.class);
        provider = new DefaultCompletionProvider(wordManageService);
    }
}

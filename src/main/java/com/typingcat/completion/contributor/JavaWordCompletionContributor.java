package com.typingcat.completion.contributor;

import com.intellij.codeInsight.completion.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.*;
import com.typingcat.completion.provider.DefaultCompletionProvider;
import com.typingcat.service.WordManageService;
import org.jetbrains.annotations.NotNull;

import static com.intellij.patterns.PlatformPatterns.psiElement;


/**
 * @author huxin
 */
public class JavaWordCompletionContributor extends CompletionContributor {


    protected CompletionProvider<CompletionParameters> provider;
    protected WordManageService wordManageService;

    public JavaWordCompletionContributor() {
        init();
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiLocalVariable.class), provider);
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiMethod.class), provider);
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiField.class), provider);
        extend(CompletionType.BASIC, psiElement(PsiIdentifier.class).withParent(PsiParameter.class), provider);
    }

    protected void init() {
        wordManageService = ApplicationManager.getApplication().getService(WordManageService.class);
        provider = new DefaultCompletionProvider(wordManageService);
    }


    @Override
    public void beforeCompletion(@NotNull CompletionInitializationContext context) {
        super.beforeCompletion(context);
    }

    @Override
    public void fillCompletionVariants(@NotNull CompletionParameters parameters, @NotNull CompletionResultSet result) {
        super.fillCompletionVariants(parameters, result);
    }

    @Override
    public void duringCompletion(@NotNull CompletionInitializationContext context) {
        super.duringCompletion(context);
    }

}

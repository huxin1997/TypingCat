package com.typingcat.completion.contributor;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.jetbrains.python.psi.*;
import com.typingcat.completion.provider.DefaultCompletionProvider;
import com.typingcat.service.WordManageService;

import static com.intellij.patterns.PlatformPatterns.psiElement;

/**
 * @author huxin
 */

public class PythonWordCompletionContributor extends CompletionContributor {

    protected CompletionProvider<CompletionParameters> provider;
    protected WordManageService wordManageService;

    public PythonWordCompletionContributor() {
        init();
        extend(CompletionType.BASIC, psiElement(LeafPsiElement.class).withParent(PyNamedParameter.class), provider);
        extend(CompletionType.BASIC, psiElement(LeafPsiElement.class).withParent(PyFunction.class), provider);
        extend(CompletionType.BASIC, psiElement(LeafPsiElement.class).withParents(PyTargetExpression.class, PyAssignmentStatement.class), provider);
        extend(CompletionType.BASIC, psiElement(LeafPsiElement.class).withParents(PyReferenceExpression.class, PyExpressionStatement.class), provider);

    }


    protected void init() {
        wordManageService = ApplicationManager.getApplication().getService(WordManageService.class);
        provider = new DefaultCompletionProvider(wordManageService);
    }

}

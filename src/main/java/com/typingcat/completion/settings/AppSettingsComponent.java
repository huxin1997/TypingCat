package com.typingcat.completion.settings;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;

public class AppSettingsComponent {

  private final JPanel settingsPanel;
  private final JBCheckBox showMeaning = new JBCheckBox("show Chinese meaning ? ");

  public AppSettingsComponent() {
    settingsPanel = FormBuilder.createFormBuilder()
            .addComponent(showMeaning, 1)
            .addComponentFillVertically(new JPanel(), 0)
            .getPanel();
  }

  public JPanel getPanel() {
    return settingsPanel;
  }

  public boolean getIdeaUserStatus() {
    return showMeaning.isSelected();
  }

  public void setIdeaUserStatus(boolean newStatus) {
    showMeaning.setSelected(newStatus);
  }

  public JComponent getPreferredFocusedComponent() {
    return showMeaning;
  }
}

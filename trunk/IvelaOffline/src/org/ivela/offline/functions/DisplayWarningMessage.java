/**
 * @(#)DisplayWarningMessage.java
 *
 * Eldorado Confidential and Proprietary Information
 * Copyright (C)2010 Eldorado, All Rights Reserved
 *
 * Histórico de Modificações
 *
 * Data           Quem              Descrição
 * ------------------------------------------------------------------------------------------------
 * 25 May 2010    Rafael Lagoa      (3646)SWT Browser interface para windows e linux.
 */
package br.org.eldorado.offline.browser.function;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class DisplayWarningMessage extends BrowserFunction {

    public DisplayWarningMessage (Browser browser, String name) {
        super (browser, name);
    }
    public Object function (Object[] arguments) {
        MessageBox messageBox = new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.OK);
        messageBox.setText("Atenção!");
        messageBox.setMessage("Essa funcionalidade não está disponível nessa versão do English4Smart");
        int buttonID = messageBox.open();
        return buttonID;
    }
}
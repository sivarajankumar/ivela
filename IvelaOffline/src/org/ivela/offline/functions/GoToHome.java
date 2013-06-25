/**
 * @(#)GoToHome.java
 *
 * Eldorado Confidential and Proprietary Information
 * Copyright (C)2010 Eldorado, All Rights Reserved
 *
 * Histórico de Modificações
 *
 * Data           Quem              Descrição
 * ------------------------------------------------------------------------------------------------
 * 07 May 2010    Rafael Lagoa      (4115)Reajuste arquitetural.  
 */
package br.org.eldorado.offline.browser.function;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import br.org.eldorado.offline.common.Constants;
import br.org.eldorado.offline.common.FunctionUtil;

public class GoToHome extends BrowserFunction {

    public GoToHome (Browser browser, String name) {
        super (browser, name);
    }
    public Object function (Object[] arguments) {
        return Constants.INSTALL_PATH + FunctionUtil.getLocation(arguments[0].toString())[Constants.ID_COURSE] + "/" + arguments[1].toString();
    }
}
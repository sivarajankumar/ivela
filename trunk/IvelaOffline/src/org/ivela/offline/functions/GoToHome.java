/**
 * @(#)GoToHome.java
 *
 * Eldorado Confidential and Proprietary Information
 * Copyright (C)2010 Eldorado, All Rights Reserved
 *
 * Hist�rico de Modifica��es
 *
 * Data           Quem              Descri��o
 * ------------------------------------------------------------------------------------------------
 * 07 May 2010    Rafael Lagoa      (4115)Reajuste arquitetural.  
 */
package org.ivela.offline.functions;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import org.ivela.offline.commons.Constants;
import org.ivela.offline.commons.FunctionUtil;

public class GoToHome extends BrowserFunction {

    public GoToHome (Browser browser, String name) {
        super (browser, name);
    }
    public Object function (Object[] arguments) {
        return Constants.INSTALL_PATH + FunctionUtil.getLocation(arguments[0].toString())[Constants.ID_COURSE] + "/" + arguments[1].toString();
    }
}
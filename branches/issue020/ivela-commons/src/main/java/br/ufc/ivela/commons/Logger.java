/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Class that implements the use of log across the application
 * 
 * @deprecated Use the org.apache.commons.logging package for Logging.
 */
public class Logger {

    private boolean active = true;
    private boolean logInConsole = false;
    private boolean logInFile = false;
    private String filePath = "/";
    private final String prefix = "iVeLA Logging".toUpperCase();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    
    private PrintStream outFile, outConsole;

    public Logger(boolean logInConsole, boolean logInFile, String filePath) throws FileNotFoundException {
        this.logInConsole = logInConsole;
        this.logInFile = logInFile;
        this.filePath = filePath;
        if (logInFile)
            outFile = new PrintStream(new java.io.File(filePath));
        if (logInConsole)
            outConsole = System.out;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isLogInFile() {
        return logInFile;
    }

    public void setLogInFile(boolean logInFile) {
        this.logInFile = logInFile;
    }
    
    public boolean isLogInConsole() {
        return logInConsole;
    }

    public void setLogInConsole(boolean logInConsole) {
        this.logInConsole = logInConsole;
    }
    
    public void log(String message) {
        if (! active)
            return;
        String date = sdf.format(new Date());
        message = "[" + prefix + " # " + date + "]: " + message;
        print(message);
    }

    public void log(Class clazz, String message) {
        if (! active)
            return;
        String date = sdf.format(new Date());
        message = "[" + prefix + " # " + date + "]: Class: " + clazz.getCanonicalName() + " Message: " + message;
        print(message);
    }

    public void log(Throwable throwable) {
        if (! active)
            return;
        String date = sdf.format(new Date());
        String message = "[" + prefix + " # " + date + "]: Exception: " + throwable.toString();
        print(message);        
    }

    public void log(Throwable throwable, String message) {
        if (! active)
            return;
        String date = sdf.format(new Date());
        message = "[" + prefix + " # " + date + "]: Message: " + message + " Exception: " + throwable.toString();
        print(message);        
    }

    public void log(Throwable throwable, Class clazz, String message) {
        if (! active)
            return;
        String date = sdf.format(new Date());
        message = "[" + prefix + " # " + date + "]: Class: " + clazz.getCanonicalName() + " Message: " + message + " Exception: " + throwable.toString();
        print(message);      
    }

    private void appendInLogFile(String message) {
        outFile.println(message);
        outFile.flush();
    }
    
    private void print(String string) {
        if (logInFile)
            appendInLogFile(string);
        if (logInConsole)
            outConsole.println(string);
    }

    @Override
    protected void finalize() throws Throwable {
        outFile.close();
        super.finalize();
    }
}
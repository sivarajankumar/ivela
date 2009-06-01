/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufc.ivela.commons.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author rodrigo
 */
public class Upload {
    
    String[] filters;
    
    /**
     * sends a file to the server and saves it according to path variable
     * @param file
     * @param path
     * @return true, if uploaded without problem
     *         false, otherwise
     */
    public boolean uploadFile(File file, String path) {
        try {
            File f = new File(path);
            InputStream data = new FileInputStream(file);
            OutputStream out = new FileOutputStream(f);
            byte buf[] = new byte[1024];
            int len;

            while ((len = data.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            out.close();
            data.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * renames a file generating a unique name without prefix
     * @param actualName
     * @return String
     */
    public String renameFile(String actualName) {
        // generates a unique file name
        return renameFile(actualName, "");
    }
    
    /**
     * renames a file generating a unique name with prefix
     * @param actualName
     * @param prefix
     * @return String
     */
    public String renameFile(String actualName, String prefix) {
        // generates a unique file name
        return prefix + Hash.generateHash(new Date(System.currentTimeMillis()).toString()) + "." + getExtension(actualName);
    }
    
    /**
     * retrieves the extension file
     * @param fileName
     * @return String
     */
    public String getExtension(String fileName){
        String extension = FilenameUtils.getExtension(fileName);
        
        // verifies if this file is a tar
        if(FilenameUtils.getExtension(FilenameUtils.removeExtension(fileName)).equals("tar")){
            extension = "tar." + extension; 
        }
        
        return extension;
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.ivela.commons;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe utilizada como utilitária para o content package
 */
public class ContentPackageUtils {

    /* Constantes */
    public static final String UPLOAD_PATH = "/tmp/";
    public static final String PROCESS_PATH = "/opt/ivela/";

    private static final void copyInputStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }

    /**
     * Método utilizado para descompactar um arquivo zip e retornar o arquivo descritor
     * 
     * @param flZipFile
     * @param dir
     * @return
     * @throws java.io.IOException
     */
    public static String unzip(File flZipFile, File dir) throws IOException {
        String result = "";

        // cria diretório informado, caso não exista
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IOException("The directory " + dir.getName() + " is not valid directory");
        }

        Enumeration entries;
        ZipFile zipFile;

        try {
            zipFile = new ZipFile(flZipFile);

            entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();

                if (entry.isDirectory()) {
                    // Assume directories are stored parents first then children.
                    System.err.println("Extracting directory: " + entry.getName());
                    // This is not robust, just for demonstration purposes.
                    (new File(dir, entry.getName())).mkdir();
                    continue;
                }

                System.err.println("Extracting file: " + entry.getName());
                if (entry.getName().equalsIgnoreCase("contentpackage.ivela.xml")) {
                    result = dir.getAbsolutePath() + File.separator + entry.getName();
                    //System.out.println(result);
                }
                copyInputStream(zipFile.getInputStream(entry), new BufferedOutputStream(new FileOutputStream(new File(dir, entry.getName()))));
            }

            zipFile.close();
        } catch (IOException ioe) {
            System.err.println("Unhandled exception:");
            ioe.printStackTrace();
            return "";
        }

        return result;
    }

    /**
     * Método utilizado para criar um diretório passado por parâmetro
     * 
     * @param parent
     * @param dir
     */
    public static void createDir(File parent, String dir) {
        if (parent.isDirectory()) {
            //System.out.println("In: " + parent.getAbsoluteFile());
            String path = parent.getAbsolutePath();
            path += System.getProperty("file.separator") + dir;
            //System.out.println("Creating: " + path);
            File newDir = new File(path);
            System.out.println("Result: " + newDir.mkdir());
        } else {
            System.out.println("No process");
        }
    }

    /**
     * Método utilizado para retorar a string com o conteúdo do arquivo
     * 
     * @param file
     * @return
     */
    public static String getStringXML(File file) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String temp = null;
            while ((temp = br.readLine()) != null) {
                result += temp;
            }
        } catch (IOException ex) {
            Logger.getLogger(ContentPackageUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Método utilizado para mover um arquivo
     * 
     * @param f
     * @param dir
     */
    public static void moveFile(File f, File dir) {
        try {
            f.renameTo(new File(dir.getCanonicalPath() + System.getProperty("file.separator") + f.getName()));
        } catch (IOException ex) {
            Logger.getLogger(ContentPackageUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

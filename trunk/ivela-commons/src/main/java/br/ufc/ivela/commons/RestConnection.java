/*
 * RestConnection.java
 *
 * Created on June 10, 2008, 1:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.ufc.ivela.commons;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RestConnection
 *
 * @author marcus
 */
public class RestConnection {

    private HttpURLConnection conn;
    private String date;

    public RestConnection(String baseUrl) {
        this(baseUrl, null);
    }

    /** Creates a new instance of RestConnection */
    public RestConnection(String baseUrl, String[][] params) {
        this(baseUrl, null, params);
    }

    /** Creates a new instance of RestConnection */
    public RestConnection(String baseUrl, String[][] pathParams, String[][] params) {
        try {
            String urlStr = baseUrl;
            if (pathParams != null && pathParams.length > 0) {
                urlStr = replaceTemplateParameters(baseUrl, pathParams);
            }
            URL url = new URL(encodeUrl(urlStr, params));

            //System.out.println("aaaaaaaa: " + url.toString());

            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            conn.setAllowUserInteraction(true);

            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
            date = format.format(new Date());
            conn.setRequestProperty("Date", date);
        } catch (Exception ex) {
            Logger.getLogger(RestConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setAuthenticator(Authenticator authenticator) {
        Authenticator.setDefault(authenticator);
    }

    public String getDate() {
        return date;
    }

    public RestResponse get() throws IOException {
        return get(null);
    }

    public RestResponse get(String[][] headers) throws IOException {
        conn.setRequestMethod("GET");

        return connect(headers, null);
    }

    public RestResponse put(String[][] headers) throws IOException {
        return put(headers, (InputStream) null);
    }

    public RestResponse put(String data) throws IOException {
        return put(null, data);
    }

    public RestResponse put(InputStream data) throws IOException {
        return put(null, data);
    }

    public RestResponse put(String[][] headers, String data) throws IOException {
        conn.setRequestMethod("PUT");

        return connect(headers, new ByteArrayInputStream(data.getBytes("UTF-8")));
    }

    public RestResponse put(String[][] headers, InputStream data) throws IOException {
        conn.setRequestMethod("PUT");

        return connect(headers, data);
    }

    public RestResponse postMultipart(java.io.File file) throws IOException {
        //conn.setRequestMethod("POST");
//      conn.setRequestProperty("Content-Type", "application/octet-stream");
//      conn.setRequestProperty("Accept",
//              "text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
//      conn.setRequestProperty("Accept-Language", "pt-br,pt;q=0.8,en-us;q=0.5,en;q=0.3");
//      conn.setRequestProperty("Accept-Encoding","gzip,deflate");
//      conn.setRequestProperty("Accept-Charset","ISO-8859-1,utf-8;q=0.7,*;q=0.7");
//      conn.setRequestProperty("Keep-Alive","300");
//      conn.setRequestProperty( "Connection","keep-alive");
        return connectMultipart(file);
    }

    private RestResponse connectMultipart(java.io.File file) throws IOException {

        //String urlString = "http://localhost:8081/ivela-services/resources/repository/addfile";
        try {
            URL url = conn.getURL();
            // create a boundary string
            String boundary = MultiPartFormOutputStream.createBoundary();
            URLConnection urlConn = MultiPartFormOutputStream.createConnection(url);
            urlConn.setRequestProperty("Accept", "*/*");
            urlConn.setRequestProperty("Content-Type",
                    MultiPartFormOutputStream.getContentType(boundary));
            // set some other request headers...
            urlConn.setRequestProperty("Connection", "Keep-Alive");
            urlConn.setRequestProperty("Cache-Control", "no-cache");
            // no need to connect cuz getOutputStream() does it
            MultiPartFormOutputStream out =
                    new MultiPartFormOutputStream(urlConn.getOutputStream(), boundary);
            // write a text field element
            //out.writeField("myText", "text field text");
            // upload a file
            out.writeFile("myFile", "*/*", file);
            // can also write bytes directly
            //out.writeFile("myFile", "text/plain", "C:\\test.txt", 
//				"This is some file text.".getBytes("ASCII"));
            out.close();
            // read response from server
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlConn.getInputStream()));
//            String line = "";
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
            
            in.close();

            byte[] buffer = new byte[1024];
            int count = 0;
            
            RestResponse response = new RestResponse();
            InputStream is = urlConn.getInputStream();

            while ((count = is.read(buffer)) != -1) {
                response.write(buffer, 0, count);
            }

            response.setResponseCode(conn.getResponseCode());
            response.setResponseMessage(conn.getResponseMessage());
            response.setContentType(urlConn.getContentType());
            response.setContentEncoding(urlConn.getContentEncoding());
            response.setLastModified(urlConn.getLastModified());

            return response;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public RestResponse post(String data) throws IOException {
        return post(null, data);
    }

    public RestResponse post(InputStream data) throws IOException {
        return post(null, data);
    }

    public RestResponse post(String[][] headers, String data) throws IOException {
        conn.setRequestMethod("POST");

        return connect(headers, new ByteArrayInputStream(data.getBytes("UTF-8")));
    }

    public RestResponse post(String[][] headers, InputStream data) throws IOException {
        conn.setRequestMethod("POST");

        return connect(headers, data);
    }

    /**
     * Used by post method whose contents are like form input
     */
    public RestResponse post(String[][] params) throws IOException {
        return post(null, params);
    }

    /**
     * Used by post method whose contents are like form input
     */
    public RestResponse post(String[][] headers, String[][] params) throws IOException {
        conn.setRequestMethod("POST");
        conn.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
        String data = encodeParams(params);
        return connect(headers, new ByteArrayInputStream(data.getBytes("UTF-8")));
    }

    public RestResponse delete() throws IOException {
        return delete(null);
    }

    public RestResponse delete(String[][] headers) throws IOException {
        conn.setRequestMethod("DELETE");

        return connect(headers, null);
    }

    /**
     * @param baseUrl
     * @param params
     * @return response
     */
    private RestResponse connect(String[][] headers,
            InputStream data) throws IOException {
        try {
            // Send data
            setHeaders(headers);

            String method = conn.getRequestMethod();

            byte[] buffer = new byte[1024];
            int count = 0;

            if (method.equals("PUT") || method.equals("POST")) {
                if (data != null) {
                    conn.setDoOutput(true);
                    OutputStream os = conn.getOutputStream();

                    while ((count = data.read(buffer)) != -1) {
                        os.write(buffer, 0, count);
                    }
                    os.flush();
                }
            }

            RestResponse response = new RestResponse();
            InputStream is = conn.getInputStream();

            while ((count = is.read(buffer)) != -1) {
                response.write(buffer, 0, count);
            }

            response.setResponseCode(conn.getResponseCode());
            response.setResponseMessage(conn.getResponseMessage());
            response.setContentType(conn.getContentType());
            response.setContentEncoding(conn.getContentEncoding());
            response.setLastModified(conn.getLastModified());

            return response;
        } catch (Exception e) {
            String errMsg = "Cannot connect to :" + conn.getURL();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String line;
                StringBuffer buf = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    buf.append(line);
                    //System.out.print(line);
                }
                errMsg = buf.toString();
            } finally {
                throw new IOException(errMsg);
            }
        }
    }

    private String replaceTemplateParameters(String baseUrl, String[][] pathParams) throws UnsupportedEncodingException {
        String url = baseUrl;
        if (pathParams != null) {
            for (int i = 0; i < pathParams.length; i++) {
                String key = pathParams[i][0];
                String value = URLEncoder.encode(pathParams[i][1], "UTF-8");
                // replaces + by %20
                Matcher matcher = Pattern.compile("[\\+]").matcher(value);
                value = matcher.replaceAll("%20");

                if (value == null) {
                    value = "";
                }
                url = url.replace(key, value);
            }
        }
        //System.out.println(url);
        return url;
    }

    private String encodeUrl(String baseUrl, String[][] params) {
        return baseUrl + encodeParams(params);
    }

    private String encodeParams(String[][] params) {
        String p = "";

        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                String key = params[i][0];
                String value = params[i][1];

                if (value != null) {
                    try {
                        p += key + "=" + URLEncoder.encode(value, "UTF-8") + "&";
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(RestConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (p.length() > 0) {
                p = "?" + p.substring(0, p.length() - 1);
            }
        }

        return p;
    }

    private void setHeaders(String[][] headers) {
        if (headers != null) {
            for (int i = 0; i < headers.length; i++) {
                conn.setRequestProperty(headers[i][0], headers[i][1]);
            }
        }
    }
}

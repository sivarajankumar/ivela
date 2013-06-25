package com.tix11.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class Utils {

	private static Utils INSTANCE = null;

	public static Utils getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Utils();
		return INSTANCE;
	}

	private Utils() {
	}

	public String getStringFromFile(String filePath) {
		StringBuffer ret = new StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String str;
			while ((str = in.readLine()) != null) {
				ret.append(str);
			}
			in.close();
		} catch (IOException e) {
		}
		return ret.toString();
	}
	
	public String safeDecodeB64(String b64s) {
		b64s = b64s.replaceAll("-", "\\+");
		b64s = b64s.replace('_', '/');
		return b64s;
	}

	public String execute(String[] cmdArray) throws Exception {
		Process process = Runtime.getRuntime().exec(cmdArray);
		BufferedReader input = new BufferedReader(new InputStreamReader(
				process.getInputStream()));
		StringBuffer output = new StringBuffer();
		String line = "";
		while ((line = input.readLine()) != null) {
			output.append(line);
		}
		process.destroy();
		process = null;
		return output.toString();
	}

	public void toPng(String srcPath, String destPath) throws Exception {

		BufferedImage tif = ImageIO.read(new File(srcPath));
		ImageIO.write(tif, "png", new File(destPath));

	}

	public byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		long length = file.length();

		if (length > Integer.MAX_VALUE)
			throw new IOException();

		byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		is.close();
		return bytes;
	}

	public  void byteArrayToFile(byte[] bytes, String strFile) throws IOException {
		FileOutputStream fos = new FileOutputStream(strFile);
		fos.write(bytes);
		fos.close();
	}
}

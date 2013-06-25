package org.ivela.offline.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class FileReaderSIM {

	public static StringBuffer readUrlsFile(String fileName) {

		StringBuffer linesReaded = new StringBuffer();

		try {
			File file = new File(fileName);
			FileInputStream in = new FileInputStream(file);
			Scanner scanner = new Scanner(in);

			while (scanner.hasNext()) {
				String readLine = scanner.next();
				linesReaded.append(readLine);
				System.out.println(readLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return linesReaded;
	}


}

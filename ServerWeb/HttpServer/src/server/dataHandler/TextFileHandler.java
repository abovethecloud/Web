package server.dataHandler;

import httpUtils.HttpResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import server.FileHandler;

/**
 * Copia nella risposta un file testuale.
 * 
 * @author lele
 *
 */
public class TextFileHandler implements FileHandler {

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see server.FileHandler#copyFile(java.lang.String,
	 * httpUtils.HttpResponse)
	 */
	public void copyFile(String filename, HttpResponse message)
			throws IOException {

		OutputStreamWriter writer = message.getOut();
		BufferedReader filerReader = new BufferedReader(
				new FileReader(filename));

		String fileLine = filerReader.readLine();
		while (fileLine != null) {
			writer.write(fileLine + "\n");
			fileLine = filerReader.readLine();
		}

		filerReader.close();
	}
}

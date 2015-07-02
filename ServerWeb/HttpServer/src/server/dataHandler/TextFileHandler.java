package server.dataHandler;

import httpUtils.HttpResponse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import server.FileHandler;

/**
 * Copia nella risposta un file testuale.
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
	
	public void copyBinaryFile(String filename, HttpResponse message)
			throws IOException, FileNotFoundException {

		OutputStream stream = message.getOutputStream();
		InputStream inputStream = new FileInputStream(filename);

		byte[] bytesAlreadyRead = new byte[102400];
		int bread = inputStream.read(bytesAlreadyRead);
		while (bread != -1) {
			stream.write(bytesAlreadyRead);
			bread = inputStream.read(bytesAlreadyRead);
		}
		stream.close();
		inputStream.close();

	}
}

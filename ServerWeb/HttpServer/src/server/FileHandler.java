package server;

import httpUtils.HttpResponse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Le classi che implementano questa interfaccia devono garantire di essere in
 * grado di inserire un file, identificato dal path, nel corpo di una richiesta
 * HTTP {@code HttpResponse}.
 */
public interface FileHandler {

	/**
	 * Copia il file nel corpo della risposta HTTP {@code HttpResponse}.
	 * 
	 * @param filename
	 *            : il path del file da copiare.
	 * @param response
	 *            : la risposta HTTP che deve contenere il file copiato.
	 * @throws IOException
	 */
	public void copyFile(String filename, HttpResponse response)
			throws IOException;
	
	public void copyBinaryFile(String filename, HttpResponse response)
			throws IOException, FileNotFoundException;

}

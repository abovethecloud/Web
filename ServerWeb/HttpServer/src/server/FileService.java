package server;

import httpUtils.HttpRequest;
import httpUtils.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

/**
 * Questa implementazione di {@link IService} permette di caricare nella risposta
 * http un generico file.
 */
public class FileService implements IService {

	private FileHandler handler;
	private String errorFile;
	private String indexFile;

	/**
	 * Ritorna un'istanza della classe
	 * 
	 * @param handler
	 *            : il {@code FileHandler} deputato a trasferire il file
	 * @param indexFile
	 *            : il file di default caricato quando si tenta di accedere alla
	 *            cartella dei contenuti web.
	 * @param errorFile
	 *            : il file caricato quando quello richiesto non esiste.
	 */
	public FileService(FileHandler handler, String indexFile, String errorFile) {
		this.handler = handler;
		this.errorFile = errorFile;
		this.indexFile = indexFile;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see server.IService#sendHTTP(java.net.Socket, httpUtils.HttpRequest)
	 */
	public void sendHTTP(final Socket clientSocket, HttpRequest request)
			throws IOException {

		String filename = checkURI(request.getUri());
		HttpResponse response = new HttpResponse();
		this.setRequestContentType(filename, response);
		response.openHttpAnswer(clientSocket);
		this.handler.copyBinaryFile(filename, response);
		response.closeHttpAnswer();
	}

	private void setRequestContentType(String filename, HttpResponse message) {
		for (ContentType type : ContentType.values()) {
			if (filename.endsWith(type.getExtension())) {
				message.setContentType(type);
			}
		}
	}

	private String checkURI(String uri) throws IOException {
		String filename = "web" + uri;
		if (filename.equals("web/")) {
			filename = setIndexFile();
		}
		File file = new File(filename);
		if ((!file.exists()) || uri.equals("")) {
			filename = setErrorFile();
		}
		return filename;
	}

	private String setErrorFile() {
		return this.errorFile;
	}

	private String setIndexFile() {
		return this.indexFile;
	}
}

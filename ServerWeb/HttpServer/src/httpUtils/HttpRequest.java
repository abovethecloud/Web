package httpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Questa classe rappresenta una generica richiesta secondo il protocollo HTTP.
 * Permette di conoscere l'URI richiesto.
 */
public class HttpRequest {

	private String uri;

	/**
	 * Genera un'istanza della classe.
	 * 
	 * @param clientSocket
	 *            : il {@code Socket} riservato alla comunicazione.
	 * @throws IOException
	 */
	public HttpRequest(final Socket clientSocket) throws IOException {
		super();
		ArrayList<String> lines = getRequestBody(clientSocket);
		String uri = "";
		if (lines.size() > 0) {
			uri = getRequestUri(lines.get(0));
		}
		this.uri = uri;
	}

	/**
	 * Ritorna l'estensione del file richiesto.
	 * 
	 * @return : una stringa contenente l'estensione. Se l'estensione non viene
	 *         individuata, ritorna {@code null.}
	 */
	public String getUriExtension() {
		if (uri.contains(".")) {
			String[] uriParts = uri.split("\\.");
			return uriParts[uriParts.length - 1];
		} else
			return null;
	}

	/**
	 * Ritorna l'URI del file richiesto.
	 * 
	 * @return una stringa contenente l'URI.
	 */
	public String getUri() {
		return uri;
	}

	private String getRequestUri(String firstLine) {
		StringTokenizer tokenizer = new StringTokenizer(firstLine, " ");
		tokenizer.nextToken();
		String uri = tokenizer.nextToken();
		return uri;
	}

	private ArrayList<String> getRequestBody(final Socket clientSocket)
			throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		ArrayList<String> lines = new ArrayList<String>();
		String line = in.readLine();
		while (line != null) {
			lines.add(line);
			line = in.readLine();
			line = checkLineLength(line);
		}
		return lines;
	}

	private String checkLineLength(String line) {
		if (line.length() == 0) {
			line = null;
		}
		return line;
	}

}

package httpUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

import server.ContentType;

/**
 * Questa classe gestisce una risposta secondo il protocollo HTTP. Si occupa 
 * di iniziare e chiudere la risposta e scrivere il corpo del messaggio.
 * I parametri iniziali della risposta sono fittizi e non significativi.
 * Le chiamate a {@codeopenHttpAnswer(Socket clientSocket)} devono sempre essere seguite 
 * da chiamate a {@code closeHttpAnswer()}.
 * 
 * @author lele
 */
public class HttpResponse {

	private ContentType contentType = ContentType.HTML;
	private OutputStreamWriter out;
	private OutputStream outputStream;

	/**
	 * Apre la comunicazione con le informazioni richieste dal protocollo HTTP.
	 * Attenzione: i dati inseriti sono fittizi e non significativi.
	 * Deve essere seguito da {@code closeHttpAnswer()}.
	 * @param clientSocket: il socket deputato alla comunicazione
	 * @throws IOException
	 */
	public void openHttpAnswer(Socket clientSocket) throws IOException {
		this.outputStream = clientSocket.getOutputStream();
		this.out = new OutputStreamWriter(clientSocket.getOutputStream(),
				Charset.forName("UTF-8").newEncoder());

		out.write("HTTP/1.1 200 OK\n");
		out.write("Date: Sun, 31 May 2015 14:47:00\n");
		out.write("Content-Type: " + contentType.getContentType()
				+ " charset=utf-8\n");
		out.write("\n");
	}

	/**
	 * Chiude la risposta. Necessaria dopo {@codeopenHttpAnswer(Socket clientSocket)}.
	 * @throws IOException
	 */
	public void closeHttpAnswer() throws IOException {
		out.write("\n");
		out.close();
	}

	/**
	 * Specifica il {@code ContentType} della risposta.
	 * @param contentType
	 */
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	/**
	 * Può essere chiamata solamente dopo una chiamata a {@codeopenHttpAnswer(Socket clientSocket)
	 * 
	 * @return lo OutputStreamWriter associato al socket della comunicazione
	 */
	public OutputStreamWriter getOut() {
		return out;
	}
	
	/**
	 * Può essere chiamata solamente dopo una chiamata a {@codeopenHttpAnswer(Socket clientSocket)
	 * 
	 * @return lo OutputStream associato al socket della comunicazione
	 */
	public OutputStream getOutputStream() {
		return outputStream;
	}
	
}

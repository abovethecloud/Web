package server;

import httpUtils.HttpRequest;

import java.io.IOException;
import java.net.Socket;

/**
 * Generico servizio offerto dal server. Con "servizio" si intende una qualsiasi
 * sequenza di elaborazione capace di produrre una risposta HTTP da inviare al
 * client.
 * 
 * @author lele
 */
public interface IService {

	/**
	 * Una generica azione capace di produrre ed inviare al socket passato come
	 * parametro una risposta alla richiesta.
	 * 
	 * @param clientSocket
	 *            : il socket del client, ovvero il destinatario della risposta.
	 * @param request
	 *            : la richiesta cui occorre rispondere.
	 * @throws IOException
	 */
	public void sendHTTP(final Socket clientSocket, HttpRequest request)
			throws IOException;

}

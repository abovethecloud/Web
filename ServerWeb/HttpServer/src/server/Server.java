package server;

import httpUtils.HttpRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import server.dataHandler.TextFileHandler;

public class Server {

	private int port;
	private HashMap<String, IService> services = new HashMap<String, IService>();

	public Server(int port) {
		this.port = port;
	}

	public void addService(String name, IService service) {
		services.put(name, service);
	}

	public void launch() {

		try {
			ServerSocket socket = new ServerSocket(port);
			while (true) {
				final Socket clientSocket = socket.accept();
				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						try {
							HttpRequest request = new HttpRequest(clientSocket);
							IService service = services.get(request
									.getUriExtension());
							if (service == null) {
								service = new FileService(
										new TextFileHandler(),
										"web/Home.html", "web/error404.html");
							}
							service.sendHTTP(clientSocket, request);
							clientSocket.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				Thread thread = new Thread(runnable);
				thread.start();
			}
			// socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

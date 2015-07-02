package tests;

import server.FileService;
import server.Server;
import server.dataHandler.ImageFileHandler;
import server.dataHandler.TextFileHandler;

public class ServerLauncher {

	public static final String HOME = "web/Home.html";
	public static final String ERROR = "web/error404.html";
	public static final int PORT = 4444;

	public static void main(String[] args) {

		Server server = new Server(PORT);
		server.addService("html", new FileService(new TextFileHandler(), HOME,
				ERROR));
		server.addService("js", new FileService(new TextFileHandler(), HOME,
				ERROR));
		server.addService("css", new FileService(new TextFileHandler(), HOME,
				ERROR));
		server.addService("png", new FileService(new ImageFileHandler("png"),
				HOME, ERROR));
		server.addService("jpg", new FileService(new ImageFileHandler("jpg"),
				HOME, ERROR));
		server.launch();

	}
}

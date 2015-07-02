package server.dataHandler;

import httpUtils.HttpResponse;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import server.FileHandler;

/**
 * Copia nella risposta un'immagine. Il tipo di immagine deve essere specificato
 * nel costruttore.
 */
public class ImageFileHandler implements FileHandler {

	private String imageType;

	/**
	 * Ritorna un'istanza della classe
	 * 
	 * @param imageType
	 *            : il tipo di immagine da copiare.
	 */
	public ImageFileHandler(String imageType) {
		this.imageType = imageType;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see server.FileHandler#copyFile(java.lang.String,
	 * httpUtils.HttpResponse)
	 */
	public void copyFile(String filename, HttpResponse message)
			throws IOException {

		File file = new File(filename);
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, this.imageType, message.getOutputStream());

	}

	@Override
	public void copyBinaryFile(String filename, HttpResponse response)
			throws IOException, FileNotFoundException {

		File file = new File(filename);
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, this.imageType, response.getOutputStream());
		
	}
}

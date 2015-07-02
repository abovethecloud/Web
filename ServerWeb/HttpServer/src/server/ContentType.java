package server;

/**
 * Content type (tipi di file) supportati dal server.
 * 
 * @author lele
 *
 */
public enum ContentType {

	HTML("text/html", "html"), JS("text/javascript", "js"), CSS("text/css",
			"css"), PNG("image/png", "png"), JPEG("image/jpeg", "jpg");
	private String extension;
	private String text;

	private ContentType(String text, String extension) {
		this.text = text;
		this.extension = extension;
	}

	public String getContentType() {
		return this.text;
	}

	public String getExtension() {
		return extension;
	}

}

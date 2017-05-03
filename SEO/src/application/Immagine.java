package application;

public class Immagine {

	private final int id;
	private final String url;
	
	public Immagine(int id, String url) {
		this.id = id;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}
	
}

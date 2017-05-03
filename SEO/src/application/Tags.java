package application;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Tags {

	ObservableList<H1> h1(Document a) {
		ObservableList<H1> h1 = FXCollections.observableArrayList();
		Elements testoH1 = a.select("h1");
		int i = 1;
		for (Element link : testoH1) {
			String text = link.text();
			h1.add(new H1(i, text));
			i++;
		}
		return h1;
	}

	ObservableList<Immagine> img(Document a) {
		ObservableList<Immagine> immagini = FXCollections.observableArrayList();
		Elements images = a.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		int i = 1;
		for (Element image : images) {
			String url = image.attr("src");
			immagini.add(new Immagine(i, url));
			i++;
		}
		return immagini;
	}

	String titleImg(Document a) {
		String titleImgs = "";
		Elements images = a.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		for (Element image : images) {
			titleImgs = image.attr("title");
			System.out.println(titleImgs);
		}
		return titleImgs;
	}

	String altImg(Document a) {
		String altImgs = "";
		Elements images = a.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
		for (Element image : images) {
			altImgs = image.attr("alt");
			System.out.println(altImgs);
		}
		return altImgs;
	}

	String robot(Document a) {
		String robots = "";
		Elements meta = a.select("meta");
		int i = 1;
		for (Element meta1 : meta) {
			if (i <= 1) {
				if (meta1.attr("name").equals("robots")) {
					robots = meta1.attr("content");
					i++;
				}
			}
		}
		return robots;
	}

	String keyword(Document a) {
		String keywords = "";
		Elements meta = a.select("meta");
		int i = 1;
		for (Element meta1 : meta) {
			if (i <= 1) {
				if (meta1.attr("name").equals("keywords")) {
					keywords = meta1.attr("content");
					i++;
				}
			}
		}
		return keywords;
	}

	String title(Document a) {
		String titles = "";
		Elements meta = a.select("title");
		int i = 1;
		for (Element meta1 : meta) {
			if (i <= 1) {
				titles = meta1.text();
				i++;
			}
		}
		return titles;
	}

	String description(Document a) {
		String descriptions = "";
		Elements meta = a.select("meta");
		int i = 1;
		for (Element meta1 : meta) {
			if (i <= 1) {
				if (meta1.attr("name").equals("description")) {
					descriptions = meta1.attr("content");
					i++;
				}
			}
		}
		return descriptions;
	}

	String canonical(Document a) {
		String canonicals = "";
		Elements link = a.select("link");
		int i = 1;
		for (Element link1 : link) {
			if (i <= 1) {
				if (link1.attr("rel").equals("canonical")) {
					canonicals = link1.attr("href");
					i++;
				}
			}
		}
		return canonicals;
	}
}

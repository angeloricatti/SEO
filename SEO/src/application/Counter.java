package application;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Counter {
	
	int robotCount(Document a) {
		Elements meta = a.select("meta");
		int i=0;
		for (Element meta1 : meta) {
			if (meta1.attr("name").equals("robots")) {
				i++;
			}
		}
		return i;
	}
	int keywordCount(Document a) {
		Elements meta = a.select("meta");
		int i = 0;
		for (Element meta1 : meta) {
			if (meta1.attr("name").equals("keywords")) {
				i++;
			}
		}
		return i;
	}
	
	int titleCount(Document a) {
		Elements meta = a.select("title");
		int i= 0;
		for (@SuppressWarnings("unused") Element meta1 : meta) {
			i++;
		}
		return i;
	}
	int descriptionCount(Document a) {
		Elements meta = a.select("meta");
		int i=0;
		for (Element meta1 : meta) {
			if (meta1.attr("name").equals("description")) {
				i++;
			}
		}
		return i;
	}

	int canonicalCount(Document a) {
		Elements link = a.select("link");
		int i=0;
		for (Element link1 : link) {
			if (link1.attr("rel").equals("canonical")) {
				i++;
			}
		}
		return i;
	}
}



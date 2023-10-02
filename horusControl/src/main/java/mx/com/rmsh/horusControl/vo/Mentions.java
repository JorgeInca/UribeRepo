package mx.com.rmsh.horusControl.vo;

public class Mentions {
	public String title;
	public String link;
	public String description;
	public String engine;
	public String keyword;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "Mentions [title=" + title + ", link=" + link + ", description=" + description + ", engine=" + engine
				+ ", keyword=" + keyword + "]";
	}


}

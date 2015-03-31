import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Hyperlink {
	private String url;
	public ArrayList<String> tags = new ArrayList<>();
	public ArrayList<String> comments = new ArrayList<>();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public Date creationDate;
	public Date modificationDate;

	Hyperlink(String url) {
		creationDate = new Date();
		modificationDate = new Date();
		this.url = url;
	}

	String getUrl() {
		return url;
	}

	void setUrl(String url) {
		this.url = url;
		modificationDate = new Date();
	}

	void setDate(String date, int i) throws ParseException {
		if (i == 0) {
			creationDate = dateFormat.parse(date);
		} else if (i == 1) {
			modificationDate = dateFormat.parse(date);
		}
	}

	// Métodos de tags

	public void addTag(String tag) {
		if (!tags.contains(tag)) {
			tags.add(tag);
			modificationDate = new Date();
		} else {
			System.out.println("This tag already exist.");
		}
	}

	public void removeTag(String tag) {
		if (tags.contains(tag)) {
			tags.remove(tag);
			modificationDate = new Date();
		} else {
			System.out.println("This tag does not exist.");
		}
	}

	public void editTag(String oldTag, String newTag) {
		if (tags.contains(oldTag)) {
			tags.remove(oldTag);
			tags.add(newTag);
			modificationDate = new Date();
		} else {
			System.out.println("This tag does not exist.");
		}
	}

	public void viewTag() {
		if (tags.isEmpty()) {
			System.out.println("There are no tags!");
		} else {
			System.out.println("Tags:");
			for (String t : tags) {
				System.out.println(t);
			}
		}
	}

	public boolean isEmptyTag() {
		return tags.isEmpty();
	}

	// Métodos de comments

	public void addComment(String comment) {
		if (!comments.contains(comment)) {
			comments.add(comment);
			modificationDate = new Date();
		} else {
			System.out.println("This comment already exist.");
		}
	}

	public void removeComment(String comment) {
		if (comments.contains(comment)) {
			comments.remove(comment);
			modificationDate = new Date();
		} else {
			System.out.println("This comment does not exist.");
		}
	}

	public void editComment(String oldComment, String newComment) {
		if (comments.contains(oldComment)) {
			comments.remove(oldComment);
			comments.add(newComment);
			modificationDate = new Date();
		} else {
			System.out.println("This comment does not exist.");
		}
	}

	public void viewComment() {
		if (comments.isEmpty()) {
			System.out.println("There are no comments!");
		} else {
			System.out.println("Comments:");
			for (String t : comments) {
				System.out.println(t);
			}
		}
	}

	public boolean isEmptyComment() {
		return comments.isEmpty();
	}

	// Método de visualizar
	public void viewHyperlink() {
		System.out.println("----------------------------------------------");
		System.out.println("The Hyperlink URL is:");
		System.out.println(getUrl());
		// Tags
		if (tags.isEmpty()) {
			System.out.println("There are no tags.");
		} else {
			System.out.println("The tags are:");
			for (String t : tags) {
				System.out.println(t);
			}
		}
		// Comments
		if (comments.isEmpty()) {
			System.out.println("There are no comments.");
		} else {
			System.out.println("The comments are:");
			for (String t : comments) {
				System.out.println(t);
			}
		}
		// Creation Date
		System.out.println("The Creation Date is:");
		System.out.println(dateFormat.format(creationDate));
		// Last Modification Date
		System.out.println("The Last Modification Date is:");
		System.out.println(dateFormat.format(modificationDate));
		System.out.println("----------------------------------------------");
	}
}

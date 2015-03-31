import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Database {
	String fileLocation = ("C:\\Database.txt");
	ArrayList<Hyperlink> list = new ArrayList<>();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	// Métodos de Hyperlink
	public void addHyperlink(String url) {

		Hyperlink link = new Hyperlink(url);
		list.add(link);
	}

	public void editHyperlink(String url, String newUrl) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				link.setUrl(newUrl);
			}
		}
	}

	public void removeHyperlink(String url) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				list.remove(link);
				break;
			}
		}
	}

	public void setDate(String url, String date, int i) throws ParseException {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				link.setDate(date, i);
			}
		}
	}

	public void viewHyperlink(String url) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				link.viewHyperlink();
			}
		}
	}

	public void viewHyperlinkByTag(String tag) {
		for (Hyperlink link : list) {
			for (String t : link.tags) {
				if (t.equals(tag)) {
					link.viewHyperlink();
				}
			}
		}
	}

	public void viewHyperlinkByComment(String comment) {
		for (Hyperlink link : list) {
			for (String t : link.comments) {
				if (t.equals(comment)) {
					link.viewHyperlink();
				}
			}
		}
	}

	public boolean doesUrlExist(String url, int toPrint) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				if (toPrint == 0) {
					System.out.println("This URL already exist!");
				}
				return true;
			}
		}

		if (toPrint == 1) {
			System.out.println("This URL does not exist!");
		}
		return false;
	}

	// Métodos de tag
	public void addTag(String url, String tag) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				link.addTag(tag);
			}
		}
	}

	public void removeTag(String url, String tag) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				link.removeTag(tag);
			}
		}
	}

	public void editTag(String url, String oldTag, String newTag) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				link.editTag(oldTag, newTag);
			}
		}
	}

	public void viewTag(String url) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				link.viewTag();
			}
		}
	}

	public boolean isEmptyTag(String url) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				return link.isEmptyTag();
			}
		}
		return false;
	}

	// Métodos de comment
	public void addComment(String url, String comment) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				link.addComment(comment);
			}
		}
	}

	public void removeComment(String url, String comment) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				link.removeComment(comment);
			}
		}
	}

	public void editComment(String url, String oldComment, String newComment) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				link.editComment(oldComment, newComment);
			}
		}
	}

	public void viewComment(String url) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				link.viewComment();
			}
		}
	}

	public boolean isEmptyComment(String url) {
		for (Hyperlink link : list) {
			if (link.getUrl().equals(url)) {
				return link.isEmptyComment();
			}
		}
		return false;
	}

	// Métodos de organizar
	public void sortByURL() {

		class CustomComparator implements Comparator<Hyperlink> {
			@Override
			public int compare(Hyperlink o1, Hyperlink o2) {
				return o1.getUrl().compareTo(o2.getUrl());
			}
		}

		Collections.sort(list, new CustomComparator());
	}

	public void sortByCreationDate() {

		class CustomComparator implements Comparator<Hyperlink> {
			@Override
			public int compare(Hyperlink o1, Hyperlink o2) {
				return o1.creationDate.compareTo(o2.creationDate);
			}
		}

		Collections.sort(list, new CustomComparator());
	}

	public void sortByModificationDate() {

		class CustomComparator implements Comparator<Hyperlink> {
			@Override
			public int compare(Hyperlink o1, Hyperlink o2) {
				return o1.modificationDate.compareTo(o2.modificationDate);
			}
		}

		Collections.sort(list, new CustomComparator());
	}

	public void viewAll() {
		for (Hyperlink link : list) {
			link.viewHyperlink();
		}
	}

	public void Load() {
		String lastHyperlink = ("");
		int i = 0;
		try (BufferedReader br = new BufferedReader(
				new FileReader(fileLocation))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.equals("#URL:")) {
					i = 1;
				} else if (line.equals("#TAGS:")) {
					i = 2;
				} else if (line.equals("#COMMENTS:")) {
					i = 3;
				} else if (line.equals("#CreationDate:")) {
					i = 4;
				} else if (line.equals("#ModificationDate:")) {
					i = 5;
				} else if (i == 1) {
					addHyperlink(line);
					lastHyperlink = line;
				} else if (i == 2) {
					addTag(lastHyperlink, line);
				} else if (i == 3) {
					addComment(lastHyperlink, line);
				} else if (i == 4) {
					setDate(lastHyperlink, line, 0);
				} else if (i == 5) {
					setDate(lastHyperlink, line, 1);
				}
			}
			br.close();
		}

		catch (Exception ex) {
			System.out.println("C:\\Database.txt does not exist!");
		}

	}

	public void Save() {

		try {
			File file = new File(fileLocation);

			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for (Hyperlink link : list) {
				bw.write("#URL:");
				bw.newLine();
				bw.write(link.getUrl());
				bw.newLine();
				// Tags
				bw.write("#TAGS:");
				bw.newLine();
				for (String t : link.tags) {
					bw.write(t);
					bw.newLine();
				}

				// Comments
				bw.write("#COMMENTS:");
				bw.newLine();
				for (String t : link.comments) {
					bw.write(t);
					bw.newLine();
				}
				// Creation Date
				bw.write("#CreationDate:");
				bw.newLine();
				bw.write(dateFormat.format(link.creationDate));
				bw.newLine();
				// Last Modification Date
				bw.write("#ModificationDate:");
				bw.newLine();
				bw.write(dateFormat.format(link.modificationDate));
				bw.newLine();
			}
			bw.close();

		} catch (Exception ex) {
			System.out.println("Error! Can't save.");
		}
	}
}

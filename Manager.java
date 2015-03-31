import java.util.Scanner;

public class Manager {

	public static String c1, c2, c3;
	public static char c;
	public static Scanner reader = new Scanner(System.in);
	public static Database data = new Database();

	public static void main(String[] args) {
		boolean toFinish = false;
		System.out
				.println("Would you like to load Hyperlinks from C:\\Database.txt?(y/n)");
		c = reader.nextLine().charAt(0);
		if (c == 'y') {
			data.Load();
		}

		while (!toFinish) {
			System.out
					.println("Would you like to ADD(1), VIEW(2), EDIT(3) or REMOVE(4) a Hyperlink? (Press 5 to EXIT)");
			c = reader.nextLine().charAt(0);
			switch (c) {
			case '1':
				Add();
				break;
			case '2':
				View();
				break;
			case '3':
				Edit();
				break;
			case '4':
				Remove();
				break;
			case '5':
				toFinish = true;
				break;
			}
		}
		System.out
				.println("Would you like to save Hyperlinks on C:\\Database.txt?(y/n)");
		c = reader.nextLine().charAt(0);
		if (c == 'y') {
			data.Save();
		}
		reader.close();
	}

	public static void Add() {
		System.out.println("Insert the new Hyperlink URL:");
		c1 = reader.nextLine();
		if (!data.doesUrlExist(c1, 0)) {
			data.addHyperlink(c1);

			if (data.doesUrlExist(c1, 1)) {
				System.out.println("Would you like to add a tag? (y/n)");
				c = reader.nextLine().charAt(0);
				if (c == 'y') {
					System.out.println("Insert tags:");
					c2 = reader.nextLine();
					data.addTag(c1, c2);
				}

				System.out.println("Would you like to add a comment? (y/n)");
				c = reader.nextLine().charAt(0);
				if (c == 'y') {
					System.out.println("Insert comments:");
					c3 = reader.nextLine();
					data.addComment(c1, c3);
				}
			}
		}
	}

	public static void Edit() {
		System.out.println("Insert the URL to edit:");
		c1 = reader.nextLine();
		if (data.doesUrlExist(c1, 1)) {

			System.out
					.println("What would you like to edit? URL(1), tags(2) or comments(3)");
			c = reader.nextLine().charAt(0);
			if (c == '1') {
				System.out.println("Insert the new URL: ");
				c2 = reader.nextLine();
				if (!data.doesUrlExist(c2, 0)) {
					data.editHyperlink(c1, c2);
				}
			}

			else if (c == '2') {
				System.out
						.println("What would you like to ADD(1) a new tag or EDIT(2) an existing one?");
				c = reader.nextLine().charAt(0);
				if (c == '1') {
					System.out.println("Insert tags:");
					c2 = reader.nextLine();
					data.addTag(c1, c2);
				} else if (c == '2') {
					data.viewTag(c1);
					if (data.isEmptyTag(c1)) {
					} else {
						System.out.println("Insert the tag you want to edit: ");
						c2 = reader.nextLine();
						System.out.println("Insert the new tag: ");
						c3 = reader.nextLine();
						data.editTag(c1, c2, c3);
					}
				}
			}

			else if (c == '3') {
				System.out
						.println("What would you like to ADD(1) a new comment or EDIT(2) an existing one?");
				c = reader.nextLine().charAt(0);
				if (c == '1') {
					System.out.println("Insert comments:");
					c2 = reader.nextLine();
					data.addComment(c1, c2);
				} else if (c == '2') {
					data.viewComment(c1);
					if (data.isEmptyComment(c1)) {
					} else {
						System.out
								.println("Insert the comment you want to edit: ");
						c2 = reader.nextLine();
						System.out.println("Insert the new comment: ");
						c3 = reader.nextLine();
						data.editComment(c1, c2, c3);
					}
				}
			}
		}
	}

	public static void Remove() {
		System.out.println("Insert the URL to remove:");

		c1 = reader.nextLine();

		if (data.doesUrlExist(c1, 1)) {

			System.out
					.println("What would you like to remove? URL(1), tags(2) or comments(3)");
			c = reader.nextLine().charAt(0);
			if (c == '1') {
				data.removeHyperlink(c1);
			}

			else if (c == '2') {
				data.viewTag(c1);
				if (data.isEmptyTag(c1)) {
				} else {
					System.out.println("Insert the tag you want to remove: ");
					c2 = reader.nextLine();
					data.removeTag(c1, c2);
				}
			}

			else if (c == '3') {
				data.viewComment(c1);
				if (data.isEmptyComment(c1)) {
				} else {
					System.out
							.println("Insert the comment you want to remove: ");
					c2 = reader.nextLine();
					data.removeComment(c1, c2);
				}
			}
		}
	}

	public static void View() {
		System.out
				.println("Would you like to search for a specific(1) Hyperlink or view a list(2) of all Hyperlinks?");
		c = reader.nextLine().charAt(0);
		if (c == '1') {
			System.out.println("Search using URL(1), tag(2) or comment(3)?");
			c = reader.nextLine().charAt(0);
			if (c == '1') {
				System.out.println("Insert the URL you are looking for: ");
				c1 = reader.nextLine();
				data.viewHyperlink(c1);
			} else if (c == '2') {
				System.out.println("Insert the tag you are looking for: ");
				c2 = reader.nextLine();
				System.out
						.println("The Hyperlinks that contain that tag are: ");
				data.viewHyperlinkByTag(c2);
			} else if (c == '3') {
				System.out.println("Insert the comment you are looking for: ");
				c3 = reader.nextLine();
				System.out
						.println("The Hyperlinks that contain that comment are: ");
				data.viewHyperlinkByComment(c3);
			}
		} else if (c == '2') {
			System.out
					.println("View list sorted by URL(1), creation date(2) or last modification date(3)?");
			c = reader.nextLine().charAt(0);
			if (c == '1') {
				data.sortByURL();
				data.viewAll();
			} else if (c == '2') {
				data.sortByCreationDate();
				data.viewAll();
			} else if (c == '3') {
				data.sortByModificationDate();
				data.viewAll();
			}
		}
	}
}

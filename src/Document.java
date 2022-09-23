import java.util.ListIterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document{
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
	}

	public void load(Scanner scan) {
		while (scan.hasNext()) {
			String line = scan.nextLine();
			if (line.equals("eod")) return;

			String[] lineArray = line.split(" ");

			String regex = "link=(.+)";
			Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			Matcher matcher;

			for (String string : lineArray) {
				matcher = pattern.matcher(string);

				if (matcher.matches())
					if(isCorrectLink(matcher.group(1))){
						link.add(new Link(matcher.group(1).toLowerCase()));
					}else if (isCorrectIdWeight(matcher.group(1))){
						link.add(createLink(matcher.group(1).toLowerCase()));
					}
			}
		}
	}

	public static boolean isCorrectLink(String id) {
		// (([0-9]*)\)
		String regex = "[a-zA-Z][a-zA-Z_0-9]*";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(id);

		return matcher.matches();
	}

	public static boolean isCorrectIdWeight(String id) {
		// (([0-9]*)\)
		boolean result = false;

		String regex = "[a-zA-Z][a-zA-Z_0-9]*(.*)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(id);

		if (matcher.matches()){
			String weightRegex = "(\\({1}([1-9]{1}[0-9]*)\\){1})";
			Pattern weightPattern = Pattern.compile(weightRegex);
			Matcher weightMatcher = weightPattern.matcher(matcher.group(1));

			return weightMatcher.matches();
		}

		return false;
	}

	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	private static Link createLink(String link) {
		String regex = "([a-zA-Z][a-zA-Z_0-9]*)\\((.*)\\)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(link);

		if ( matcher.matches()){

			return new Link(matcher.group(1), Integer.parseInt(matcher.group(2)));
		}
		return null;
	}

	public static boolean isCorrectId(String id) {
		// (([0-9]*)\)
		String regex = "[a-zA-Z][a-zA-Z_0-9]*";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(id);

		return matcher.matches();
	}

	@Override
	public String toString() {
		return "Document: "+ name + link.toString().toLowerCase(Locale.ROOT);
	}

	public String toStringReverse() {
		return "Document: "+ name + link.toStringReverse().toLowerCase(Locale.ROOT);
	}
}
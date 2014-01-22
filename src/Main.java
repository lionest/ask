import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		String regex = "src\\s*=\\s*(\"|'|)(\\S){1,}(\"|'|\\s)";
		Pattern parttern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE
				| Pattern.MULTILINE);
		Matcher m = parttern.matcher("ww src = \"h.sdsd;fk.asdfsadfsaf232r.,&&3 />");
		while (m.find()) {
			String s0 = m.group();
			String s1 = m.group(1);
			System.out.println(s0 );
		}

	}
}

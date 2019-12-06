package util;

public class LibrarianUtil {

	public static boolean emptyFields(String[] fields) {
		for (String field : fields) {
			if(field.isEmpty())
				return true;
		}
		return false;
	}
}

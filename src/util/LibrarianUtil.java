package util;

public class LibrarianUtil {

	public static boolean emptyFields(String[] fields) {
		for (String field : fields) {
			if(field.isEmpty())
				return true;
		}
		return false;
	}
	
	/**
	 * validates if the number is at least 1
	 * @param string
	 * @return
	 */
	public static boolean isNumericAtLeastOne(String string) {
		return string.matches("[1-9]+[0-9]*");
	}
}

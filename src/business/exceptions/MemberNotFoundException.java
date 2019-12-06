package business.exceptions;

import java.io.Serializable;

public class MemberNotFoundException extends Exception implements Serializable {
	
	private static final long serialVersionUID = 3326915348398932420L;
	public MemberNotFoundException() {
		super();
	}
	public MemberNotFoundException(String msg) {
		super(msg);
	}
	public MemberNotFoundException(Throwable t) {
		super(t);
	}
}

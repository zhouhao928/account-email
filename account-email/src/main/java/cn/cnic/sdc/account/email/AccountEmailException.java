package cn.cnic.sdc.account.email;

import javax.mail.MessagingException;

public class AccountEmailException extends Exception {

	/**
	 * auto genarated by eclipse.
	 */
	private static final long serialVersionUID = 1L;

	public AccountEmailException(String string, MessagingException e) {
		super(string, e);
	}

}

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class MyMailAuthenticator extends Authenticator {
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("epaman09@gmail.com", "****");
	}
}

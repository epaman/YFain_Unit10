import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class MailReader {

	// gmail settings
	public static final String EMAIL_USER_ADDRESS = "epaman09@gmail.com";
	public static final String MAIL_POP_HOST = "pop.gmail.com";

	private Store store;

	public static void main(String[] args) {
		try {
			// Get the mail password
			String password = null;
			if (args.length != 1) {
				System.out
						.println("You may enter gmail password when run program: java MailReader password");
				System.out.print("Enter password to read gmail: ");
				BufferedReader keyboard = new BufferedReader(
						new InputStreamReader(System.in));
				password = keyboard.readLine();
			} else {
				password = args[0];
			}

			// Create instance of MailReader
			MailReader mailerReader = new MailReader(password);

			// Retrieve messages
			mailerReader.retrieveMessages();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MailReader(String password) throws Exception {
		Properties properties = System.getProperties();
		Session session = Session.getDefaultInstance(properties);

		store = session.getStore("pop3s");
		store.connect(MAIL_POP_HOST, EMAIL_USER_ADDRESS, password);
	}

	private void retrieveMessages() throws Exception {
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);

		Message[] messages = folder.getMessages();
		System.out.println("Messages: " + messages.length);

		for (Message message : messages) {
			try {
				Address[] ad = message.getFrom();
				System.out.print("From: ");
				for (Address addr : ad) {
					System.out.println(addr + " ");
				}
				System.out.println("      " + message.getSubject());
			} catch (MessagingException e) {
				System.out.println("Can not read the the field From the Subject in the message");
				e.printStackTrace();
			}
		}
	}

}

import javax.mail.Message;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.cnic.sdc.account.email.AccountEmailService;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

public class AccountEmailServiceTest {
	private GreenMail greenMail;

	@Before
	public void startmailServer() throws Exception {
		greenMail = new GreenMail(ServerSetup.SMTP);
		greenMail.setUser("ineverdie@126.com", "myj19891217");
		greenMail.start();
	}

	@Test
	public void testSendMail() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"account-email.xml");
		AccountEmailService accountEmailService = (AccountEmailService) ctx
				.getBean("accountEmailService");
		String to = "zhouhao@cnic.cn";
		String subject = "Test Subect";
		String htmlText = "<p>tset</p>";
		accountEmailService.sendMail(to, subject, htmlText);
		greenMail.waitForIncomingEmail(2000, 1);
		Message[] msgs = greenMail.getReceivedMessages();
		System.out.println(msgs);
		System.out.println(msgs.length);
		// assertEquals(1, msgs.length);
		// assertEquals(subject, msgs[0].getSubject());
		// assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());
	}

	@After
	public void stopMailService() throws Exception {
		greenMail.stop();
	}
}

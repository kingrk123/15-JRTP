package in.nit.util;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import in.nit.constants.AppConstants;

@Component
public class EmailUtils {

	@Autowired
    JavaMailSender mailSender;
	
	private static Logger logger = LoggerFactory.getLogger(EmailUtils.class);
	
 
    public boolean sendEmail(String to, String subject,String body) {
    	boolean isSent=false;
    	try {
    		 MimeMessage mimeMessage = mailSender.createMimeMessage();
    		 
             MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
  
             mimeMessageHelper.setSubject(subject);
             mimeMessageHelper.setTo(to);
             mimeMessageHelper.setText(body, true);
  
             mailSender.send(mimeMessageHelper.getMimeMessage());
             isSent =true; 
		} catch (Exception e) {
			logger.error(AppConstants.EXCEPTION_OCCURED+e.getMessage(), e);
		}
            return isSent;
    }
}

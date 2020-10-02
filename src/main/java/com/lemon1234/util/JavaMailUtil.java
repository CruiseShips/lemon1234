package com.lemon1234.util;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaMailUtil {
	
	public static void sendMail(String to, String password) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("<style class='fox_global_style'>div.fox_html_content { line-height: 1.5; }</style>");
		sBuffer.append("<div><span id='_FoxCURSOR'></span><br></div><div>");
		sBuffer.append("<span style='color: rgb(0, 0, 0); background-color: rgba(0, 0, 0, 0);'>&nbsp; &nbsp; 尊敬的管理员，您的密码已经被修改</span></div>");
		sBuffer.append("<div><span style='color: rgb(0, 0, 0); line-height: 1.5; background-color: rgba(0, 0, 0, 0);'>&nbsp; &nbsp;&nbsp;</span>");
		sBuffer.append("<span style='color: rgb(0, 0, 0); line-height: 1.5; background-color: rgba(0, 0, 0, 0);'>新密码为 &nbsp;");
		sBuffer.append(password);
		sBuffer.append("</span></div> <div><br></div>");
		sBuffer.append("<hr id='FMSigSeperator' style='width: 210px; height: 1px;' color='#b5c4df' size='1' align='left'> <div><span id='_FoxFROMNAME'>");
		sBuffer.append("<div style='margin: 10px;'>lemon1234 系统邮件<br><div style='font-family: verdana; font-size: 10pt;'><br></div></div></span></div>");
		
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
            message.setFrom(new InternetAddress(javax.mail.internet.MimeUtility.encodeText("lemon1234.com 系统邮件") + "<18500399506@163.com>"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("lemon1234系统邮件_修改密码");
            Multipart multipart = new MimeMultipart();
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sBuffer.toString(), "text/html;charset=utf-8");
            multipart.addBodyPart(contentPart);
            message.setContent(multipart);
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.163.com", "18500399506@163.com", "chen338651aa");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) throws Exception {
		sendMail("13714312440@163.com", "123123");
	}
}

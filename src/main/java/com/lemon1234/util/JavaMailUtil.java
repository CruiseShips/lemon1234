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
	
//	public static boolean sendMail(String to, String text, String title) throws Exception {
//		String html = "<style class='fox_global_style'>div.fox_html_content { line-height: 1.5; }</style> <div><span id='_FoxCURSOR'>"
//				+ "</span><br></div><div><span style='color: rgb(0, 0, 0); background-color: rgba(0, 0, 0, 0);'>&nbsp; &nbsp; 尊敬的管理员，您的密码已经被修改</span>"
//				+ "</div><div><span style='color: rgb(0, 0, 0); line-height: 1.5; background-color: rgba(0, 0, 0, 0);'>&nbsp; &nbsp;&nbsp;</span>"
//				+ "<span style='color: rgb(0, 0, 0); line-height: 1.5; background-color: rgba(0, 0, 0, 0);'>新密码 &nbsp;123456</span></div> <div><br></div>"
//				+ "<hr id='FMSigSeperator' style='width: 210px; height: 1px;' color='#b5c4df' size='1' align='left'> <div><span id='_FoxFROMNAME'>"
//				+ "<div style='margin: 10px;'>lemon1234 系统邮件<br><div style='font-family: verdana; font-size: 10pt;'><br></div></div></span>"
//				+ "</div>";
//        Properties props = new Properties();
//        props.setProperty("mail.smtp.host", "smtp.163.com"); // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
//        props.put("mail.smtp.host", "smtp.163.com"); // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
//        props.put("mail.smtp.auth", "true"); // 用刚刚设置好的props对象构建一个session
//        Session session = Session.getDefaultInstance(props); // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
//        // 用（你可以在控制台（console)上看到发送邮件的过程）
//        session.setDebug(true); // 用session为参数定义消息对象
//        MimeMessage message = new MimeMessage(session); // 加载发件人地址
//        try {
//            message.setFrom(new InternetAddress(javax.mail.internet.MimeUtility.encodeText("lemon1234.com 系统邮件") + "<18500399506@163.com>"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // 加载收件人地址
//            message.setSubject(title); // 加载标题
//            Multipart multipart = new MimeMultipart(); // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
//            BodyPart contentPart = new MimeBodyPart(); // 设置邮件的文本内容
//            contentPart.setContent(text, "text/html;charset=utf-8");
//            multipart.addBodyPart(contentPart);
//            message.setContent(multipart);
//            message.saveChanges(); // 保存变化
//            Transport transport = session.getTransport("smtp"); // 连接服务器的邮箱
//            transport.connect("smtp.163.com", "18500399506@163.com", "chen338651aa"); // 把邮件发送出去
//            transport.sendMessage(message, message.getAllRecipients());
//            transport.close();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
}

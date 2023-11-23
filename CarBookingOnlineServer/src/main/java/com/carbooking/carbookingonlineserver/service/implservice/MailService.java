package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.service.IMailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
@Service
public class MailService implements IMailService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;
    public static final String EMAIL_TEMPLATE = "email";
    public static final String EMAIL_SUBJECT = "Chúc mừng bạn đã đặt vé thành công";
    public static final String UTF_8_ENCODING = "UTF-8";

    private final String fromEmail = "lekhanhuyenn.12@gmail.com";

    @Override
    public void senMailRenfund(String to) {
        try {

            String text = "Vé của bạn hủy thành công! cảm ơn bạn vì đã sử dụng" +
                    "dịch vụ của tôi!" +
                    " hy vọng bạn sẽ sớm quay trở lại";


            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject("BẠN ĐÃ HỦY VÉ THÀNH CÔNG ");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(text, true);
            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    @Async
    public void sendHtmlEmail(String picup, String dropoff, String date,String price, String to) {
        try {

            String text = "Đặt Vé xe đi từ: " + picup + "<br>" +
                    "Điểm đến của chuyến đi: " + dropoff + "<br>" +
                    "Ngày đi: " + date + "<br>" +
                    "Giá Vé: " + price + "<br>" +
                    "Chúc Quý Khách Vui Vẻ!";


            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(EMAIL_SUBJECT);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(text, true);
            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void sendMailPassword(String Pass, String to ) {
        try {

            String text = "Mật Khẩu Khôi Phục Của Bạn Là: " + Pass;


            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject("KHÔI PHỤC MẬT KHẨU THÀNH CÔNG!");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(text, true);
            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }
}

package olsvc.controllers;

import olsvc.mail.SmtpMailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.mail.MessagingException;

@RestController
public class MailController {

    @Autowired
    private SmtpMailSender smtpMailSender;

    @RequestMapping("/send-mail")
    public void sendMail() throws MessagingException {

        smtpMailSender.send("siejek.angelika@gmail.com", "Test mail from Spring", "Howdy");

    }

}

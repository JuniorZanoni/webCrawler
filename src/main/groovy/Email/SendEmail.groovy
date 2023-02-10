package Email

import javax.activation.DataHandler
import javax.activation.DataSource
import javax.activation.FileDataSource
import javax.mail.BodyPart
import javax.mail.Message;
import javax.mail.MessagingException
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class SendEmail {

    void sendOneEmail(String email) {
        String login = "projetoacelera@outlook.com";
        String password = "WebCrawler!"
        String host = "smtp-mail.outlook.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(login));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            message.setSubject("Desafio Web Crawler!");

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("Segue em anexo os arquivos.");

            MimeBodyPart attachment1 = new MimeBodyPart();
            String filename1 = "./Downloads/historico-versoes-de-componentes-TISS.csv"
            DataSource source1 = new FileDataSource(filename1);
            attachment1.setDataHandler(new DataHandler(source1));
            attachment1.setFileName(filename1);

            MimeBodyPart attachment2 = new MimeBodyPart();
            String filename2 = "./Downloads/padrao-de-comunicao-TISS.zip"
            DataSource source2 = new FileDataSource(filename2);
            attachment2.setDataHandler(new DataHandler(source2));
            attachment2.setFileName(filename2);

            MimeBodyPart attachment3 = new MimeBodyPart();
            String filename3 = "./Downloads/tabela-erros-de-envio-ANS.xlsx"
            DataSource source3 = new FileDataSource(filename3);
            attachment3.setDataHandler(new DataHandler(source3));
            attachment3.setFileName(filename3);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(attachment1);
            multipart.addBodyPart(attachment2);
            multipart.addBodyPart(attachment3);

            message.setContent(multipart );

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    void sendMultiplesEmails(List<String> emails) {
        emails.forEach { sendOneEmail(it)}
    }
}

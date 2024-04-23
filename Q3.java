import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
  
class Q3{  
 public static void main(String [] args){  
  
  String to="hr@igniterhub.com";
  final String user="ritikkasotiya7@gmail.com";
  final String password="Ritik@8529";
   
      
  Properties properties = System.getProperties();  
  properties.setProperty("mail.smtp.host", "mail.javatpoint.com");  
  properties.put("mail.smtp.auth", "true");  
  
  Session session = Session.getDefaultInstance(properties,  
   new javax.mail.Authenticator() {  
   protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(user,password);  
   }  
  });  
     
      
  try{  
    MimeMessage message = new MimeMessage(session);  
    message.setFrom(new InternetAddress(user));  
    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
    message.setSubject("Challenge 3 Completed");  
      
         
    BodyPart messageBodyPart1 = new MimeBodyPart();  
    messageBodyPart1.setText("Ritik kasotiya, Computer science branch, 4th semester, 04650404422");  
      
          
    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
  
    String filename = "leavehub.jpg"; 
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName(filename);  
     
     
          
    Multipart multipart = new MimeMultipart();  
    multipart.addBodyPart(messageBodyPart1);  
    multipart.addBodyPart(messageBodyPart2);  
  
     
    message.setContent(multipart );  
     
      
    Transport.send(message);  
   
   System.out.println("message sent");  
   }catch (MessagingException ex) {ex.printStackTrace();}  
 }  
}  
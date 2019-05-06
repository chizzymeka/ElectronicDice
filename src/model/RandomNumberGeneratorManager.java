package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.lang.Math;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class RandomNumberGeneratorManager {

    HashMap<String, String> dataMap;

    public RandomNumberGeneratorManager(HashMap<String, String> dataMap) {
        this.dataMap = dataMap;
    }

    public void validateNumbers() {

        long numberCount = Long.parseLong(dataMap.get("numberCount"));
        long lowerRange = Long.parseLong(dataMap.get("lowerRange"));
        long higherRange = Long.parseLong(dataMap.get("higherRange"));
        long numberOfFormatColumns = Long.parseLong(dataMap.get("numberOfFormatColumns"));
        long maximumNumberCount = 60000L;
        long maximumRange = 1000000000000L;
        String uniqueness = dataMap.get("uniqueness");

        if (numberCount > maximumNumberCount) {
            System.out.println("This number must be less than 60,000.");
            return;
        }
        if (numberOfFormatColumns > numberCount) {
            System.out.println("The number of columns cannot be greater than the number count.");
            return;
        }
        if ((lowerRange < 0) || (higherRange > maximumRange)) {
            System.out.println("This number must be between 0 and 1,000,000,000,000.");
            return;
        }
        if ((uniqueness.equals("Unique")) && numberCount > higherRange) {
            System.out.println("The maximum number cannot be lower than the intended number count.");
            return;
        }
        if (higherRange - lowerRange > 0) {
            generateRandomNumbers(numberCount, lowerRange, higherRange, uniqueness);
        }
    }

    private void generateRandomNumbers(long numberCount, long lowerRange, long higherRange, String uniqueness) {

        ArrayList<Long> result = new ArrayList<>();

        if (uniqueness.equals("Unique")) {
            for (long i = lowerRange; i <= higherRange; i++) {
                result.add(i);
            }
            Collections.shuffle(result);
            for (long i = 0; i < numberCount; i++) {
                System.out.println(result.get((int)i));
            }
        } else if (uniqueness.equals("nonUnique")) {
            for (long i = lowerRange; i <= higherRange; i++) {
                long randomNumber = (long) (Math.random() * numberCount) + lowerRange;
                result.add(randomNumber);
                System.out.println(randomNumber);
            }
        }
        formatResult(result);
    }

    private void formatResult(ArrayList<Long> result) {

        processEmailAddresses();
    }

    private void processEmailAddresses() {
        String emailAddresses = dataMap.get("emailAddresses");
        //Format and reassign it to the dataMap, that is, dataMap.put("emailAddresses", FORMATTEDEMAILS);
        emailResult();
    }

    private void emailResult() {

        String fromEmail = "chizzymeka@yahoo.co.uk";
        String password ="bgknoccout5114";
        String emailAddresses = "chizzymeka@yahoo.co.uk, plus447850441897@chizzymeka.co.uk";
        String[] recipientList = emailAddresses.split(",");
        InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
        int counter = 0;
        for (String recipient : recipientList) {
            try {
                recipientAddress[counter] = new InternetAddress(recipient.trim());
            } catch (AddressException addressException) {
                addressException.printStackTrace();
            }
            counter++;
        }

        String host = "smtp.mail.yahoo.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", fromEmail);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipients(Message.RecipientType.TO, recipientAddress);
            message.setSubject("This is the Subject Line!");
            message.setText("This is actual message");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, fromEmail, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Sent message successfully....");
        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
        }
    }

    private void saveResultToTrail() {

    }
}
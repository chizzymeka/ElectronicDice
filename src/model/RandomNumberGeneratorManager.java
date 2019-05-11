package model;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import java.util.Date;
import java.sql.Timestamp;

public class RandomNumberGeneratorManager {

    HashMap<String, String> dataMap;

    public RandomNumberGeneratorManager(HashMap<String, String> dataMap) {
        this.dataMap = dataMap;
    }

    public void validateData() {

        long numberCount = Long.parseLong(dataMap.get("numberCount"));
        long lowerRange = Long.parseLong(dataMap.get("lowerRange"));
        long higherRange = Long.parseLong(dataMap.get("higherRange"));
        long numberOfFormatColumns = Long.parseLong(dataMap.get("numberOfFormatColumns"));
        final long MAXIMUM_NUMBER_COUNT = 60000L;
        final long MAXIMUM_RANGE = 1000000000000L;
        String uniqueness = dataMap.get("uniqueness");
        String emailAddresses = dataMap.get("emailAddresses").trim().replaceAll("\\s","");
        int maximumNumberOfRecipients = 10;

        if ("Y".equals(dataMap.get("sendEmailCopyToSelf")) && !emailAddresses.contains("chizzymeka@yahoo.co.uk")) {
            emailAddresses += "," + "chizzymeka@yahoo.co.uk";
            maximumNumberOfRecipients = 11;
        }
        dataMap.put("emailAddresses", emailAddresses);
        if (emailAddresses.split(",").length > maximumNumberOfRecipients) {
            System.out.println("You can only have a maximum of ten recipients.");
            return;
        }
        if (numberCount > MAXIMUM_NUMBER_COUNT) {
            System.out.println("This number must be less than 60,000.");
            return;
        }
        if (numberOfFormatColumns > 20) {
            System.out.println("The number of columns cannot be greater than 20.");
            return;
        }
        if (numberOfFormatColumns > numberCount) {
            numberOfFormatColumns = numberCount;
            dataMap.put("numberOfFormatColumns", String.valueOf(numberOfFormatColumns));
        }
        if ((lowerRange < 0) || (higherRange > MAXIMUM_RANGE)) {
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

        ArrayList<Long> randomNumbers = new ArrayList<>();

        if (uniqueness.equals("Unique")) {
            for (long i = lowerRange; i <= higherRange; i++) {
                randomNumbers.add(i);
            }
            Collections.shuffle(randomNumbers);
            for (long i = 0; i < numberCount; i++) {
            }
        } else if (uniqueness.equals("nonUnique")) {
            for (long i = lowerRange; i <= higherRange; i++) {
                long randomNumber = (long) (Math.random() * numberCount) + lowerRange;
                randomNumbers.add(randomNumber);
            }
        }

        if ("placeInTrail".equals(dataMap.get("trail"))) {
            saveResultToTrail(randomNumbers);
        } else if ("doNotPlaceInTrail".equals(dataMap.get("trail"))) {
            setUpEmailMessageContent(randomNumbers);
        }
    }

    private void saveResultToTrail(ArrayList<Long> randomNumbers) {

        System.out.println("saveResultToTrail called!");
        // Save random numbers to trail database.

        setUpEmailMessageContent(randomNumbers);
    }

    private void setUpEmailMessageContent(ArrayList<Long> randomNumbers) {

        long numberOfFormatColumns = Long.parseLong(dataMap.get("numberOfFormatColumns"));
        String result = "";
        long counter = 0;

        for (long randomNumber : randomNumbers) {
            counter++;
            result += String.valueOf(randomNumber);
            if (counter == numberOfFormatColumns) {
                result += "\n";
                counter = 0;
            }
        }

        try {

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream emailMessageBodyInputStream = classLoader.getResourceAsStream("emailMessageBody.html");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(emailMessageBodyInputStream));
            StringBuilder emailMessageBodyStringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                line = line.replace("descriptionPlaceHolder", dataMap.get("description"));
                line = line.replace("resultPlaceHolder", result);
                line = line.replace("numberCountPlaceholder", dataMap.get("numberCount"));
                line = line.replace("rangePlaceHolder", dataMap.get("lowerRange") + "-" + dataMap.get("higherRange"));
                line = line.replace("uniquenessPlaceHolder", ("Unique".equals(dataMap.get("uniqueness")) ? "The numbers are unique." : "The numbers are not unique."));
                line = line.replace("trailLinkPlaceHolder", "TRAIL LINK");
                line = line.replace("timestampPlaceHolder", new Timestamp(new Date().getTime()).toString());
                emailMessageBodyStringBuilder.append(line);
            }
            bufferedReader.close();

            dataMap.put("emailMessageBody", emailMessageBodyStringBuilder.toString());

            Properties properties = new Properties();
            properties.load(emailMessageBodyInputStream);
            
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
        emailResult();
    }

    private void emailResult() {

        String fromEmail = "chizzymeka@gmail.com";
        String userName = "chizzymeka";
        String password ="vfbxfhwlsctlubnl";
        String emailAddresses = dataMap.get("emailAddresses");
        String[] recipients = emailAddresses.split(",");
        InternetAddress[] recipientAddress = new InternetAddress[recipients.length];
        int counter = 0;

        for (String recipient : recipients) {
            try {
                recipientAddress[counter] = new InternetAddress(recipient);
            } catch (AddressException addressException) {
                addressException.printStackTrace();
            }
            counter++;
        }

        String host = "smtp.gmail.com";
        String htmlMessage = dataMap.get("emailMessageBody");

        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", userName);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);

        try {

            MimeMessage message = new MimeMessage(session);
            message.setContent(htmlMessage, "text/html;charset=UTF-8");
            message.setFrom(new InternetAddress(userName));
            message.addRecipients(Message.RecipientType.TO, recipientAddress);
            message.setSubject("This is the Subject Line!");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, fromEmail, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Sent message successfully....");

        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
        }
    }
}
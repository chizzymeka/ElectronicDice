package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import model.RandomNumberGeneratorManager;

import model.RandomNumberGenerator;

@WebServlet(name = "ControllerServlet", loadOnStartup = 1, urlPatterns={"/generatorRandomNumber"})
public class ControllerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userPath = request.getServletPath();

        if (userPath.equals("/generatorRandomNumber")) {

            String numberCount = request.getParameter("numberCount");
            String lowerRange = request.getParameter("lowerRange");
            String higherRange = request.getParameter("higherRange");
            String numberOfFormatColumns = request.getParameter("numberOfFormatColumns");
            String uniqueness = request.getParameter("uniqueness");
            String trail = request.getParameter("trail");
            String emailSubject = request.getParameter("emailSubject");
            String emailAddresses = request.getParameter("emailAddresses");
            String description = request.getParameter("description");
            String sendEmailCopyToSelf = request.getParameter("sendEmailCopyToSelf");

            RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
            randomNumberGenerator.setNumberCount(numberCount);
            randomNumberGenerator.setLowerRange(lowerRange);
            randomNumberGenerator.setHigherRange(higherRange);
            randomNumberGenerator.setNumberOfFormatColumns(numberOfFormatColumns);
            randomNumberGenerator.setUniqueness(uniqueness);
            randomNumberGenerator.setTrail(trail);
            randomNumberGenerator.setEmailSubject(emailSubject);
            randomNumberGenerator.setEmailAddresses(emailAddresses);
            randomNumberGenerator.setDescription(description);
            randomNumberGenerator.setSendEmailCopyToSelf(sendEmailCopyToSelf);

            HashMap<String, String> dataMap = new HashMap<>();
            dataMap.put("numberCount", randomNumberGenerator.getNumberCount());
            dataMap.put("lowerRange", randomNumberGenerator.getLowerRange());
            dataMap.put("higherRange", randomNumberGenerator.getHigherRange());
            dataMap.put("numberOfFormatColumns", randomNumberGenerator.getNumberOfFormatColumns());
            dataMap.put("uniqueness", randomNumberGenerator.getUniqueness());
            dataMap.put("trail", randomNumberGenerator.getTrail());
            dataMap.put("emailSubject", randomNumberGenerator.getEmailSubject());
            dataMap.put("emailAddresses", randomNumberGenerator.getEmailAddresses());
            dataMap.put("description", randomNumberGenerator.getDescription());
            dataMap.put("sendEmailCopyToSelf", randomNumberGenerator.getSendEmailCopyToSelf());

            RandomNumberGeneratorManager randomNumberGeneratorManager = new RandomNumberGeneratorManager(dataMap);
            randomNumberGeneratorManager.validateNumbers();

            userPath = "/index";
        }

        String url = userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import model.RandomNumberGeneratorBean;

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

            RandomNumberGeneratorBean randomNumberGeneratorBean = new RandomNumberGeneratorBean();
            randomNumberGeneratorBean.setNumberCount(numberCount);
            randomNumberGeneratorBean.setLowerRange(lowerRange);
            randomNumberGeneratorBean.setHigherRange(higherRange);
            randomNumberGeneratorBean.setNumberOfFormatColumns(numberOfFormatColumns);
            randomNumberGeneratorBean.setUniqueness(uniqueness);
            randomNumberGeneratorBean.setTrail(trail);
            randomNumberGeneratorBean.setEmailSubject(emailSubject);
            randomNumberGeneratorBean.setEmailAddresses(emailAddresses);
            randomNumberGeneratorBean.setDescription(description);
            randomNumberGeneratorBean.setSendEmailCopyToSelf(sendEmailCopyToSelf);

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
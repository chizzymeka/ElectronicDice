package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

            System.out.println("numberCount: " + numberCount);
            System.out.println("lowerRange: " + lowerRange);
            System.out.println("higherRange: " + higherRange);
            System.out.println("numberOfFormatColumns: " + numberOfFormatColumns);
            System.out.println("uniqueness: " + uniqueness);
            System.out.println("trail: " + trail);
            System.out.println("emailSubject: " + emailSubject);
            System.out.println("emailSubemailAddressesject: " + emailAddresses);
            System.out.println("description: " + description);
            System.out.println("sendEmailCopyToSelf: " + sendEmailCopyToSelf);

            userPath = "/index";
        }

        String url = userPath + ".jsp";
        System.out.println("url: " + url);

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
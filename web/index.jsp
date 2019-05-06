<%--
  Created by IntelliJ IDEA.
  User: Chizzy Meka
  Date: 01/05/2019
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/main.css" />
  <title>Random Number Generator</title>
</head>
<body>
<script>
  $(document).ready(function () {
    $("#currentYear").text(new Date().getFullYear())
  })
</script>
<div class="flex-container">
  <div id="top" name="top">
    Random Number Generator
  </div>
  <div id="middle" name="middle">
    <form id="randomNumberGeneratorForm" name="randomNumberGeneratorForm" action="generatorRandomNumber" method="post">
      <table id="randomNumberGeneratorFormTable" name="randomNumberGeneratorFormTable">
        <tr><td>Step 1: The Integers</td></tr>
        <tr><td>Generate <input type="text" id="numberCount" name="numberCount" value="5"> random integers (maximum 60,000).</td></tr>
        <tr><td>Each integer should have a value between <input type="text" id="lowerRange" name="lowerRange" value="1"> and <input type="text" id="higherRange" name="higherRange" value="5"> (both inclusive; limits ±1,000,000,000,000).</td></tr>
        <tr><td>Format in <input type="text" id="numberOfFormatColumns" name="numberOfFormatColumns" value="1"> column(s).</td></tr>

        <tr><td><br></td></tr>

        <tr><td>Step 2: Uniqueness</td></tr>
        <tr><td><input type="radio" name="uniqueness" value="Unique" checked> Each integer should be unique (like raffle tickets drawn from a hat).</td></tr>
        <tr><td><input type="radio" name="uniqueness" value="nonUnique"> Repeat integers are allowed (like a series of die rolls).</td></tr>

        <tr><td><br></td></tr>

        <tr><td>Step 3: Your Trail</td></tr>
        <tr><td>Do you want to place these numbers in your trail so other people can view them? [explain this]</td></tr>
        <tr><td><input type="radio" name="trail" value="placeInTrail"> Yes, place these numbers in my trail.</td></tr>
        <tr><td><input type="radio" name="trail" value="doNotPlaceInTrail" checked> No, keep these numbers private.</td></tr>

        <tr><td><br></td></tr>

        <tr><td>Step 4: Email Notification</td></tr>
        <tr><td>If you want to email a notification of these numbers to someone, fill in the following fields; otherwise leave blank.</td></tr>
        <tr><td>Subject line of email:</td></tr>
        <tr><td><input type="text" id="emailSubject" name="emailSubject" value="chizzymeka@yahoo.co.uk"></td></tr>
        <tr><td>Recipient email addresses (max 10) separated by newlines or commas:</td></tr>
        <tr><td>
                            <textarea id="emailAddresses" name="emailAddresses" rows="4" cols="50">
                                mbahchidubem@ymail.com,chimaohakwe@yahoo.co.uk,chizzymeka@yahoo.co.uk,emeka.udenka@yahoo.com,mbonu@live.co.uk,iykez2002@yahoo.com,haridaanyanwu@yahoo.com,martins.ihediwa@gmail.com</textarea></td></tr>
        <tr><td>Textual description of the numbers (max 400 characters) to be included in the body of the email:</td></tr>
        <tr><td><textarea id="description" name="description" rows="4" cols="50">Isusu Disbursement Sequence.</textarea></td></tr>
        <tr><td><input type="checkbox" id="sendEmailCopyToSelf" name="sendEmailCopyToSelf" value="Y" checked> Send a copy to your registered email address.</td></tr>

        <tr><td><br></td></tr>

        <tr><td>Step 5: Submission</td></tr>
        <tr><td>Be patient! It may take a little while to generate your numbers...</td></tr>
        <tr><td><input type="submit" id="submit" name="submit" value="Submit">&nbsp&nbsp<input type="reset" id="resetForm" name="resetForm" value="Reset Form">&nbsp&nbsp<button type="button" id="switchToAdvancedMode" name="switchToAdvancedMode">Switch to Advanced Mode</button></td></tr>
      </table>
    </form>
  </div>
  <div id="bottom" name="bottom">
    &copy; <span id="currentYear"></span> | Contact: contact@electronicdice.co.uk
  </div>
</div>
</body>
</html>
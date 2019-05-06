package model;

public class RandomNumberGenerator {

    private String numberCount;
    private String lowerRange;
    private String higherRange;
    private String numberOfFormatColumns;
    private String uniqueness;
    private String trail;
    private String emailSubject;
    private String emailAddresses;
    private String description;
    private String sendEmailCopyToSelf;

    public String getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(String numberCount) {
        this.numberCount = numberCount;
    }

    public String getLowerRange() {
        return lowerRange;
    }

    public void setLowerRange(String lowerRange) {
        this.lowerRange = lowerRange;
    }

    public String getHigherRange() {
        return higherRange;
    }

    public void setHigherRange(String higherRange) {
        this.higherRange = higherRange;
    }

    public String getNumberOfFormatColumns() {
        return numberOfFormatColumns;
    }

    public void setNumberOfFormatColumns(String numberOfFormatColumns) {
        this.numberOfFormatColumns = numberOfFormatColumns;
    }

    public String getUniqueness() {
        return uniqueness;
    }

    public void setUniqueness(String uniqueness) {
        this.uniqueness = uniqueness;
    }

    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
        this.trail = trail;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(String emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSendEmailCopyToSelf() {
        return sendEmailCopyToSelf;
    }

    public void setSendEmailCopyToSelf(String sendEmailCopyToSelf) {
        this.sendEmailCopyToSelf = sendEmailCopyToSelf;
    }
}
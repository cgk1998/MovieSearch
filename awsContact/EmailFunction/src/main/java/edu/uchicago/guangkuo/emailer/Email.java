package edu.uchicago.guangkuo.emailer;

public class Email {

    private String subject;
    private String body;
    private String emailFrom;

    public Email(String subject, String body, String email) {
        this.subject = subject;
        this.body = body;
        this.emailFrom = email;
    }

    public Email() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String email) {
        this.emailFrom = email;
    }

    @Override
    public String toString() {
        return "Message{" +
                "subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", email='" + emailFrom + '\'' +
                '}';
    }
}

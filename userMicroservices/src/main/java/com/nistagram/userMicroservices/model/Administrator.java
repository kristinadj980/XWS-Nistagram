package model;

import java.util.List;

public class Administrator extends Person{

    private List<VerificationRequest> verificationRequests;

    public Administrator() {
        super();
    }

    public Administrator(List<VerificationRequest> verificationRequests) {
        super();
        this.verificationRequests = verificationRequests;
    }

    public List<VerificationRequest> getVerificationRequests() {
        return verificationRequests;
    }

    public void setVerificationRequests(List<VerificationRequest> verificationRequests) {
        this.verificationRequests = verificationRequests;
    }

}
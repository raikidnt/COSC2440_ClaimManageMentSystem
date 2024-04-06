package Model;

import Model.Customer;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Claim {
    private String claimId;
    private Date claimDate;
    private Customer insuredPerson;
    private Date examDate;
    private List<String> documentList;
    private float claimAmount;
    private String status;
    private String bankName;
    private String bankNumber;
    private String receiverName;

    public Claim(String claimId, Date claimDate, Customer
                insuredPerson, Date examDate, List<String> documentList,
                 float claimAmount, String status,
                 String bankName, String bankNumber, String receiverName) {
        this.claimId = claimId;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.examDate = examDate;
        this.documentList = documentList;
        this.claimAmount = claimAmount;
        this.status = status;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
        this.receiverName = receiverName;
    }

    public Claim(String claimId, Date claimDate, Customer insuredPerson,
                 Date examDate, float claimAmount, String status, String bankName,
                 String bankNumber, String receiverName) {
        this.claimId = claimId;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.examDate = examDate;
        this.claimAmount = claimAmount;
        this.status = status;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
        this.receiverName = receiverName;
        this.documentList = new ArrayList<>();
    }

    public Claim(String claimId, float claimAmount, String status, String bankName, String bankNumber, String receiverName) {
        this.claimId = claimId;
        this.claimDate = claimDate;
        this.examDate = examDate;
        this.claimAmount = claimAmount;
        this.status = status;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
        this.receiverName = receiverName;
        this.insuredPerson = null;
        this.documentList = null;
        this.claimDate = new Date();
        this.examDate = new Date();
    }

    public String getClaimId() {
        return claimId;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(Customer insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public List<String> getDoccumentList() {
        return documentList;
    }
    public void setDoccumentList(List<String> doclist){ this.documentList = doclist; }

//   doccument add, remove ,clear
    public void addDocument(String document) {
        if (documentList.contains(document)) {
            return;
        }
        documentList.add(document);
    }
    public void removeDocument(String document) {
        if (documentList.contains(document)) {
            return;
        }
        documentList.remove(document);
    }
    public void clearDocument() {
        documentList.clear();
    }

    public float getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(float claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Override
    public String toString() {
        return "Model.Claim{" +
                "claimId='" + claimId + '\'' +
                ", claimDate=" + claimDate +
                ", insuredPerson=" + insuredPerson.getFullName() +
                ", examDate=" + examDate +
                ", documentList=" + documentList +
                ", claimAmount=" + claimAmount +
                ", status=" + status + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankNumber='" + bankNumber + '\'' +
                ", receiverName='" + receiverName + '\'' +
                '}';
    }
}

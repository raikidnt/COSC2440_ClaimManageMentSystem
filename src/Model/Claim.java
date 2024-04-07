package Model;

import Model.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Claim {
    private String claimId;
    private LocalDate claimDate;
    private Customer insuredPerson;
    private LocalDate examDate;
    private List<String> documentList;
    private float claimAmount;
    private String status;
    private String bankName;
    private String bankNumber;
    private String receiverName;

    public Claim(String claimId, String claimDate, Customer
                insuredPerson, String examDate, List<String> documentList,
                 float claimAmount, String status,
                 String bankName, String bankNumber, String receiverName) {
        this.claimId = claimId;
        this.claimDate = LocalDate.parse(claimDate);
        this.insuredPerson = insuredPerson;
        this.examDate = LocalDate.parse(examDate);
        this.documentList = documentList;
        this.claimAmount = claimAmount;
        this.status = status;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
        this.receiverName = receiverName;
    }

    public Claim(String claimId, String claimDate, String examDate, ArrayList<String> documentList, float claimAmount, String status, String bankName, String bankNumber, String receiverName) {
        this.claimId = claimId;
        this.claimDate = LocalDate.parse(claimDate);
        if (Objects.equals(examDate, "null")){
           this.examDate = null;
        }
        else{
            this.examDate = LocalDate.parse(examDate);
        }
        this.claimAmount = claimAmount;
        this.status = status;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
        this.receiverName = receiverName;
        this.insuredPerson = null;
    }
    public Claim(String claimId, LocalDate claimDate,Customer insuredPerson, ArrayList<String> documentList, float claimAmount, String status, String bankName, String bankNumber, String receiverName) {
        this.claimId = claimId;
        this.claimDate = claimDate;
        this.examDate = null;
        this.claimAmount = claimAmount;
        this.status = status;
        this.bankName = bankName;
        this.bankNumber = bankNumber;
        this.receiverName = receiverName;
        this.insuredPerson = insuredPerson;
        this.documentList = documentList;
    }


    public String getClaimId() {
        return claimId;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
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

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public List<String> getDoccumentList() {
        return documentList;
    }
    public void setDoccumentList(List<String> doclist){ this.documentList = doclist; }

//   document add, remove ,clear
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
        return "claimId='" + claimId + '\'' +
                ", claimDate=" + claimDate +
                ", insuredPerson=" + insuredPerson.getFullName() +
                ", examDate=" + examDate +
                ", documentList=" + documentList +
                ", claimAmount=" + claimAmount +
                ", status=" + status + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankNumber='" + bankNumber + '\'' +
                ", receiverName='" + receiverName + '\'' + '\'';
    }
}

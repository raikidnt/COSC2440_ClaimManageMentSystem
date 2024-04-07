/**
 * @author DoNhatThanh-s3977947
 */
package Model;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Claim {
    private final String claimId;
    private LocalDate claimDate;
    private Customer insuredPerson;
    private LocalDate examDate;
    private List<String> documentList;
    private float claimAmount;
    private String status;
    private String bankName;
    private String bankNumber;
    private String receiverName;

//    Constructors
    public Claim(String claimId, String claimDate, String examDate, ArrayList<String> documentList, float claimAmount, String status, String bankName, String bankNumber, String receiverName) {
        this.claimId = claimId;
        this.claimDate = LocalDate.parse(claimDate);
        if (Objects.equals(examDate, "null")){
           this.examDate = null;
        }
        else{
            this.examDate = LocalDate.parse(examDate);
        }
        this.documentList = documentList;
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

//    Getters and Setters
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

//    Data writing method, use to parse the documentList to a string
    public String documentListData(){
        if (documentList == null){
            return "[]";
        } else if (documentList.isEmpty()) {
            return "[]";
        }
        StringBuilder data = new StringBuilder("[");
        for (String document : documentList){
            if(Objects.equals(document, documentList.getLast())){
                data.append(document).append("]");
            }
            else{
                data.append(document).append(";");
            }
        }
        return data.toString();
    }

//    Override toString method with proper formatting
    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s %-20s %-60s %-20s %-20s %-20s %-20s %s", claimId, claimDate, insuredPerson.getFullName(), examDate, documentList, claimAmount, status, bankName, bankNumber, receiverName);
    }
}

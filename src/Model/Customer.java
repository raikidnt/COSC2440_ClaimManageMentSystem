package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Customer {
    private String customerId;
    private String fullName;
    private InsuranceCard insuranceCard;
    private List<Claim> claimList;

    public Customer(String customerId, String fullName, InsuranceCard insuranceCard) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.claimList = new ArrayList<>();
    }
    public Customer(String customerId, String fullName, List<Claim> claimList, InsuranceCard insuranceCard) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.claimList = claimList;
        this.insuranceCard = insuranceCard;
        this.claimList = new ArrayList<>();
    }
    public Customer(String customerId, String fullName) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.claimList = new ArrayList<>();
        this.insuranceCard = null;
    }


    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    public List<Claim> getClaimList() {
        return claimList;
    }

    public void addClaim(Claim claim) {
        for (Claim c : claimList) {
            if (c.getClaimId().equals(claim.getClaimId())) {
                return;
            }
        }
        this.claimList.add(claim);
    }

    public void removeClaim(Claim claim) {
        if (claimList.isEmpty()) {
            return;
        }
        if (claimList.contains(claim)) {
            claimList.remove(claim);
        }
        else {
            System.out.println("The claim does not exist in your account");
        }
    }

    public String claimListData() {
        if (this.claimList.isEmpty()) {
            return "[]";
        }
        StringBuilder data = new StringBuilder("[");
        for (Claim c : claimList) {
            if (c.equals(claimList.getLast())) {
                data.append(c.getClaimId()).append("]");
            }
            else {
                data.append(c.getClaimId()).append(";");
            }
        }
        return data.toString();
    }

    @Override
    public String toString() {
        String[] claimids = new String[claimList.size()];
        for (int i = 0; i < claimList.size(); i++) {
            claimids[i] = claimList.get(i).getClaimId();
        }
        return String.format("%-20s%-20s%-20s%s",customerId, fullName, insuranceCard.getCardNumber(), Arrays.toString(claimids));
    }
}

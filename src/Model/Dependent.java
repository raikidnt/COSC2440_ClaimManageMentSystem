package Model;

import Model.Customer;

import java.util.List;

public class Dependent extends Customer {

    public Dependent(String customerId, String fullName, InsuranceCard insuranceCard) {
        super(customerId, fullName, insuranceCard);
    }

    public Dependent(String customerId, String fullName, List<Claim> claimList, InsuranceCard insuranceCard) {
        super(customerId, fullName, claimList, insuranceCard);
    }

    public Dependent(String customerId, String fullName) {
        super(customerId, fullName);
    }
}

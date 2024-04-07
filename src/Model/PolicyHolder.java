package Model;

import Model.Customer;
import Model.Dependent;

import java.util.ArrayList;
import java.util.List;

public class PolicyHolder extends Customer {
    private List<Dependent> dependents;

    public PolicyHolder(String customerId, String fullName, InsuranceCard insuranceCard) {
        super(customerId, fullName, insuranceCard);
        this.dependents = new ArrayList<>();
    }

    public PolicyHolder(String customerId, String fullName, List<Claim> claimList, InsuranceCard insuranceCard) {
        super(customerId, fullName, claimList, insuranceCard);
        this.dependents = new ArrayList<>();
    }

    public PolicyHolder(String customerId, String fullName) {
        super(customerId, fullName);
        this.dependents = new ArrayList<>();
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void addDependent(Dependent dependent) {
        if (dependents.contains(dependent)) {
            System.out.printf("Model.Dependent already exists \n");
            return;
        }
        dependents.add(dependent);
    }

    public String dependentIdData(){
        if (dependents.isEmpty()){
            return "[]";
        }
        StringBuilder data = new StringBuilder("[");
        for (Dependent dependent : dependents){
            if(dependent == dependents.getLast()){
                data.append(dependent.getCustomerId()).append("]");
            }
            else{
                data.append(dependent.getCustomerId()).append(";");
            }
        }
        return data.toString();
    }
}

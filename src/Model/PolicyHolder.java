/**
 * @author DoNhatThanh-s3977947
 */
package Model;

import java.util.ArrayList;
import java.util.List;

public class PolicyHolder extends Customer {
    private final List<Dependent> dependents;

//Constructor
    public PolicyHolder(String customerId, String fullName) {
        super(customerId, fullName);
        this.dependents = new ArrayList<>();
    }

//    Dependents method
    public void addDependent(Dependent dependent) {
        if (dependents.contains(dependent)) {
            System.out.print("Model.Dependent already exists \n");
            return;
        }
        dependents.add(dependent);
    }

//    Method for writing dependent list to data file
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

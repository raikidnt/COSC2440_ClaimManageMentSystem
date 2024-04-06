package Service;


import Model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClaimSystem implements ClaimProcessManager {
    private List<Dependent> dependents;
    private List<PolicyHolder> policyHolders;
    private List<InsuranceCard> insuranceCards;
    private List<Claim> claims;
    public ClaimSystem() {
        this.dependents = new ArrayList<>();
        this.policyHolders = new ArrayList<>();
        this.insuranceCards = new ArrayList<>();
        this.claims = new ArrayList<>();
    }

    @Override
    public void add(Claim claim) {
        claims.add(claim);
    }


    @Override
    public void update(Claim claim) {
        for (Claim smt : claims) {
            if (claim.getClaimId().equals(smt.getClaimId())) {
                smt.setClaimDate(claim.getClaimDate());
                smt.setStatus(claim.getStatus());
                smt.setInsuredPerson(claim.getInsuredPerson());
                smt.setExamDate(claim.getExamDate());
                smt.setDoccumentList(claim.getDoccumentList());
                smt.setClaimAmount(claim.getClaimAmount());
                smt.setBankName(claim.getBankName());
                smt.setBankNumber(claim.getBankNumber());
                smt.setInsuredPerson(claim.getInsuredPerson());
            } else {
                System.out.println("The claim doesnt exist in the database");
            }
        }
    }

    @Override
    public void delete(Claim claim) {
        for (Claim smt : claims) {
            if (smt.getClaimId().equals(claim.getClaimId())) {
                claims.remove(smt);
            } else {
                System.out.println("The claim does not exist in the data");
            }
        }
    }

    @Override
    public Claim getOne(String claimId) {
        for (Claim smt : claims) {
            if (smt.getClaimId().equals(claimId)) {
                return smt;
            }
        }
        return null;
    }

    @Override
    public List<Claim> getAll() {
        if (claims.isEmpty()) {
            return null;
        } else {
            return claims;
        }
    }



    public void run() {
        loadData();
        System.out.println("Welcome to the Insurance Model.Claim System");
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. View claims");
            System.out.println("2. Add a new Model.Claim");
            System.out.println("3. Process a Model.Claim");
            System.out.println("4. Delete a Model.Claim");
            System.out.println("5. Exit");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    printClaims();
                    break;
                case 2:
                    addClaim();
                    break;
                case 3:
                    processClaim();
                    break;
                case 4:
                    deleteClaim();
                    break;
                case 5:
                    writeData();
                    return;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
    public void loadData(){
//        Initialize objects with no Class attributes
        String dependentFilePath = "src/Data/Dependent.csv";
        String policyHolderFilePath = "src/Data/PolicyHolder.csv";
        String insuranceCardFilePath = "src/Data/InsuranceCard.csv";
        String claimFilePath = "src/Data/Claim.csv";
        String splitCsvBy= ",";
        List<String[]> dependentClaimids = new ArrayList<>();
        List<String> dependentCardnumbers = new ArrayList<>();
        List<String[]> policyHolderClaimids = new ArrayList<>();
        List<String> policyHolderCardnumbers = new ArrayList<>();
        List<String[]> policyHolderDependentIds = new ArrayList<>();
        try{
            BufferedReader dependentReader = new BufferedReader(new FileReader(dependentFilePath));
            String line = "";
//            Start with Depnedent data file
            while ((line = dependentReader.readLine()) != null) {
                String[] splitArray = line.split(splitCsvBy);
                Dependent newdependent = new Dependent(splitArray[0], splitArray[1]);
                dependents.add(newdependent);
                String[] arrayString = parseArray(splitArray[2]);
                dependentClaimids.add(arrayString);
                dependentCardnumbers.add(splitArray[3]);
            }
            dependentReader.close();
            //Start with PolicyHolder data file
            BufferedReader policyHolderReader = new BufferedReader(new FileReader(policyHolderFilePath));
            while ((line = policyHolderReader.readLine()) != null) {
                String[] splitArray = line.split(splitCsvBy);
                PolicyHolder newPolicyHolder = new PolicyHolder(splitArray[0], splitArray[1]);
                policyHolders.add(newPolicyHolder);
                String[] arrayString = parseArray(splitArray[2]);
                policyHolderClaimids.add(arrayString);
                policyHolderCardnumbers.add(splitArray[3]);
                String[] dependentIds = parseArray(splitArray[4]);
                policyHolderDependentIds.add(dependentIds);
            }
            policyHolderReader.close();
            //Start with InsuranceCard data file
            BufferedReader insuranceCardReader = new BufferedReader(new FileReader(insuranceCardFilePath));
            while ((line = insuranceCardReader.readLine()) != null) {
                String[] splitArray = line.split(splitCsvBy);
                InsuranceCard newInsuranceCard = new InsuranceCard(splitArray[0], splitArray[1], splitArray[2]);
                insuranceCards.add(newInsuranceCard);
            }
            insuranceCardReader.close();
            //Start with Claim data file
            BufferedReader claimReader = new BufferedReader(new FileReader(claimFilePath));
            while ((line = claimReader.readLine()) != null) {
                String[] splitArray = line.split(splitCsvBy);
                ArrayList<String> documentList = parseArraytoArrList(splitArray[3]);
                Float arr4 = ParseFloat(splitArray[4]);
                Claim newClaim = new Claim(splitArray[0], splitArray[1], splitArray[2], documentList, arr4, splitArray[5], splitArray[6], splitArray[7],splitArray[8]);
                claims.add(newClaim);
            }
            claimReader.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Error reading the file");
            e.printStackTrace();
        }
        for (int i = 0; i < dependentClaimids.size(); i++){
            for(int el = 0 ; el < claims.size(); el++){
                //Access the Element in the dependentClaimids array to get the claim ids
                for (int j = 0; j < dependentClaimids.get(i).length; j++){
                    if(dependentClaimids.get(i)[j].equals(claims.get(el).getClaimId())){
                        dependents.get(i).addClaim(claims.get(el));
                        claims.get(el).setInsuredPerson(dependents.get(i));
                    }
                }
            }
        }
        for (int i = 0; i < policyHolderClaimids.size(); i++){
            for(int el = 0 ; el < claims.size(); el++){
                //Access the Element in the dependentClaimids array to get the claim ids
                for (int j = 0; j < policyHolderClaimids.get(i).length; j++){
                    if(policyHolderClaimids.get(i)[j].equals(claims.get(el).getClaimId())){
                        policyHolders.get(i).addClaim(claims.get(el));
                        claims.get(el).setInsuredPerson(policyHolders.get(i));
                    }
                }
            }
        }
        for (int i =0; i< dependentCardnumbers.size(); i++){
            for (int el = 0; el < insuranceCards.size(); el++){
                if(dependentCardnumbers.get(i).equals(insuranceCards.get(el).getCardNumber())){
                    dependents.get(i).setInsuranceCard(insuranceCards.get(el));
                    insuranceCards.get(el).setCardHolder(dependents.get(i));
                }
            }
        }
        for (int i =0; i< policyHolderCardnumbers.size(); i++){
            for (int el = 0; el < insuranceCards.size(); el++){
                if(policyHolderCardnumbers.get(i).equals(insuranceCards.get(el).getCardNumber())){
                    policyHolders.get(i).setInsuranceCard(insuranceCards.get(el));
                    insuranceCards.get(el).setCardHolder(policyHolders.get(i));
                }
            }
        }

        for (int i = 0; i < policyHolderDependentIds.size(); i++){
            for (int el = 0; el < dependents.size(); el++){
                for (int j = 0; j < policyHolderDependentIds.get(i).length; j++){
                    if(policyHolderDependentIds.get(i)[j].equals(dependents.get(el).getCustomerId())){
                        policyHolders.get(i).addDependent(dependents.get(el));
                    }
                }
            }
        }
    }

    public void printClaims() {
        for (Claim claim : claims) {
            System.out.println(claim);
        }
    }
//    Parse array of strings data into List of strings

    public void addClaim(){
//        Get the last number of the claimId of the last claim in claims, as the claim id will be like f-0000000001
        String lastClaimId = claims.get(claims.size()-1).getClaimId();
        String[] splitClaimId = lastClaimId.split("-");
        int newClaimId = Integer.parseInt(splitClaimId[1]) + 1;
        String newClaimIdString = "f-" + String.format("%010d", newClaimId);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the claim date in the format yyyy-mm-dd");
        for (PolicyHolder policyHolder : policyHolders) {
            System.out.println(policyHolder);
        }
        for (Dependent dependent : dependents) {
            System.out.println(dependent);
        }
        System.out.println("Enter the id of the insuring person");
        String insuringPersonId = scanner.next();
        Customer insuringPerson = null;
        while (insuringPerson == null){
            for (PolicyHolder policyHolder : policyHolders) {
                if (policyHolder.getCustomerId().equals(insuringPersonId)){
                    insuringPerson = policyHolder;
                    break;
                }
            }
            for (Dependent dependent : dependents) {
                if (dependent.getCustomerId().equals(insuringPersonId)){
                    insuringPerson = dependent;
                    break;
                }
            }
            if (insuringPerson == null){
                System.out.println("Person not found, please enter the correct id");
                insuringPersonId = scanner.next();
            }
        }
        LocalDate claimDate = LocalDate.now();
        float claimAmount = scanner.nextFloat();
        System.out.println("Enter the status of the claim");
        String status = scanner.next();
        System.out.println("Enter the receiver name");
        String receiverName = scanner.next();
        System.out.println("Enter the bank name");
        String bankName = scanner.next();
        System.out.println("Enter the bank number");
        String bankNumber = scanner.next();
        System.out.println("Enter the document list separated by commas");
        String documentList = scanner.next();
        ArrayList<String> documentListArray = parseArraytoArrList(documentList);
        Claim newClaim = new Claim(newClaimIdString, claimDate,insuringPerson, documentListArray, claimAmount, status, bankName, bankNumber, receiverName);
        System.out.println("New claim added" + newClaim);
        claims.add(newClaim);
    }
    public void processClaim(){
        for (Claim claim : claims) {
            if (claim.getStatus().equals("New")||claim.getStatus().equals("Processing")) {
                System.out.println(claim);
            }
        }
        System.out.println("Enter the claim id of the claim you want to process");
        Scanner scanner = new Scanner(System.in);
        String claimId = scanner.next();
        Claim claim = getOne(claimId);
        if (claim == null){
            System.out.println("Claim not found");
            return;
        } else if (claim.getStatus().equals("Done")){
            System.out.println("Claim already Done");
            return;
        }
        System.out.println("Changing the status of the claim");
        String status;
        if (claim.getStatus().equals("New")){
            status = "Processing";
        } else {
            status = "Done";
        }
        claim.setStatus(status);
        System.out.println("Claim processed: " + claim);
    }

    public void deleteClaim(){
        printClaims();
        System.out.println("Enter the claim id of the claim you want to delete");
        Scanner scanner = new Scanner(System.in);
        String claimId = scanner.next();
        Claim claim = getOne(claimId);
        if (claim == null){
            System.out.println("Claim not found");
            return;
        }
        delete(claim);
    }

    private static ArrayList<String> parseArraytoArrList(String arrayString){
        ArrayList<String> parts = new ArrayList<>();
        // Check if the input string represents an empty array
        if (arrayString.trim().equals("[]")) {
            return parts; // Return an empty String[] if input is "[]"
        }

        // Remove the brackets
        arrayString = arrayString.substring(1, arrayString.length()-1);
        String[] partsArray = arrayString.split(",");
        for (int i = 0; i < partsArray.length; i++) {
            parts.add(partsArray[i].trim().replaceAll("^\"|\"$", ""));
        }
        return parts;
    }

    private static float ParseFloat(String floatString){
        return Float.parseFloat(floatString);
    }

    private static String[] parseArray(String arrayString){
        // Check if the input string represents an empty array
        if (arrayString.trim().equals("[]")) {
            return new String[0]; // Return an empty String[] if input is "[]"
        }

        // Remove the brackets
        arrayString = arrayString.substring(1, arrayString.length()-1);
        String[] parts = arrayString.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim().replaceAll("^\"|\"$", "");
        }
        return parts;
    }

}




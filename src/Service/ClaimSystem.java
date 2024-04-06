package Service;


import Model.Claim;
import Model.Dependent;
import Model.InsuranceCard;
import Model.PolicyHolder;
import java.io.*;
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
        ClaimSystem.loadData();
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
    public void loadData() throws IOException {
//        Initialize objects with no Class attributes
        String dependentFilePath = "src/Data/Dependent.csv";
        String policyHolderFilePath = "src/Data/PolicyHolder.csv";
        String insuranceCardFilePath = "src/Data/InsuranceCard.csv";
        String claimFilePath = "src/Data/Claim.csv";
        String splitCsvBy= ",";
        List<String> dependentClaimids = new ArrayList<>();
        List<String> dependent
        List<String> policyHolderClaimids = new ArrayList<>();
        try{
            BufferedReader dependentReader = new BufferedReader(new FileReader(dependentFilePath));
            String line = "";
            while ((line = dependentReader.readLine()) != null) {
                String[] splitArray = line.split(splitCsvBy);
                Dependent dependent = new Dependent(splitArray[0], splitArray[1]);
                dependent


            }
            dependentReader.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Error reading the file");
            e.printStackTrace();
        }
    }
    public void printClaims() {
        for (Claim claim : claims) {
            System.out.println(claim);
        }
    }
}




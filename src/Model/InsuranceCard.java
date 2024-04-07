/**
 * @author DoNhatThanh-s3977947
 */
package Model;

import java.time.LocalDate;

public class InsuranceCard {
    private final String cardNumber;
    private Customer cardHolder;
    private final String policyOwner;
    private final LocalDate expiryDate;

//    Constructor
    public InsuranceCard(String cardNumber, String policyOwner, String expiryDate) {
        this.cardNumber = cardNumber;
        this.policyOwner = policyOwner;
        this.expiryDate = LocalDate.parse(expiryDate);
    }

//    getters and setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardHolder(Customer cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }


}

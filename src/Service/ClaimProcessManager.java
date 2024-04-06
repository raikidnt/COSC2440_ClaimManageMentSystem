package Service;

import Model.Claim;

import java.util.List;

public interface ClaimProcessManager {
    void add(Claim claim);
    void update(Claim claim);
    void delete(Claim claim);
    Claim getOne(String claimId);
    List<Claim> getAll();
}


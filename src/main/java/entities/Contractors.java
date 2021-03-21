package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author claes
 */
public class Contractors {
    
    List<Contractor> contractors = new ArrayList();

    public Contractors() {
    }
    
    public Contractors(List<Contractor> cons) {
        cons.forEach((c) -> {
            contractors.add(c);
        });
    }

    public List<Contractor> getContractors() {
        return contractors;
    }

    public void setAll(List<Contractor> contractors) {
        this.contractors = contractors;
    }
    
    
    
}

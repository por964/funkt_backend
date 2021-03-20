
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author claes
 */
public class Contractors {
    
    List<Contractor> posts = new ArrayList();

    public Contractors() {
    }
    
    public Contractors(List<Contractor> cons) {
        cons.forEach((e) -> {
            posts.add(e);
        });
    }

    public List<Contractor> getPosts() {
        return posts;
    }

    public void setAll(List<Contractor> posts) {
        this.posts = posts;
    }
    
    
    
}

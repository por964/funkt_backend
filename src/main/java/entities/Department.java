package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author claes
 */
@Entity
@NamedQuery(name = "Department.getAllRows", query = "SELECT d from Department d")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int code;

    private String name;
    private String description;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    List<Employee> teams = new ArrayList<>();

    public Department() {
    }

    public Department(int code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public void addEmployee(Employee emp) {
        this.teams.add(emp);
        if (emp != null) {
            emp.setDepartment(this);
        }
    }

    public List<Employee> getTeams() {
        return teams;
    }

    public void setTeams(List<Employee> teams) {
        this.teams = teams;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

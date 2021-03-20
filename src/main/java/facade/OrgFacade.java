package facade;

import entities.Contractor;
import entities.Contractors;
import entities.Department;
import entities.Departments;
import entities.Employee;
import entities.Employees;
import entities.Project;
import entities.Projects;
import errorhandling.AlreadyExistsException;
import errorhandling.MissingInputException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author claes
 */
public class OrgFacade {

    private static EntityManagerFactory emf;
    private static OrgFacade instance;

    public OrgFacade() {

    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static OrgFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OrgFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee newEmployee(Employee empl) throws MissingInputException, AlreadyExistsException {
        EntityManager em = getEntityManager();

        if (empl.getFirstName().length() == 0 || empl.getLastName().length() == 0 || empl.getEmail().length() == 0) {
            throw new MissingInputException("One or more values are missing");
        }
        if (em.find(Employee.class, empl.getEmail()) != null) {
            throw new AlreadyExistsException("Employee already exists");
        } else {
            try {
                em.getTransaction().begin();
                em.persist(empl);
                em.getTransaction().commit();
                return empl;
            } finally {
                em.close();
            }
        }
    }

    public Department newDepartment(Department dept) throws MissingInputException, AlreadyExistsException {
        EntityManager em = getEntityManager();

        if (dept.getCode() == 0 || dept.getName().length() == 0 || dept.getDescription().length() == 0) {
            throw new MissingInputException("One or more values are missing");
        }
        if (em.find(Department.class, dept.getDescription()) != null) {
            throw new AlreadyExistsException("Department already exists");
        } else {
            try {
                em.getTransaction().begin();
                em.persist(dept);
                em.getTransaction().commit();
                return dept;
            } finally {
                em.close();
            }
        }
    }

    public Project newProject(Project proj) throws MissingInputException, AlreadyExistsException {
        EntityManager em = getEntityManager();

        if (proj.getId() == 0 || proj.getTitle().length() == 0 || proj.getDuration() == 0) {
            throw new MissingInputException("One or more values are missing");
        }
        if (em.find(Project.class, proj.getTitle()) != null) {
            throw new AlreadyExistsException("Project already exists");
        } else {
            try {
                em.getTransaction().begin();
                em.persist(proj);
                em.getTransaction().commit();
                return proj;
            } finally {
                em.close();
            }
        }
    }

    public Employees getAllEmps() {
        EntityManager em = getEntityManager();
        try {
            return new Employees(em.createNamedQuery("Employee.getAllRows").getResultList());
        } finally {
            em.close();
        }
    }

    public String addEmpl(String fName, String lName, String mail) {

        EntityManager em = getEntityManager();

        Employee emp = new Employee(fName, lName, mail);

        try {
            em.getTransaction().begin();
            em.persist(emp);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return emp.getEmail();
    }
    
    public String addContractor(String fName, String lName, String zip, String mail) {

        EntityManager em = getEntityManager();

        Contractor con = new Contractor(fName, lName, zip, mail);

        try {
            em.getTransaction().begin();
            em.persist(con);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return con.getMail();
    }
    
    public Contractors getAllContractors() {
        EntityManager em = getEntityManager();
        try {
            return new Contractors(em.createNamedQuery("Contractor.getAllRows").getResultList());
        } finally {
            em.close();
        }
    }
    
    

    public Projects getAllProjects() {
        EntityManager em = getEntityManager();
        try {
            return new Projects(em.createNamedQuery("Project.getAllRows").getResultList());
        } finally {
            em.close();
        }
    }

    public String addProject(String title, int duration) {

        EntityManager em = getEntityManager();

        Project proj = new Project(title, duration);

        try {
            em.getTransaction().begin();
            em.persist(proj);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return proj.getTitle();
    }

    public Departments getAllDepartments() {
        EntityManager em = getEntityManager();
        try {
            return new Departments(em.createNamedQuery("Department.getAllRows").getResultList());
        } finally {
            em.close();
        }
    }

    public String addDepartment(int code, String name, String descr) {

        EntityManager em = getEntityManager();

        Department dp = new Department(code, name, descr);

        try {
            em.getTransaction().begin();
            em.persist(dp);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return dp.getDescription();
    }

    public List<Employee> allEmployees() {

        EntityManager em = getEntityManager();

        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e.id,e.firstName,e.lastName,e.email FROM Employee e", Employee.class);
            List<Employee> allempl = query.getResultList();
            return allempl;
        } finally {
            em.close();
        }

    }
    
    public Employees getAllEmps2() {
        EntityManager em = getEntityManager();
        try {
            return new Employees(em.createNamedQuery("Employee.getAll").getResultList());
        } finally {
            em.close();
        }
    }

}

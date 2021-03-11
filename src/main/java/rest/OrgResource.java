package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Department;
import entities.Departments;
import entities.Employee;
import entities.Employees;
import entities.Project;
import entities.Projects;
import errorhandling.MissingInputException;
import facade.OrgFacade;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;

/**
 *
 * @author claes
 */

@Path("org")
public class OrgResource {
    
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final ExecutorService es = Executors.newCachedThreadPool();
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final OrgFacade EMPFACADE = OrgFacade.getEmployeeFacade(EMF);
    
    @Context
    UriInfo uriInfo;
    
    @GET
    @Path("empl")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployees() throws IOException, InterruptedException, ExecutionException, TimeoutException {
        Employees result = EMPFACADE.getAllEmps();
        return gson.toJson(result);

    }
    
    @POST
    @Path("empl")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addEmpl(String emp) throws MissingInputException {
        Employee em = gson.fromJson(emp, Employee.class);
        String result = EMPFACADE.addEmpl(em.getFirstName(), em.getLastName(), em.getEmail());
        return gson.toJson(result);
    }
    
    @GET
    @Path("proj")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProjects() throws IOException, InterruptedException, ExecutionException, TimeoutException {
        Projects result = EMPFACADE.getAllProjects();
        return gson.toJson(result);

    }
    
    @POST
    @Path("proj")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addProject(String pr) throws MissingInputException {
        Project proj = gson.fromJson(pr, Project.class);
        String result = EMPFACADE.addProject(proj.getTitle(), proj.getDuration());
        return gson.toJson(result);
    }
    
    @GET
    @Path("dept")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDepartments() throws IOException, InterruptedException, ExecutionException, TimeoutException {
        Departments result = EMPFACADE.getAllDepartments();
        return gson.toJson(result);

    }
    
    @POST
    @Path("dept")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addDepartment(String dp) throws MissingInputException {
        Department dep = gson.fromJson(dp, Department.class);
        String result = EMPFACADE.addDepartment(dep.getCode(), dep.getName(), dep.getDescription());
        return gson.toJson(result);
    }

    
}

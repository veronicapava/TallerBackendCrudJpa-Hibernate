package com.example.tallerdosspring;

import com.example.tallerdosspring.model.Employee;
import com.example.tallerdosspring.model.Project;
import com.example.tallerdosspring.model.Role;
import com.example.tallerdosspring.repository.IEmployeeJpaRepository;
import com.example.tallerdosspring.repository.IProjectJpaRepository;
import com.example.tallerdosspring.repository.IRoleJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class NewEmployeeJpaRepositoryTest {
    @Autowired
    private IEmployeeJpaRepository repoEmpl;

    @Autowired
    private IRoleJpaRepository repoRole;

    @Autowired
    private IProjectJpaRepository repoProj;

    @Test
    public void saveEmployee(){

        Role admin = new Role("ROLE_ADMIN");
        Role dev = new Role("ROLE_DEV");

        admin = repoRole.save(admin);
        dev = repoRole.save(dev);

        Project proj1 = new Project("proj1");
        Project proj2 = new Project("proj2");
        Project proj3 = new Project("proj3");

        List<Project> project = new ArrayList<Project>();
        project.set(0, proj1);
        project.set(1, proj2);
        project.set(2, proj3);

        proj1 = repoProj.save(proj1);
        proj2 = repoProj.save(proj2);
        proj3 = repoProj.save(proj3);


        Employee john = new Employee("John", "Smith", "empl123", dev, project);
        Employee claire = new Employee("Claire", "Simpson", "empl124",admin, project);

        john.getProjects().add(proj1);
        john.getProjects().add(proj2);

        claire.getProjects().add(proj1);
        claire.getProjects().add(proj2);
        claire.getProjects().add(proj3);

        repoEmpl.save(john);
        repoEmpl.save(claire);

        //Este metodo inserta lo que hay en el repositorio en la db
        repoEmpl.flush();

        Employee empl124 = repoEmpl.findByEmployeeId("empl124");
        Assertions.assertEquals("Claire", empl124.getFirstName());
        Assertions.assertEquals(2, repoEmpl.findAll().size());
        Assertions.assertEquals(admin,empl124.getRole());
    }
}

package com.example.tallerdosspring;


import com.example.tallerdosspring.model.Employee;
import com.example.tallerdosspring.repository.IEmployeeJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NewEmployeeJpaRepositoryTest {
    @Autowired
    private IEmployeeJpaRepository repoEmpl;

    @Test
    public void saveEmployee(){
        Employee john = new Employee("John", "Smith", "empl123");
        Employee claire = new Employee("Claire", "Simpson", "empl124");

        repoEmpl.save(john);
        repoEmpl.save(claire);

        //para que los inserte
        repoEmpl.flush();

        assertEquals(2, repoEmpl.findAll().size());


    }

}

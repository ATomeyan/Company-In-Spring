package com.company.repository;

import com.company.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("from Employee where active=true")
    @Override
    List<Employee> findAll();

    @Query("from Employee where firstName=:#{#employee.firstName} and lastName=:#{#employee.lastName} and " +
            "dateOfBirth=:#{#employee.dateOfBirth} and email=:#{#employee.email} and gender=:#{#employee.gender} and " +
            "active=true")
    Optional<List<Employee>> findByCriteria(Employee employee);

    @Query("from Employee where id=:id and active=true")
    @Override
    Optional<Employee> findById(@Param("id") Integer id);

    @Query("update Employee set active=false where id=:id")
    @Modifying
    @Override
    void deleteById(@Param("id") Integer id);
}
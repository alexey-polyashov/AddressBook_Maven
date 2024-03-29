package com.example.adrbook.repo;

import com.example.adrbook.entity.Department;
import com.example.adrbook.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    List<Department> findAll();

    Optional<Department> findDepartmentById(Long id);

    List<Department> findDepartmentByName(String name);

    List<Department> findDepartmentByCode(String code);

    @Query("Select p From PersonEntity p Where p.department in (" +
            "Select d From Department d Where d.id in :departmentIDs" +
            ")")
    List<PersonEntity> getEmployees(List<Long> departmentIDs);

    @Query("Select d From Department d Left JOIN FETCH d.employees where d.id=:departmentId ")
    Optional<Department> getDepartmentsAndEmployees(Long departmentId);

    @Query("Select d From Department d Left JOIN FETCH d.employees AS empl " +
            " order by d.name, empl.position, empl.fullName")
    Set<Department> getDepartmentsAndEmployees();

    @Query("Select d From Department d " +
            " Left JOIN FETCH d.employees AS empl " +
            " Where lower(empl.fullName) like %:searchtext%" +
            "   or lower(empl.position) like %:searchtext%" +
            "   or empl.phoneNumber like %:searchtext%" +
            "   or empl.cellPhone like %:searchtext%" +
            "   or lower(d.name) like %:searchtext%" +
            " order by d.name, empl.position, empl.fullName")
    Set<Department> getDepartmentsAndEmployees(@Param("searchtext") String searchtext);


    @Query("Select d From Department d Where d.head.id = :headId")
    List<Department> findByHeadId(Long headId);

    @Query("Select d From Department d " +
            " Left JOIN FETCH d.employees AS empl " +
            " Where lower(d.name) like %:searchtext%" +
            " order by d.name, empl.position, empl.fullName")
    Set<Department> getDepartmentsByNameAndEmployees(String searchtext);
}

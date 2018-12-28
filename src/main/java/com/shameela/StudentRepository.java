package com.shameela;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/*
 * @class StudentRepository to perform all CRUD opeartions
 */
@Repository
@Transactional
public interface StudentRepository extends JpaRepository<StudentRegistration, Long>{

}

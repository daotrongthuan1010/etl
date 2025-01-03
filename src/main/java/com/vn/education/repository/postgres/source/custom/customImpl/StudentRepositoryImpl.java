package com.vn.education.repository.postgres.source.custom.customImpl;

import com.vn.education.entity.postgres.source.Student;
import com.vn.education.repository.postgres.source.custom.StudentRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findStudentsByCustomCriteria(String criteria) {

        String jpql = "SELECT s FROM Student s WHERE s.createdAt = :criteria";
        TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
        query.setParameter("criteria", criteria);
        return query.getResultList();
    }
}


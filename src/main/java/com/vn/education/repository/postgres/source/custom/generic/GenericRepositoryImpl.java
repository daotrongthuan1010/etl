//package com.vn.education.repository.postgres.source.custom.generic;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class GenericRepositoryImpl<T, ID extends Serializable> implements GenericRepository<T, ID> {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private Class<T> domainClass;
//
//    @Autowired
//    public GenericRepositoryImpl(Class<T> domainClass) {
//        this.domainClass = domainClass;
//    }
//
//    @Override
//    public T save(T entity) {
//        entityManager.persist(entity);
//        return entity;
//    }
//
//    @Override
//    public Optional<T> findById(ID id) {
//        T entity = entityManager.find(domainClass, id);
//        return Optional.ofNullable(entity);
//    }
//
//    @Override
//    public List<T> findAll() {
//        String jpql = "SELECT e FROM " + domainClass.getName() + " e";
//        return entityManager.createQuery(jpql, domainClass).getResultList();
//    }
//
//    @Override
//    public void delete(T entity) {
//        entityManager.remove(entity);
//    }
//
//}

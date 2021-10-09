package com.example.data.repository;

import com.example.data.entity.PaymentEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PaymentRepositoryImpl implements CustomPaymentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PaymentEntity> findOrderedByIdDescLimitedTo(int limit) {
        return entityManager.createQuery("SELECT p FROM PaymentEntity p ORDER BY p.id DESC",
                PaymentEntity.class).setMaxResults(limit).getResultList();
    }
}

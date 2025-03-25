package br.com.billing.userapi.repositories.queries;

import br.com.billing.userapi.model.UserRegisterModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class UserRegisterRepositoryImpl implements UserRegisterRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserRegisterModel findByEmailAndSenha(UserRegisterModel user) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserRegisterModel> criteriaQuery = criteriaBuilder
                .createQuery(UserRegisterModel.class);
        Root<UserRegisterModel> root = criteriaQuery.from(UserRegisterModel.class);

        Predicate[] predicates = createPredicatesOfEqualities(root, user, criteriaBuilder);
        criteriaQuery.select(root).where(predicates);

        TypedQuery<UserRegisterModel> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList().isEmpty() ? new UserRegisterModel()
                : typedQuery.getSingleResult();
    }

    private Predicate[] createPredicatesOfEqualities(Root<UserRegisterModel> root, UserRegisterModel user,
                                                     CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(root.get("email"), user.getEmail()));
        predicates.add(criteriaBuilder.equal(root.get("senha"), user.getSenha()));

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}

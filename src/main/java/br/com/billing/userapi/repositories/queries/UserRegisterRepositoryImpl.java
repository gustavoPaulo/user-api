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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<UserRegisterModel> pesquisarPorEmail(UserRegisterModel filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserRegisterModel> criteriaQuery = criteriaBuilder
                .createQuery(UserRegisterModel.class);
        Root<UserRegisterModel> root = criteriaQuery.from(UserRegisterModel.class);

        Predicate[] predicates = createPredicatesOfEmail(root, filter, criteriaBuilder);
        criteriaQuery.select(root).where(predicates);

        TypedQuery<UserRegisterModel> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList().isEmpty() ? Arrays.asList(new UserRegisterModel())
                : typedQuery.getResultList();
    }

    private Predicate[] createPredicatesOfEqualities(Root<UserRegisterModel> root, UserRegisterModel user,
                                                     CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(root.get("email"), user.getEmail()));
        predicates.add(criteriaBuilder.equal(root.get("senha"), user.getSenha()));

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private Predicate[] createPredicatesOfEmail(Root<UserRegisterModel> root, UserRegisterModel filter,
                                                     CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(filter.getEmail())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("email")), "%" + filter.getEmail().toLowerCase() + "%"));
        }

        if (Objects.nonNull(filter.getUserType()) && !filter.getUserType().equals("TODOS")) {
            predicates.add(criteriaBuilder.equal(root.get("userType"), filter.getUserType()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}

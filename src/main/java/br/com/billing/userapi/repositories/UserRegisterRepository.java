package br.com.billing.userapi.repositories;

import br.com.billing.userapi.model.UserRegisterModel;
import br.com.billing.userapi.repositories.queries.UserRegisterRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegisterRepository extends JpaRepository<UserRegisterModel, Integer>, UserRegisterRepositoryQuery {

    UserRegisterModel findByEmailAndSenha(UserRegisterModel user);
}

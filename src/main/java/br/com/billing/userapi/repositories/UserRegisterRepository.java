package br.com.billing.userapi.repositories;

import br.com.billing.userapi.model.UserRegisterModel;
import br.com.billing.userapi.repositories.queries.UserRegisterRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRegisterRepository extends JpaRepository<UserRegisterModel, Integer>, UserRegisterRepositoryQuery {

    UserRegisterModel findByEmail(String email);

    List<UserRegisterModel> pesquisarPorEmail(UserRegisterModel filter);
}

package br.com.billing.userapi.repositories.queries;

import br.com.billing.userapi.model.UserRegisterModel;

import java.util.List;

public interface UserRegisterRepositoryQuery {

    UserRegisterModel findByEmailAndSenha(UserRegisterModel user);

    List<UserRegisterModel> pesquisarPorEmail(UserRegisterModel filter);
}

package br.com.billing.userapi.repositories.queries;

import br.com.billing.userapi.model.UserRegisterModel;

public interface UserRegisterRepositoryQuery {

    UserRegisterModel findByEmailAndSenha(UserRegisterModel user);
}

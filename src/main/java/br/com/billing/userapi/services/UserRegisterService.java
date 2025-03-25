package br.com.billing.userapi.services;

import br.com.billing.userapi.model.UserRegisterModel;
import br.com.billing.userapi.repositories.UserRegisterRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserRegisterService {

    @Autowired
    private UserRegisterRepository userRegisterRepository;

    public UserRegisterModel login(UserRegisterModel user) {
        UserRegisterModel userRegisterRecovered =
                userRegisterRepository.findByEmailAndSenha(user);
        return Objects.isNull(userRegisterRecovered.getCode()) ? new UserRegisterModel()
                : userRegisterRecovered;
    }

    public UserRegisterModel novo(@Valid UserRegisterModel user) {
        return userRegisterRepository.save(user);
    }

    public List<UserRegisterModel> pesquisar() {
        return userRegisterRepository.findAll();
    }
}

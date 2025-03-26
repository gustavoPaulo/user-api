package br.com.billing.userapi.services;

import br.com.billing.userapi.model.UserRegisterModel;
import br.com.billing.userapi.model.enums.UserType;
import br.com.billing.userapi.repositories.UserRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UserRegisterService {

    @Autowired
    private UserRegisterRepository userRegisterRepository;

    public UserRegisterModel login(UserRegisterModel user) {
        UserRegisterModel userRegisterRecovered =
                userRegisterRepository.findByEmail(user.getEmail());

        if (Objects.isNull(userRegisterRecovered)) {
            return new UserRegisterModel(null, null, null, null);
        }

        if (!userRegisterRecovered.getSenha().equals(user.getSenha())) {
            return new UserRegisterModel(0, null, null, null);
        }

        return userRegisterRecovered;
    }

    public UserRegisterModel novo(UserRegisterModel user) {
        if (userAlreadyExist(user)) {
            return new UserRegisterModel();
        }

        if (null == user.getUserType()) {
            user.setUserType(UserType.DEFAULT);
        }

        user.setDate_creation(LocalDateTime.now());
        return userRegisterRepository.save(user);
    }

    public List<UserRegisterModel> pesquisar() {
        return userRegisterRepository.findAll();
    }

    private boolean userAlreadyExist(UserRegisterModel user) {
        return null != userRegisterRepository.findByEmail(user.getEmail());
    }
}

package br.com.billing.userapi.services;

import br.com.billing.userapi.model.UserRegisterModel;
import br.com.billing.userapi.model.enums.UserType;
import br.com.billing.userapi.repositories.UserRegisterRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public List<UserRegisterModel> listar() {
        return userRegisterRepository.findAll();
    }

    private boolean userAlreadyExist(UserRegisterModel user) {
        return null != userRegisterRepository.findByEmail(user.getEmail());
    }

    public List<UserRegisterModel> pesquisarPorEmail(UserRegisterModel filter) {
        return userRegisterRepository.pesquisarPorEmail(filter);
    }

    public void delete(Integer code) {
        if (Objects.isNull(verifyIfExists(code).getCode())) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        userRegisterRepository.deleteById(code);
    }

    private UserRegisterModel verifyIfExists(Integer code) {
        Optional<UserRegisterModel> userRecovered = userRegisterRepository.findById(code);
        return userRecovered.isPresent() ? userRecovered.get() : new UserRegisterModel();
    }

    public UserRegisterModel update(UserRegisterModel user) {
        UserRegisterModel userRecovered = verifyIfExists(user.getCode());

        if (userRecovered.getEmail().equals(user.getEmail())) {
            BeanUtils.copyProperties(user, userRecovered, "code");
        } else {
            if (userAlreadyExist(user)) {
                return new UserRegisterModel();
            }
        }

        return this.userRegisterRepository.save(userRecovered);
    }

    public UserRegisterModel findByCode(Integer code) {
        return userRegisterRepository.findById(code).orElseThrow(() ->
                new ObjectNotFoundException("Usuário não encontrado!", UserRegisterModel.class));
    }
}

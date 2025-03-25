package br.com.billing.userapi.model;

import br.com.billing.userapi.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_register")
public class UserRegisterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDateTime date_creation = (null == this.getEmail() ? null : LocalDateTime.now());

    public UserRegisterModel() {}

    public UserRegisterModel(Integer code, String email, String senha, UserType userType) {
        this.code = code;
        this.email = email;
        this.senha = senha;
        this.userType = userType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public LocalDateTime getDate_creation() {
        return date_creation;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        UserRegisterModel that = (UserRegisterModel) object;
        return Objects.equals(code, that.code) && Objects.equals(email, that.email) && Objects.equals(senha, that.senha) && userType == that.userType && Objects.equals(date_creation, that.date_creation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, email, senha, userType, date_creation);
    }
}

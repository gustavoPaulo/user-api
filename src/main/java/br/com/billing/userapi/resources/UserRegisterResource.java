package br.com.billing.userapi.resources;

import br.com.billing.userapi.model.UserRegisterModel;
import br.com.billing.userapi.services.UserRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-register")
public class UserRegisterResource {

    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping("/login")
    public ResponseEntity<UserRegisterModel> login(@RequestBody UserRegisterModel user) {
        UserRegisterModel userRegisterRecovered = userRegisterService.login(user);
        return ResponseEntity.ok(userRegisterRecovered);
    }

    @PostMapping("/novo")
    public ResponseEntity<UserRegisterModel> novo(@RequestBody UserRegisterModel user) {
        UserRegisterModel userRegisterRecovered = userRegisterService.novo(user);
        return ResponseEntity.ok(userRegisterRecovered);
    }

    @GetMapping
    public List<UserRegisterModel> pesquisar() {
        List<UserRegisterModel> users = userRegisterService.pesquisar();
        return ResponseEntity.ok(users).getBody();
    }
}

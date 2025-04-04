package br.com.billing.userapi.resources;

import br.com.billing.userapi.model.UserRegisterModel;
import br.com.billing.userapi.services.UserRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<UserRegisterModel> listar() {
        List<UserRegisterModel> users = userRegisterService.listar();
        return ResponseEntity.ok(users).getBody();
    }

    @GetMapping(value = "/findByEmailAndType", params = "filter")
    public List<UserRegisterModel> pesquisarPorEmail(UserRegisterModel filter) {
        List<UserRegisterModel> users = userRegisterService.pesquisarPorEmail(filter);
        return ResponseEntity.ok(users).getBody();
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer code) {
        userRegisterService.delete(code);
    }

    @PutMapping
    public ResponseEntity<UserRegisterModel> update(@Valid @RequestBody UserRegisterModel user) {
        UserRegisterModel userRecovered = userRegisterService.update(user);
        return ResponseEntity.ok(userRecovered);
    }

    @GetMapping("/{code}")
    public ResponseEntity<UserRegisterModel> findByCode(@PathVariable Integer code) {
        UserRegisterModel usuarioRecovered = userRegisterService.findByCode(code);
        return ResponseEntity.ok(usuarioRecovered);
    }
}

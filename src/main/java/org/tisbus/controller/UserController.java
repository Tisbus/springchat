package org.tisbus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tisbus.entity.UserEntity;
import org.tisbus.exception.UserAlreadyExistException;
import org.tisbus.exception.UserNotFoundException;
import org.tisbus.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("Пользователь успешно сохранен");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка - пользователь не сохранен");
        }
    }

    @GetMapping("/id={id}")
    public ResponseEntity getUserToId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserToId(id));
        }catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка подключения к базе");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity getUserToName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(userService.getUserToName(name));
        }catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка подключения к базе");
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllUser() throws UserNotFoundException {
            return ResponseEntity.ok(userService.getAllUser());
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка удаления пользователя");
        }
    }
    @DeleteMapping("/delete_all")
    public ResponseEntity deleteAllUser(){
            userService.deleteAllUser();
        return ResponseEntity.ok("Все пользователи успешно удалены!");
    }
}

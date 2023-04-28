package org.tisbus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tisbus.entity.UserEntity;
import org.tisbus.exception.UserAlreadyExistException;
import org.tisbus.exception.UserNotFoundException;
import org.tisbus.model.User;
import org.tisbus.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("Ошибка - пользователь с таким именем уже существует!");
        }
        return userRepo.save(user);
    }

    public User getUserToId(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user.getId() == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return User.toModel(user);
    }

    public UserEntity getUserToName(String name) throws UserNotFoundException {
        UserEntity user = userRepo.findByUsername(name);
        if (user.getUsername() == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return user;
    }

    public List<User> getAllUser() throws UserNotFoundException {
        List<User> listUser = new ArrayList<>();
        userRepo.findAll().forEach(i -> listUser.add(User.toModel(i)));
        return listUser;
    }

    public Long deleteUser(Long id) {
        userRepo.deleteById(id);
        return id;
    }

    public void deleteAllUser() {
        userRepo.deleteAll();
    }
}

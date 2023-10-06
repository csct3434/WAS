package application.service;

import application.dto.user.UserListDto;
import application.dto.user.UserLoginDto;
import application.dto.user.UserSaveDto;
import db.UserDatabase;
import application.model.User;
import webserver.exceptions.BadRequestException;
import webserver.exceptions.ConflictException;
import webserver.exceptions.LoginFailException;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static UserService instance;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void add(UserSaveDto dto) throws BadRequestException, ConflictException {
        verifySaveDto(dto);

        verifyUserIdNotExists(dto.getUserId());

        UserDatabase.addUser(new User(
                dto.getUserId(),
                dto.getPassword(),
                dto.getUsername(),
                dto.getEmail()));
    }

    private void verifySaveDto(UserSaveDto dto) throws BadRequestException {
        checkNullOrBlank(dto.getUserId());
        checkNullOrBlank(dto.getPassword());
        checkNullOrBlank(dto.getUsername());
        checkNullOrBlank(dto.getEmail());
    }

    private void verifyUserIdNotExists(String userId) throws ConflictException {
        if (UserDatabase.checkUserIdExists(userId)) {
            throw new ConflictException();
        }
    }

    public List<UserListDto> getList() {
        ArrayList<UserListDto> userList = new ArrayList<>();

        for (User user : UserDatabase.findAll()) {
            userList.add(new UserListDto(user.getUserId(), user.getUsername(), user.getEmail()));
        }

        return userList;
    }

    public void verifyCredentials(UserLoginDto dto) throws LoginFailException {
        verifyLoginDto(dto);

        User user = UserDatabase.findById(dto.getUserId());

        if((user == null) || !(user.getPassword().equals(dto.getPassword()))) {
            throw new LoginFailException();
        }
    }

    private void verifyLoginDto(UserLoginDto dto) throws LoginFailException {
        if (dto.getUserId() == null || dto.getPassword() == null) {
            throw new LoginFailException();
        }
    }

    public String findNameById(String userId) {
        return UserDatabase.findById(userId).getUsername();
    }

    private void checkNullOrBlank(String parameter) throws BadRequestException {
        if (parameter == null || parameter.isBlank()) {
            throw new BadRequestException();
        }
    }
}

package kg.apps.service;

import kg.apps.model.User;

public interface UserService {
    User get(Integer id);

    User createWholeUser(String email);
}

package kg.apps.service.impl;

import kg.apps.model.User;
import kg.apps.repository.UserRepository;
import kg.apps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User get(Long id) {
        return userRepository.getOne(id);
    }

}

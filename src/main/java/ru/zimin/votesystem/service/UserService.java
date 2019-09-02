package ru.zimin.votesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.zimin.votesystem.AuthorizedUser;
import ru.zimin.votesystem.model.User;
import ru.zimin.votesystem.repository.UserRepository;
import ru.zimin.votesystem.to.UserTo;
import ru.zimin.votesystem.util.exceptions.NotFoundException;

import java.util.List;

import static ru.zimin.votesystem.util.UserUtil.prepareToSave;
import static ru.zimin.votesystem.util.UserUtil.updateFromTo;
import static ru.zimin.votesystem.util.ValidationUtil.*;


@Service
public class UserService implements UserDetailsService {

    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;;

    
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        checkNew(user);
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(userRepository.delete(id) != 0, id);
    }

    public User get(int id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return userRepository.findAll(SORT_NAME_EMAIL);
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(userRepository.save(prepareToSave(user, passwordEncoder)), user.getId());
    }

    public void update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        userRepository.save(prepareToSave(user, passwordEncoder));
    }

    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}

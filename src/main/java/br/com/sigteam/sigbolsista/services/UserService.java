package br.com.sigteam.sigbolsista.services;

import br.com.sigteam.sigbolsista.exceptions.UserAlreadyExistException;
import br.com.sigteam.sigbolsista.models.Role;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user) throws UserAlreadyExistException {
        if (checkIfUserExist(user.getUsername())) {
            throw new UserAlreadyExistException("Já existe um usuário com este username!");
        }
        encodePassword(user);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return user;
    }

    public boolean checkIfUserExist(String username) {
        return userRepository.findByUsername(username) != null ? true : false;
    }

    private void encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    
    public void save(User user) {
    	userRepository.save(user);
    }

	public boolean checkRole(User user) {
		List<Integer> roles =  Arrays.asList(Role.ADMIN);
		return !roles.contains(user.getRole());
	}
}

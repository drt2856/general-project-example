package com.cu.generalprojectexample.service;


import com.cu.generalprojectexample.model.User;
import com.cu.generalprojectexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService/* implements UserDetailsService */{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityService authoritiesService;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    public User save(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            throw new EntityExistsException("There is already existing entity with such ID in the database.");
        }

        return userRepository.save(user);
    }

    public User update(User user) {
        if (user.getId() != null && !userRepository.existsById(user.getId())) {
            throw new EntityNotFoundException("There is no entity with such ID in the database.");
        }

        return userRepository.save(user);
    }

    public void delete(int id) {
        authoritiesService.deleteAllByUserId(id);
        userRepository.deleteById(id);
    }


   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findById(username);
        UserDTO us= convertirEntidadADto(user);
        if(user!=null){
            User.UserBuilder userBuilder= User.withUsername(username);
            userBuilder.password("{noop}"+us.getPassword()).roles(convertirListaEnArray(us.getAuthorities()));
            return userBuilder.build();
        }else{
            throw  new UsernameNotFoundException(username);
        }
    }
    private UserDTO convertirEntidadADto(User user_entidad) {
        UserDTO userDto = new UserDTO(user_entidad);
        userDto.setAuthorities(authoritiesService.findByUsername(user_entidad.getUsername()));
        return userDto;
    }
    private String[] convertirListaEnArray(List<Authorities> list){
        String[] ed=new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ed[i]=list.get(i).getAuthoritiesPK().getAuthority();
        }
        return ed;
    }
*/
    public boolean existByUsername(int username) {
        return userRepository.existsById(username);
    }
}

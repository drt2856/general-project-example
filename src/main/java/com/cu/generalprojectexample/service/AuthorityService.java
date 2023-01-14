package com.cu.generalprojectexample.service;

import com.cu.generalprojectexample.model.Authority;
import com.cu.generalprojectexample.model.AuthorityPK;
import com.cu.generalprojectexample.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    public Authority findById(AuthorityPK id) {
        return authorityRepository.findById(id).get();
    }

    public List<Authority> findByUserId(int id) {
        return authorityRepository.findByUserid(id);
    }

    public Authority save(Authority authority) {
        if (authority.getAuthorityPK() != null && authorityRepository.existsById(authority.getAuthorityPK())) {
            throw new EntityExistsException("There is already existing entity with such ID in the database.");
        }

        return authorityRepository.save(authority);
    }

    public Authority update(Authority authority) {
        if (authority.getAuthorityPK() != null && !authorityRepository.existsById(authority.getAuthorityPK())) {
            throw new EntityNotFoundException("There is no entity with such ID in the database.");
        }

        return authorityRepository.save(authority);
    }

    public void delete(AuthorityPK id) {
        authorityRepository.deleteById(id);
    }

    public void deleteAllByUserId(int userId) {
        List<Authority> aux = findByUserId(userId);
        for (Authority a : aux) {
            delete(a.getAuthorityPK());
        }
    }
}

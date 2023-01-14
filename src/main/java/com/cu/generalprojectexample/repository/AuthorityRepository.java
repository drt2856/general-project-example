package com.cu.generalprojectexample.repository;

import com.cu.generalprojectexample.model.Authority;
import com.cu.generalprojectexample.model.AuthorityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityPK> {
    @Query(value="SELECT a FROM Authority a WHERE a.authorityPK.userid = :userid")
    List<Authority> findByUserid(int userid);
}

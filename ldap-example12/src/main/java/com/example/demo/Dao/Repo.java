package com.example.demo.Dao;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entry.Users;

@Repository
public interface Repo extends LdapRepository<Users>,RepoAdditional {


}

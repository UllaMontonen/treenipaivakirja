package Harjoitustyo.Treenipaivakirja.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Harjoitustyo.Treenipaivakirja.domain.ApplicationUser;
import Harjoitustyo.Treenipaivakirja.domain.ApplicationUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	ApplicationUserRepository repository;
	public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
	this.repository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername: " + username);
		ApplicationUser curruser = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
		AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}
}
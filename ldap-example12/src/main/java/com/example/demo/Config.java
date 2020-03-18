package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.demo.Dao.GroupRepo;
import com.example.demo.Dao.GroupRepository;
import com.example.demo.Dao.RepoAdditional;
import com.example.demo.Dao.RepoImpl;
import com.example.demo.Dao.UserRepo;
import com.example.demo.Dao.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.filter.JWTFilter;
import com.example.demo.service.GropuService;
import com.example.demo.service.GroupRepositoryService;
import com.example.demo.service.Service;
import com.example.demo.service.ServiceImpl;
import com.example.demo.service.UserRepositoryService;
import com.example.demo.service.UserService;
import com.example.demo.view.Response1;

@Configuration
@EnableLdapRepositories
@ComponentScan(basePackages = {"com.example.demo","com.ldap.ldapcurd"})
public class Config {
	
	@Autowired
	private JWTFilter jwtFilter;
	
	@Bean
	public LdapContextSource ldapContextSource()
	{
		LdapContextSource lcs= new LdapContextSource();
		lcs.setUrl("ldap://40.121.41.30:10389");
		lcs.setUserDn("uid=admin,ou=system");
		lcs.setPassword("secret");
	//	lcs.setBase("dc=example,dc=com");
	//	lcs.setBase("o=winfo");
		return lcs;
	}
	
	@Bean
	public LdapTemplate ldapTemplate()
	{
		return  new LdapTemplate(ldapContextSource());
	}
	
	@Bean
	public RepoAdditional repoAdditional()
	{
		return  new RepoImpl();
	}

	@Bean
	public Service service()
	{
		return  new ServiceImpl();
	}
	
	@Bean
	public FilterRegistrationBean<JWTFilter> filterRegistrationBean() {
		FilterRegistrationBean<JWTFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(jwtFilter);
		filterRegistrationBean.addUrlPatterns("/ldap/*");
		return filterRegistrationBean;
	}

	@Bean
	public User getClient() {
		return new User();
	}
	
	
//	@Bean
//	public UserRepo user()
//	{
//		return  new UserRepository();
//	}
//	@Bean
//	public GroupRepo group()
//	{
//		return  new GroupRepository();
//	}
//
//	@Bean
//	public GropuService service1()
//	{
//		return  new GroupRepositoryService();
//	}

	
	
}

package com.example.demo.Dao;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.AuthenticatedLdapEntryContextMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapEntryIdentification;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.example.demo.entity.UserBean;
import com.example.demo.entry.Group;
import com.example.demo.entry.Users;
import com.example.demo.entry.Users;
import com.example.demo.view.PersonVo;
import com.example.demo.view.Response1;

public class RepoImpl implements RepoAdditional {

	@Autowired
	private LdapTemplate ldapTemplate;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private Repo repo;

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private GroupRepo groupRepo;

	public static final String BASE_DN = "o=winfo";
	public int pwdFailureLimit;
	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private String body;

	protected Name buildDn(Users p) {
		return LdapNameBuilder.newInstance(BASE_DN).add("ou", "wdas").add("ou", "users").add("cn", p.getFullname())
				.build();
	}

	@Override
	public String unlockUser(Users pvo) {
		System.out.println("this is pvo" + pvo);

		Name dn = buildDn(pvo);

		DirContextOperations context = ldapTemplate.lookupContext(dn);
		ModificationItem[] modificationItems;
		modificationItems = new ModificationItem[1];

		modificationItems[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE,
				new BasicAttribute("pwdAccountLockedTime"));

		ldapTemplate.modifyAttributes(dn, modificationItems);

		return "Account Unlocked";
	}

	@Override
	public ResponseEntity<Object> auth(String uid, String password) {

		boolean flag = false;

		String pass = password;
		System.out.println("authentication process running.." + pass);
		Response1 res = new Response1();
		try {
			System.out.println("this is try block");
			AuthenticatedLdapEntryContextMapper<DirContextOperations> mapper = authenticatedLdapEntryContextMapper();

			DirContextOperations dco = ldapTemplate.authenticate(LdapQueryBuilder.query().where("cn").is(uid), password,
					mapper);
			System.out.println("this is mapper" + mapper);
			flag = ((dco != null) && (dco.getStringAttribute("cn").equals(uid)));
			System.out.println("this is login checking " + flag);
			if (flag == true) {
				System.out.println("this is if loop");
				Session session = entityManager.unwrap(Session.class);
				UserBean userObj = session.get(UserBean.class, uid);
				System.out.println("database object" + userObj);
				userObj.setPwdAttemptsLeft(5);
				System.out.println("database object 1" + userObj);
				res.setAuthentication(true);
				res.setPwdFailureLimit(5);
				res.setStatus(userObj.getStatus());
				session.saveOrUpdate(userObj);
				LdapQuery query = query().where("cn").is(uid);
				Users g = ldapTemplate.findOne(query, Users.class);
				res.setGroup(g.getGroup());
				System.out.println("this is response in dao" + res);
			}

		} catch (Exception e) {
			System.out.println("this is catch block");
			Session session = entityManager.unwrap(Session.class);
			UserBean userObj = session.get(UserBean.class, uid);
			int AttemptsLeft = userObj.getPwdAttemptsLeft();
			if (AttemptsLeft > 0) {
				System.out.println("this is if loop with decrement");
				int limit = AttemptsLeft - 1;
				userObj.setPwdAttemptsLeft(limit);
				System.out.println("when credentials are wrong : " + userObj.getPwdAttemptsLeft());
				pwdFailureLimit = userObj.getPwdAttemptsLeft();
				session.saveOrUpdate(userObj);
			}
			res.setAuthentication(false);
			res.setPwdFailureLimit(pwdFailureLimit);
			System.out.println("this is response" + res);
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Baeldung-Example-Header", "Value-ResponseEntityBuilderWithHttpHeaders");

		return ResponseEntity.ok().headers(responseHeaders).body(res);
	}

	private AuthenticatedLdapEntryContextMapper<DirContextOperations> authenticatedLdapEntryContextMapper() {
		return (DirContext ctx, LdapEntryIdentification ldapEntryIdentification) -> {
			try {
				return (DirContextOperations) ctx.lookup(ldapEntryIdentification.getRelativeName());
			} catch (NamingException e) {
				throw new RuntimeException("lookup failed" + ldapEntryIdentification.getRelativeName(), e);
			}

		};
	}

	@Override
	public String resetPassword(Users p1, String currentpassword) {
		boolean flag = false;
		String uid = p1.getFullname();
		System.out.println(uid);

		System.out.println("this is try block");
		AuthenticatedLdapEntryContextMapper<DirContextOperations> mapper = authenticatedLdapEntryContextMapper();

		DirContextOperations dco = ldapTemplate.authenticate(LdapQueryBuilder.query().where("cn").is(uid),
				currentpassword, mapper);
		System.out.println("this is mapper" + mapper);
		flag = ((dco != null) && (dco.getStringAttribute("cn").equals(uid)));
		System.out.println("this is login checking " + flag);
		if (flag == true) {
			String cn = p1.getFullname();
			Optional<Users> p = repo.findOne(LdapQueryBuilder.query().where("cn").is(cn));
			Users pp = p.isPresent() ? p.get() : new Users();
			System.out.println("this is before update " + pp);
			pp.setUserpassword(p1.getUserpassword());
			System.out.println("this is after update " + pp);
			ldapTemplate.update(pp);
			String email = pp.getMail();
			String userName = pp.getUid();
			try {
				restConfirmationMail(email,userName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Session session = entityManager.unwrap(Session.class);
			UserBean userObj = session.get(UserBean.class, uid);
			userObj.setStatus("active");
			session.saveOrUpdate(userObj);
		}
		return "Your password was reseted";

	}

	@Override
	public String emailSender(Users p) {
//			String cn =p.getFullname();
//			Optional<Users> userLdapObj = repo.findOne(LdapQueryBuilder.query().where("cn").is(cn));
//			Users pp =userLdapObj.isPresent()?userLdapObj.get():new Users();
//			System.out.println("this is person object"+pp);
		String email = p.getMail();
		try {

			String password = sendEmail(email);
			System.out.println("this is auto password " + password);
			p.setUserpassword(password);
			System.out.println("this is user object " + p);
			// ldapTemplate.update(pp);
			return "Email Sent!";
		} catch (Exception ex) {
			return "Error in sending email: " + ex;
		}
	}

	private String sendEmail(String email) throws Exception {

		int passwordLength = 10;
		String password = generatePassword(passwordLength);

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		System.out.println("this is helper  and message"+message + helper);
		helper.setTo(email);
		helper.setText("Your Verification password is : " + password);
		helper.setSubject("Confirm Password");
		sender.send(message);
		return password;

	}

	public static String generatePassword(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}
	
	public void restConfirmationMail(String email,String userName) throws Exception {

		int passwordLength = 10;
		String password = generatePassword(passwordLength);

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setTo(email);
		helper.setText("Hi "+userName + ",your password was resetted successfully");
		helper.setSubject("Password Reset");
		sender.send(message);
	}

	@Override
	public String deleteUser(Users po) {
		String cn = po.getFullname();
		Optional<Users> p = repo.findOne(LdapQueryBuilder.query().where("cn").is(cn));
		Users pp = p.isPresent() ? p.get() : new Users();
		Group group = groupRepo.userGroup(pp.getGroup());
		System.out.println("group is " + group.toString());
		Users uu = new Users();
		uu.setDn(pp.getDn());
		uu.setFullname(po.getFullname());
		uu.setGivenname(po.getGivenname());
		uu.setDescription(po.getDescription());
		uu.setLastname(po.getLastname());
		uu.setMail(po.getMail());
		uu.setUid(po.getUid());
		uu.setUserpassword(po.getUserpassword());
		System.out.println("get dn" + uu.getDn());
		groupRepo.deleteMemberFromGroup(uu, group);
		Session session = entityManager.unwrap(Session.class);
		UserBean user = session.get(UserBean.class, cn);
		try {
			repo.delete(pp);
			session.delete(user);
			return "user was deleted";
		} catch (Exception e) {
			return "user was not deleted";
		}

	}

}

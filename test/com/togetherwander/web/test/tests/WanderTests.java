package com.togetherwander.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.togetherwander.web.dao.User;
import com.togetherwander.web.dao.UsersDao;
import com.togetherwander.web.dao.Wander;
import com.togetherwander.web.dao.WanderDao;

@ActiveProfiles("dev")

@ContextConfiguration(locations = {
		"classpath:com/togetherwander/web/config/dao-context.xml",
		"classpath:com/togetherwander/web/config/security-context.xml",
		"classpath:com/togetherwander/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class WanderTests {

	@Autowired
	private UsersDao usersDao;

	@Autowired 
	private WanderDao wanderDao;
	
	@Autowired
	private DataSource dataSource;

	private User user1 = new User("John Purcell", "jpurcell", "hellothere",
			"john@caveofprogramming.com", true, "ROLE_USER");

	private Wander wander1 = new Wander();


	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from dates");
		jdbc.execute("delete from travelers");
		jdbc.execute("delete from wanders");
		jdbc.execute("delete from users");

	}

	@Test
	public void testCreateUser() {
		usersDao.create(user1);

		List<User> users1 = usersDao.getAllUsers();
				
		assertEquals("One user should have been created and retrieved", 1,
				users1.size());
	}

	
	@Test
	public void testCreateWander() {
		wander1.setName("Tigard");
		wander1.setNotes("New investment hopefully!");
		wanderDao.create(wander1, user1.getUsername());
		List<Wander> wander1 = wanderDao.getWanders();
		assertEquals("One Wander should have been created and retrieved", 1,
				wander1.size());
	}
	
}

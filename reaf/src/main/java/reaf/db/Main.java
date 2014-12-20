package reaf.db;

import java.sql.SQLException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws SQLException {
		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml")){
			JdbcDaoImpl jdbc = ctx.getBean("jdbcDaoImpl",JdbcDaoImpl.class);
			System.out.println(jdbc.getCategory((short) 0, 1));
		}

	}

}

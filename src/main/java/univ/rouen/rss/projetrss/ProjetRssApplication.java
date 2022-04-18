package univ.rouen.rss.projetrss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetRssApplication {

	public static void main(String[] args) {
		System.getProperties().put("server.port","8100");
		SpringApplication.run(ProjetRssApplication.class, args);
	}

}

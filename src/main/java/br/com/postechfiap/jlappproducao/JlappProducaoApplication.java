package br.com.postechfiap.jlappproducao;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRabbit
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"br.com.postechfiap.jlappproducao"})
@EnableJpaRepositories(basePackages = "br.com.postechfiap.jlappproducao.infra.db.repository")
public class JlappProducaoApplication {

  public static void main(String[] args) {
    SpringApplication.run(JlappProducaoApplication.class, args);
  }

}

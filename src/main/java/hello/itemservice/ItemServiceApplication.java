package hello.itemservice;

import hello.itemservice.config.*;
import hello.itemservice.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


//@Import(MemoryConfig.class)
//@Import(JdbcTemplateV2Config.class)
// @Import(JdbcTemplateV3Config.class)
///@Import(MyBatisConfig.class)
//@Import(JpaConfig.class)
@Import(SpringDataJpaConfig.class)
@Slf4j
@SpringBootApplication(scanBasePackages = "hello.itemservice.web")
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	@Bean
	@Profile("local")//특정 프로필(속성 파일에서)이 활성화 되어있는 경우에만 실행 가능하다.
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		return new TestDataInit(itemRepository);
	}


	/*@Bean
	@Profile("test")//프로필이 test인 경우에만 bean에 등록한다.
	public DataSource dataSource() {
		log.info("메모리 데이터베이스 초기화");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
		//jdbc:h2:mem:db 는 임베디드 모드로 동작하는 H2를 사용할 수 있게 한다.
		//DB_CLOSE_DELAY=-1 임베디드 모드에서는 커넥션이 끊어지면 데이터베이스도 종료되는데 이를 방지한다.
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}*/
}

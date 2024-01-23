package kirderf.tvmazemapperdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TvmazeMapperDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TvmazeMapperDemoApplication.class, args);
    }

}

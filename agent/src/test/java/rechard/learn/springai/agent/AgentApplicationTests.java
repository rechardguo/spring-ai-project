package rechard.learn.springai.agent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;


class AgentApplicationTests {

	@Test
	void react() {
		//java react
		//publisher + subscriber
		Flux.just("1","2","3").concatWithValues("done").subscribe(data->{
			System.out.println(data);
		});
	}

}

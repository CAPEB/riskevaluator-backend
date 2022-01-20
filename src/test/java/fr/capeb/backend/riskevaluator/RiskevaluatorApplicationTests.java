package fr.capeb.backend.riskevaluator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RiskevaluatorApplicationTests {

	@Test
	void TestControllerStatus() {
		TestController controller = new TestController();
		assert(controller.getStatus().equals("CAPEB ENV status OK"));



	}

}

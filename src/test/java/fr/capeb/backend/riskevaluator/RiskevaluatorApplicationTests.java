package fr.capeb.backend.riskevaluator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RiskevaluatorApplicationTests {

	@Autowired
	public TestController testController;
	@Test
	void TestControllerStatus() {
		assert(testController.getStatus().equals("CAPEB ENV status OK New"));
	}

}

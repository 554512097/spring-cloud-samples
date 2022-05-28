package com.example.legacyapp.services;

import com.example.legacyapp.TheLegacyApp;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.web.client.RestTemplate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

/**
 * Version of the test with stub runner
 *
 * @author Marcin Grzejszczak
 */
@SpringBootTest(classes = TheLegacyApp.class,
		webEnvironment = NONE)
@AutoConfigureStubRunner(
		ids = "com.example.github:github-webhook:+:stubs:7654",
		repositoryRoot = "https://repo.spring.io/libs-milestone-local",
		stubsMode = StubRunnerProperties.StubsMode.REMOTE
)
public class OnlineModeTests {

	@Test
	public void should_return_charge_collection() {
		String object = new RestTemplate()
				.getForObject("http://localhost:7654/", String.class);

		BDDAssertions.then(object).contains("dsyer");
	}
}
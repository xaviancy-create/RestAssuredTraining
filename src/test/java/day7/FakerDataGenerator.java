package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {
	
	@Test
	void testGenerateDummyData() {
		Faker faker=new Faker();
		System.out.println(faker.name().firstName());
		System.out.println(faker.name().firstName());
		System.out.println(faker.name().username());
		System.out.println(faker.internet().password());
		System.out.println(faker.phoneNumber().cellPhone());
		System.out.println(faker.internet().safeEmailAddress());
		System.out.println(faker.business().creditCardNumber());
	}

}

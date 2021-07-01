import com.github.javafaker.Faker;

public class Main {

    public static void main(String[] args) {

        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            var person = faker.name();
            var date = faker.date();
            System.out.println("==================================================");
            System.out.printf("Name: %s%n", person.name());
            System.out.printf("Date: %s%n", date.birthday(18, 95));
            System.out.println("==================================================");
        }

    }
}

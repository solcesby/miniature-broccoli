package com.itechart.springproject.utils;

import com.github.javafaker.Faker;
import com.itechart.springproject.dto.user.UserCreateDTO;

public class TestUtils {

    private static final Faker FAKER = new Faker();

    public static UserCreateDTO generateUserCreateDTO() {
        final UserCreateDTO dto = new UserCreateDTO();
        dto.setFirstName(FAKER.name().firstName());
        dto.setLastName(FAKER.name().lastName());
        dto.setPhone('+' + FAKER.phoneNumber().subscriberNumber(12));
        dto.setEmail(FAKER.internet().emailAddress());
        dto.setPassword(FAKER.bothify("?????#####"));

        return dto;
    }
}

package com.jamilis.login.constants;

import com.jamilis.login.dto.SignUpRequestDto;
import com.jamilis.login.entity.UserEntity;
import com.jamilis.login.utils.EncryptUtils;
import java.time.Instant;
import java.util.UUID;

public class UserConstants {
    public static final SignUpRequestDto SIGN_UP_REQUEST_DTO = new SignUpRequestDto("Test01",
            "firsttest@email.com", "T01password", PhoneConstants.PHONE_DTO_LIST);

    public static final SignUpRequestDto SIGN_UP_REQUEST_DTO2 = new SignUpRequestDto("Test02",
            "secondtest@email.com", "T02password", PhoneConstants.PHONE_DTO_LIST);
    public static final String EXPIRED_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1c2VyQGV4YW1wbGUuY29tIiwiZXhwIjoxNzAxMDEyNDczLCJpYXQiOjE3MDEwMTI0MTN9.sOasG023zMojhfRqL30LolJpgWW_bbR0ZrCneot7E5U";
    public static final UserEntity USER_ENTITY = new UserEntity(UUID.randomUUID().toString(), Instant.now(),
            Instant.now(), true, "Test01", "test01@email.com",
            EncryptUtils.encrypt("T01password", "test01@email.com"), PhoneConstants.PHONE_ENTITY_LIST);
}

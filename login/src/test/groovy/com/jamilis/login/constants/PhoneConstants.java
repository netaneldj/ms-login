package com.jamilis.login.constants;

import com.jamilis.login.dto.PhoneDto;
import com.jamilis.login.entity.PhoneEntity;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PhoneConstants {
    public static final PhoneDto PHONE_DTO = new PhoneDto(23456789L, 11, "AR");
    public static final PhoneEntity PHONE_ENTITY = new PhoneEntity(1, UUID.randomUUID().toString(), 23456789L, 11, "AR");
    public static final List<PhoneEntity> PHONE_ENTITY_LIST = Collections.singletonList(PHONE_ENTITY);
    public static final List<PhoneDto> PHONE_DTO_LIST = Collections.singletonList(PHONE_DTO);


}

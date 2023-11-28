package com.jamilis.login.mapper;

import com.jamilis.login.dto.PhoneDto;
import com.jamilis.login.entity.PhoneEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IPhoneMapper {
    IPhoneMapper INSTANCE = Mappers.getMapper(IPhoneMapper.class);

    PhoneEntity mapDto(PhoneDto dto);

    PhoneDto mapEntity(PhoneEntity entity);

    List<PhoneEntity> mapDtoList(List<PhoneDto> dto);

    List<PhoneDto> mapEntityList(List<PhoneEntity> entity);

}

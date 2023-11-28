package com.jamilis.login.mapper

import com.jamilis.login.constants.PhoneConstants
import spock.lang.Specification

class IPhoneMapperTest extends Specification {
    def "MapDto"() {
        given:
        def phoneDto = PhoneConstants.PHONE_DTO
        when:
        def phoneEntity = IPhoneMapper.INSTANCE.mapDto(phoneDto)
        then:
        phoneEntity.getNumber().equals(phoneDto.getNumber())
        phoneEntity.cityCode.equals(phoneDto.getCityCode())
        phoneEntity.countryCode.equals(phoneDto.getCountryCode())
    }

    def "MapEntity"() {
        given:
        def phoneEntity = PhoneConstants.PHONE_ENTITY
        when:
        def phoneDto = IPhoneMapper.INSTANCE.mapEntity(phoneEntity)
        then:
        phoneEntity.getNumber().equals(phoneDto.getNumber())
        phoneEntity.cityCode.equals(phoneDto.getCityCode())
        phoneEntity.countryCode.equals(phoneDto.getCountryCode())
    }

    def "MapDtoList"() {
        given:
        def phoneEntityList = PhoneConstants.PHONE_ENTITY_LIST
        when:
        def phoneDtoList = IPhoneMapper.INSTANCE.mapEntityList(phoneEntityList)
        then:
        for (int i = 0; i < phoneDtoList.size(); i++) {
            phoneEntityList.get(i).getNumber().equals(phoneDtoList.get(i).getNumber())
            phoneEntityList.get(i).cityCode.equals(phoneDtoList.get(i).getCityCode())
            phoneEntityList.get(i).countryCode.equals(phoneDtoList.get(i).getCountryCode())
        }
    }

    def "MapEntityList"() {
        given:
        def phoneDtoList = PhoneConstants.PHONE_DTO_LIST
        when:
        def phoneEntityList = IPhoneMapper.INSTANCE.mapDtoList(phoneDtoList)
        then:
        for (int i = 0; i < phoneEntityList.size(); i++) {
            phoneEntityList.get(i).getNumber().equals(phoneDtoList.get(i).getNumber())
            phoneEntityList.get(i).cityCode.equals(phoneDtoList.get(i).getCityCode())
            phoneEntityList.get(i).countryCode.equals(phoneDtoList.get(i).getCountryCode())
        }
    }
}

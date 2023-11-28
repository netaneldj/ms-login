package com.jamilis.login.utils

import spock.lang.Specification

class EncryptUtilsTest extends Specification {
    def "Encrypt Decrypt"() {
        given:
        def myString = "abababa"
        def myKey = "abcd"
        when:
        def encryptedString = EncryptUtils.encrypt(myString, myKey)
        then:
        encryptedString != myString
        encryptedString != myKey
        EncryptUtils.decrypt(encryptedString, myKey) == myString
    }
}

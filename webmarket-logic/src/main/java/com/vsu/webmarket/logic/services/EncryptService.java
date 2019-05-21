package com.vsu.webmarket.logic.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class EncryptService {
    public String encryptString(String toEncrypt) {
        return DigestUtils.sha256Hex(toEncrypt);
    }
}

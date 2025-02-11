package fr.epsitek.api.demo.infrastructure.signature;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public interface SignatureService {
    byte[] sign(String content) throws Exception;
    boolean verify(String content, byte[] signatureToVerify) throws Exception;
}

package fr.epsitek.api.demo.infrastructure.signature;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Arrays;

public class XmlDigitalSignatureService implements SignatureService {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public XmlDigitalSignatureService(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey(String file, char[] password, String storeType, String alias) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(storeType);
        keyStore.load(new FileInputStream(file), password);
        return (PrivateKey) keyStore.getKey(alias, password);
    }

    public PublicKey getPublicKey(String file, char[] password, String storeType, String alias) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(storeType);
        keyStore.load(new FileInputStream(file), password);
        Certificate certificate = keyStore.getCertificate(alias);
        return certificate.getPublicKey();
    }

    public byte[] sign(byte[] message, String signingAlgorithm, PrivateKey signingKey) throws SecurityException {
        try {
            Signature signature = Signature.getInstance(signingAlgorithm);
            signature.initSign(signingKey);
            signature.update(message);
            return signature.sign();
        } catch (GeneralSecurityException exp) {
            throw new SecurityException("Error during signature generation", exp);
        }
    }

    public static boolean verify(byte[] messageBytes, String signingAlgorithm, PublicKey publicKey, byte[] signedData) {
        try {
            Signature signature = Signature.getInstance(signingAlgorithm);
            signature.initVerify(publicKey);
            signature.update(messageBytes);
            return signature.verify(signedData);
        } catch (GeneralSecurityException exp) {
            throw new SecurityException("Error during verifying", exp);
        }
    }

    public static byte[] signWithMessageDigestAndCipher(byte[] messageBytes, String hashingAlgorithm, PrivateKey privateKey) {
        try {
            MessageDigest md = MessageDigest.getInstance(hashingAlgorithm);
            byte[] messageHash = md.digest(messageBytes);
            DigestAlgorithmIdentifierFinder hashAlgorithmFinder = new DefaultDigestAlgorithmIdentifierFinder();
            AlgorithmIdentifier hashingAlgorithmIdentifier = hashAlgorithmFinder.find(hashingAlgorithm);
            DigestInfo digestInfo = new DigestInfo(hashingAlgorithmIdentifier, messageHash);
            byte[] hashToEncrypt = digestInfo.getEncoded();

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(hashToEncrypt);
        } catch (GeneralSecurityException | IOException exp) {
            throw new SecurityException("Error during signature generation", exp);
        }
    }

    public static boolean verifyWithMessageDigestAndCipher(byte[] messageBytes, String hashingAlgorithm, PublicKey publicKey, byte[] encryptedMessageHash) {
        try {
            MessageDigest md = MessageDigest.getInstance(hashingAlgorithm);
            byte[] newMessageHash = md.digest(messageBytes);
            DigestAlgorithmIdentifierFinder hashAlgorithmFinder = new DefaultDigestAlgorithmIdentifierFinder();
            AlgorithmIdentifier hashingAlgorithmIdentifier = hashAlgorithmFinder.find(hashingAlgorithm);
            DigestInfo digestInfo = new DigestInfo(hashingAlgorithmIdentifier, newMessageHash);
            byte[] hashToEncrypt = digestInfo.getEncoded();

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] decryptedMessageHash = cipher.doFinal(encryptedMessageHash);
            return Arrays.equals(decryptedMessageHash, hashToEncrypt);
        } catch (GeneralSecurityException | IOException exp) {
            throw new SecurityException("Error during verifying", exp);
        }
    }

    @Override
    public byte[] sign(String content) throws Exception {
        if (privateKey == null) {
            privateKey = this.getPrivateKey("src/main/resources/keystore.jks", "password".toCharArray(), "JKS", "alias");
        }
        return signWithMessageDigestAndCipher(content.getBytes(), "SHA-256", privateKey);
    }

    @Override
    public boolean verify(String content, byte[] signatureToVerify) throws Exception {
        if (publicKey == null) {
            publicKey = this.getPublicKey("src/main/resources/keystore.jks", "password".toCharArray(), "JKS", "alias");
        }
        return verifyWithMessageDigestAndCipher(content.getBytes(), "SHA-256", publicKey, signatureToVerify);
    }
}

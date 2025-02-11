package fr.epsitek.api.demo.infrastructure.signature;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class XmlDigitalSignatureServiceTest {

    @Test
    void givenContent_whenSign_thenRetunSignature() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        XmlDigitalSignatureService service = new XmlDigitalSignatureService(keyPair.getPrivate(), keyPair.getPublic());

        String content = "<infoExchange>content</infoExchange>";
        byte[] signature = service.sign(content);
        log.info("Signature={}", Base64.getEncoder().encodeToString(signature));
        boolean isValid = service.verify(content, signature);

        assert isValid;
    }
}

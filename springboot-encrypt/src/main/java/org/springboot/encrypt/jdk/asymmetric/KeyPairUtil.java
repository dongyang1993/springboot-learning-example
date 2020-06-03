package org.springboot.encrypt.jdk.asymmetric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.*;
import java.util.Base64;

public class KeyPairUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(KeyPairUtil.class);

    public static void generate(String algorithm,int keySize) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
            generator.initialize(keySize);
            KeyPair keyPair = generator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            LOGGER.info("privateKey:{}", privateKeyStr);
            LOGGER.info("publicKey:{}", publicKeyStr);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    public static void main(String[] args) {
        generate("RSA", 2048);
    }

}

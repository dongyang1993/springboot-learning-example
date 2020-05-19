package org.springboot.encrypt.tink;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.config.TinkConfig;
import com.google.crypto.tink.signature.SignatureKeyTemplates;

import java.util.Base64;

public final class TinkUtil {

    public static void main(String[] args) throws Exception {
        TinkConfig.register();
        KeysetHandle keysetHandle = KeysetHandle.generateNew(AeadKeyTemplates.AES128_GCM);

        System.out.println(keysetHandle.toString());

        Aead aead = keysetHandle.getPrimitive(Aead.class);
        String data = "中国 China";

        byte[] encrypt = aead.encrypt(data.getBytes(), null);
        System.out.println(new String(encrypt));
        System.out.println(new String(Base64.getEncoder().encodeToString(encrypt)));

        byte[] decrypt = aead.decrypt(encrypt, null);
        System.out.println(new String(decrypt));
    }
}

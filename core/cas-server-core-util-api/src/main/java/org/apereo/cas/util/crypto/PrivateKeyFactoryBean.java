package org.apereo.cas.util.crypto;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * Factory Bean for creating a private key from a file.
 *
 * @author Scott Battaglia
 * @since 3.1
 */
@Slf4j
public class PrivateKeyFactoryBean extends AbstractFactoryBean<PrivateKey> {


    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private Resource location;
    private String algorithm;

    @Override
    protected PrivateKey createInstance() throws Exception {
        PrivateKey key = readPemPrivateKey();
        if (key == null) {
            LOGGER.debug("Key [{}] is not in PEM format. Trying next...", this.location);
            key = readDERPrivateKey();
        }
        return key;
    }

    private PrivateKey readPemPrivateKey() {
        LOGGER.debug("Attempting to read as PEM [{}]", this.location);
        try (Reader in = new InputStreamReader(this.location.getInputStream(), StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(in);
             PEMParser pp = new PEMParser(br)) {
            final PEMKeyPair pemKeyPair = (PEMKeyPair) pp.readObject();
            final KeyPair kp = new JcaPEMKeyConverter().getKeyPair(pemKeyPair);
            return kp.getPrivate();
        } catch (final Exception e) {
            LOGGER.debug("Unable to read key", e);
            return null;
        }
    }

    private PrivateKey readDERPrivateKey() {
        LOGGER.debug("Attempting to read key as DER [{}]", this.location);
        try (InputStream privKey = this.location.getInputStream()) {
            final byte[] bytes = new byte[privKey.available()];
            privKey.read(bytes);
            final PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(bytes);
            final KeyFactory factory = KeyFactory.getInstance(this.algorithm);
            return factory.generatePrivate(privSpec);
        } catch (final Exception e) {
            LOGGER.debug("Unable to read key", e);
            return null;
        }
    }

    @Override
    public Class getObjectType() {
        return PrivateKey.class;
    }

    public void setLocation(final Resource location) {
        this.location = location;
    }

    public void setAlgorithm(final String algorithm) {
        this.algorithm = algorithm;
    }

    public Resource getLocation() {
        return this.location;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

}

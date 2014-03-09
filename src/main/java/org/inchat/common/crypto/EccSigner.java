/*
 * Copyright (C) 2013, 2014 inchat.org
 *
 * This file is part of inchat-common.
 *
 * inchat-common is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * inchat-common is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.inchat.common.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import org.inchat.common.util.Exceptions;

public class EccSigner {

    public final static String ALGORITHM_NAME = "SHA256withECDSA";
    Signature signature;

    /**
     * Creates a new {@link EccSigner} that signs/verifies using
     * {@code SHA256withECDSA}.
     *
     * @throws IllegalStateException If the internally used {@link Signature}
     * could not be set up correctly.
     */
    public EccSigner() {
        initSignature();
    }

    private void initSignature() {
        try {
            BouncyCastleIntegrator.initBouncyCastleProvider();
            signature = Signature.getInstance(ALGORITHM_NAME, BouncyCastleIntegrator.PROVIDER_NAME);
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            throw new IllegalStateException("Could not get a Signature instance: " + ex.getMessage());
        }
    }

    /**
     * Signs the given {@code data} using the {@link PrivateKey}. The key has to
     * be generated using {@link EccKeyPairGenerator}.
     *
     * @param data The data to sign. This can be large since internally, a
     * digest is generated and afterwards used to be signed. This may not be
     * null.
     * @param signerPrivateKey The {@link PrivateKey} of the participant who
     * signs. This may not be null.
     * @return The signature of the {@code data}.
     * @throws IllegalArgumentException If at least one argument is null.
     * @throws IllegalStateException If the key is invalid or another exception
     * happened during signing.
     */
    public byte[] sign(byte[] data, PrivateKey signerPrivateKey) {
        Exceptions.verifyArgumentsNotNull(data, signerPrivateKey);

        try {
            signature.initSign(signerPrivateKey);
            signature.update(data);
            return signature.sign();
        } catch (InvalidKeyException | SignatureException ex) {
            throw new IllegalStateException("An error occurred during signing data: " + ex.getMessage());
        }
    }

    /**
     * Verifies the given {@code data} using the {@code dataSignature} and the
     * {@link PublicKey}. The key has to be generated using
     * {@link EccKeyPairGenerator}.
     *
     * @param data The data to verify. This may not be null.
     * @param dataSignature The signature of the data, provided by the signer.
     * This may not be null.
     * @param signerPublicKey The {@link PublicKey} of the participant who has
     * signed the data. This may not be null.
     * @return true, if the data integrity can be verified, otherwise false.
     * @throws IllegalArgumentException If at least one argument is null.
     * @throws IllegalStateException If the key is invalid or another exception
     * happened during verifying.
     */
    public boolean verify(byte[] data, byte[] dataSignature, PublicKey signerPublicKey) {
        Exceptions.verifyArgumentsNotNull(data, dataSignature, signerPublicKey);

        try {
            signature.initVerify(signerPublicKey);
            signature.update(data);
            return signature.verify(dataSignature);
        } catch (InvalidKeyException | SignatureException ex) {
            throw new IllegalStateException("An error occured during verifying data: " + ex.getMessage());
        }
    }

}

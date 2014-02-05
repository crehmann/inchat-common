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

import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;
import static org.junit.Assert.*;

public class BouncyCastleIntegratorTest {

    @Test
    public void testInstatiation() {
        BouncyCastleIntegrator integrator = new BouncyCastleIntegrator();
    }

    @Test
    public void testProviderName() {
        assertEquals(BouncyCastleProvider.PROVIDER_NAME, BouncyCastleIntegrator.PROVIDER_NAME);
    }

    @Test
    public void testInitBouncyCasteProvider() {
        if (Security.getProvider(BouncyCastleIntegrator.PROVIDER_NAME) != null) {
            Security.removeProvider(BouncyCastleIntegrator.PROVIDER_NAME);
        }

        assertNull(Security.getProvider(BouncyCastleIntegrator.PROVIDER_NAME));
        BouncyCastleIntegrator.initBouncyCastleProvider();
        assertNotNull(Security.getProvider(BouncyCastleIntegrator.PROVIDER_NAME));
    }
}

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
package org.inchat.common;

import java.security.KeyPair;
import org.inchat.common.crypto.EccKeyPairGenerator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class ParticipantTest {

    private Participant participant;
    private KeyPair keyPair;

    @Before
    public void setUp() {
        keyPair = EccKeyPairGenerator.generate();
        participant = new Participant(keyPair);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructorOnNull() {
        participant = new Participant(null);
    }

    @Test
    public void testNullConstructorOnAssignment() {
        participant = new Participant(keyPair);
        assertSame(keyPair, participant.keyPair);
    }

    @Test
    public void testGetKeyPair() {
        participant.keyPair = keyPair;
        assertSame(keyPair, participant.getKeyPair());
    }

}

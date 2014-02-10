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
package org.inchat.common.util;

import org.junit.Test;

public class ExceptionsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testVerifyArgumentNotNullOnNull() {
        Exceptions.verifyArgumentNotNull(null);
    }

    @Test
    public void testVerifyArgumentNotNull() {
        Exceptions.verifyArgumentNotNull("this is not null -- no exception must be thrown");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerifyArgumentsNotNullOnNull() {
        Exceptions.verifyArgumentsNotNull(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerifyArgumentsNotNullOnNulls() {
        Exceptions.verifyArgumentsNotNull(new String[]{null, null});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVerifyArgumentsNotNullOnNotOnlyNulls() {
        Exceptions.verifyArgumentsNotNull(new String[]{"hello", null});
    }

    @Test
    public void testVerifyArgumentsNotNullOnNotEmptyArray() {
        Exceptions.verifyArgumentsNotNull(new String[]{});
    }

    @Test
    public void testVerifyArgumentsNotNul() {
        Exceptions.verifyArgumentsNotNull(new String[]{"hello"});
    }

}

package com.typingcat.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author huxin
 */
class NameUtilsTest {

    @Test
    void testGetLastWord() {
        assertEquals("", NameUtils.getLastWord(""));
        assertEquals("World", NameUtils.getLastWord("HelloWorld"));
        assertEquals("Hello", NameUtils.getLastWord("Hello"));
        assertEquals("World", NameUtils.getLastWord("Hello_World"));
        assertEquals("world", NameUtils.getLastWord("Hello_world"));
        assertEquals("and", NameUtils.getLastWord("Hello_world_and"));
        assertEquals("", NameUtils.getLastWord("Hello_world_"));
    }

    @Test
    void testIsCompositeName() {
        assertTrue(NameUtils.isCompositeName("hello_"));
        assertTrue(NameUtils.isCompositeName("hello_world"));
        assertTrue(NameUtils.isCompositeName("helloWorld"));
        assertFalse(NameUtils.isCompositeName("hello"));
        assertTrue(NameUtils.isCompositeName("_hello"));
    }
}
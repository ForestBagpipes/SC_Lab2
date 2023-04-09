/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO GraphPoet测试策略
    //     GraphPoet：
    //        1、输入文件为空
    //        2、输入文件存在且不为空
    //        3、输入文件不为空且全大写
    //     poem：
    //        1、输入的字符串为null
    //        2、输入的字符串不为null，但只有一个字符
    //        3、输入的字符串不为null，且不止一个字符
    //     toString：
    //        1、输入的graph不为空，但没有边
    //        2、输入的graph有点又有边
    //        3、输入的graph为空
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void testGraphPoet() throws IOException {
        final P1.poet.GraphPoet nimoy1 = new P1.poet.GraphPoet(new File("test/P1/poet/empty.txt"));
        final String input1 = "Just smile you, make whole bright.";
        assertEquals("Just smile you, make whole bright.",nimoy1.poem(input1));

        final P1.poet.GraphPoet nimoy2 = new P1.poet.GraphPoet(new File("test/P1/poet/poem.txt"));
        final String input2 = "Just smile you,";
        assertEquals("Just one smile from you,",nimoy2.poem(input2));

        final P1.poet.GraphPoet nimoy3 = new P1.poet.GraphPoet(new File("test/P1/poet/poem1.txt"));
        final String input3 = "Just smile you,";
        assertEquals("Just one smile from you,",nimoy3.poem(input3));
    }

    @Test
    public void testPoem() throws IOException {
        final P1.poet.GraphPoet nimoy1 = new P1.poet.GraphPoet(new File("test/P1/poet/empty.txt"));
        final String input1 = null;
        assertEquals(null,nimoy1.poem(input1));
        assertEquals(null,nimoy1.poem(""));

        final P1.poet.GraphPoet nimoy2 = new P1.poet.GraphPoet(new File("test/P1/poet/poem.txt"));
        final String input2 = "Just";
        assertEquals("Just",nimoy2.poem(input2));

        final P1.poet.GraphPoet nimoy3 = new P1.poet.GraphPoet(new File("test/P1/poet/poem.txt"));
        final String input3 = "Just smile you,";
        assertEquals("Just one smile from you,",nimoy3.poem(input3));
    }

    @Test
    public void testToString() throws IOException {
        final P1.poet.GraphPoet nimoy1 = new P1.poet.GraphPoet(new File("test/P1/poet/word.txt"));
        assertEquals("Vertices:" + "\r\n" + "just" + "\r\n" + "Edges:" + "\r\n",nimoy1.toString());
        final P1.poet.GraphPoet nimoy2 = new P1.poet.GraphPoet(new File("test/P1/poet/word1.txt"));
        assertEquals("Vertices:" + "\r\n"
                + "one" + "\r\n" + "just" + "\r\n" + "smile" + "\r\n"
                + "Edges:" + "\r\n"
                + "just" + "->" + "one" + "权值为：" + 1 + "\r\n"
                + "one" + "->" + "smile" + "权值为：" + 1 + "\r\n",nimoy2.toString());
        final P1.poet.GraphPoet nimoy3 = new P1.poet.GraphPoet(new File("test/P1/poet/empty.txt"));
        assertEquals("Vertices:" + "\r\n" + "\r\n"+ "Edges:" + "\r\n",nimoy3.toString());
    }
}

/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.File;
import java.io.IOException;

/**
 * Example program using GraphPoet.
 * 
 * <p>PS2 instructions: you are free to change this example class.
 */
public class Main {
    
    /**
     * Generate example poetry.
     * 
     * @param args unused
     * @throws IOException if a poet corpus file cannot be found or read
     */
    public static void main(String[] args) throws IOException {
        final P1.poet.GraphPoet nimoy = new P1.poet.GraphPoet(new File("src/P1/poet/mugar-omni-theater.txt"));
        final String input = "Test the system.";
        System.out.println(input + "\n>>>\n" + nimoy.poem(input));

        final P1.poet.GraphPoet nimoy1 = new P1.poet.GraphPoet(new File("src/P1/poet/myPoem.txt"));
        final String input1 = "Just smile you, make whole bright.";
        System.out.println(input1 + "\n>>>\n" + nimoy1.poem(input1));

        final P1.poet.GraphPoet nimoy2 = new P1.poet.GraphPoet(new File("src/P1/poet/myPoem.txt"));
        final String input2 = "Just smile you,";
        System.out.println(nimoy1.poem(input2));
    }
    
}

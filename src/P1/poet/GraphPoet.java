/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   TODO  graph：顶点为String类型的有向带权图
    // Representation invariant:
    //   TODO  graph：graph不为null
    // Safety from rep exposure:
    //   TODO  graph用 private和final限定修饰
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(corpus));
        String str ;
        StringBuilder sb = new StringBuilder();
        String[] strList;
        while((str = br.readLine())!=null){
            sb.append(str.toLowerCase()); //将所有的单词转换为小写
            sb.append(" ");//加入一个空格，即用空格替代换行符，方便后续分割单词
        }
        strList = sb.toString().split(" ");
        br.close();//关闭文件流
        //若输入的文件为空，直接结束方法
        if(strList.length == 0){
            System.out.println("输入文件为空");
            return;
        }
        this.graph.add(strList[0]);
        //将strList中的数据添加到图中
        for (int i = 0; i < strList.length-1; i++) {
            if (this.graph.targets(strList[i]).containsKey(strList[i+1])){
                //先删除，再添加
                this.graph.set(strList[i], strList[i+1], this.graph.set(strList[i], strList[i+1], 0)+1);
            }else {
                //如果不存在直接添加，设置权重为1
                this.graph.set(strList[i], strList[i+1], 1);
            }
        }
        checkRep();
    }
    
    // TODO checkRep
    private void checkRep(){
        assert this.graph != null : "graph不可为null";
    }
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        if(input == null || input == ""){
            return null;
        }
        StringBuilder res = new StringBuilder();
        String[] low = input.toLowerCase().split(" "); //保存小写的各单词，用于生成图
        String[] words = input.split(" "); //保存原来的各单词
        res.append(words[0]); //首先将第一个单词保存在结果字符串中
        for (int i = 1; i < low.length; i++) {
            //如果前后两个单词都在图中
            int max = 0;
            String bridge = "";
            if(this.graph.vertices().contains(low[i-1])&&this.graph.vertices().contains(low[i])){
                //寻找交集，从中找到最大权值的点作为bridge插入
                for(Map.Entry<String,Integer> entry : this.graph.targets(low[i-1]).entrySet()){
                    if(this.graph.sources(low[i]).containsKey(entry.getKey())){
                        int tmp = entry.getValue() + this.graph.sources(low[i]).get(entry.getKey());
                        if(tmp>max){
                            max = tmp;
                            bridge = entry.getKey();
                        }
                    }
                }
                res.append(" ");
                res.append(bridge);
            }
            res.append(" ");
            res.append(words[i]); // words[i-1]已经在图中了，只需添加words[i]即可
        }
        checkRep();
        return res.toString();
    }
    
    // TODO toString()
    public String toString(){
        return this.graph.toString();
    }
}

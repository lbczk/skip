import java.util.Random;

public class Skip{
    public Node root;
    public static double p=.5;
    public static Random randGen = new Random();
    public final int width;

    public Skip(int content, int width){
        this.width = width;
        this.root = new Node(content, null, width);
    } 

    public void add(int content){
        int target_level = this.gen_level();
        Node ins = new Node(content, null, target_level + 1);
        if(!root.search(content)){
        root.add(ins, width - 1, target_level);
        }
    }

    public void print(){
        root.print();
    }

    public boolean search(int content){
        return root.search(content);
    }

    public int gen_level(){
        int res = 0;
        while(Math.random() > 1 - p){
            res++;
        }
        return Math.min(res, this.width);
    }
}
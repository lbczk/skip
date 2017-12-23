import java.util.ArrayList;

class Node{
    public int content;
    public ArrayList<Node> following = new ArrayList<Node>();

    public Node(int content, ArrayList<Node> following, int level){
        this.content = content;
        if(following == null){
            following = new ArrayList<Node>();
        }
        this.following = following;
        for(int i=0; i < level; i++){
            this.following.add(null);
        }

    }

    public Node(int content, Node next_node){
        this.content = content;
        ArrayList<Node> f = new ArrayList<Node>();
        f.add(next_node);
        this.following = f;
    }

    public boolean search(int content, int level){
        if(this.content == content){
            return true;
        }
        Node n = following.get(level);
        // System.out.println("looking for=" + content +" cur=" + this.content + ", level == " + level);

        if(n != null && n.content <= content){
            // System.out.println(n.content);
            return n.search(content, level);
        }
        else if ((n == null && level > 0) || ( n!= null && n.content > content && level > 0)){
            return this.search(content, level - 1);
        }
        return false;
    }

    public boolean search(int content){
        return this.search(content, this.following.size() - 1);
    }

    public void add(Node ins, int cur_level, int target_level){
        if(this.content != ins.content){
        // System.out.println(ins.content + " " + cur_level + " " + target_level);
        Node n = following.get(cur_level);

        if(n != null && n.content < ins.content){
            // System.out.println("Sending to "  + n.content);
            n.add(ins, cur_level, target_level);
        }
        else if(n == null || n.content > ins.content){
            if(target_level >= cur_level){
            ins.following.set(cur_level, n);
            this.following.set(cur_level, ins);
            }
            if( cur_level > 0){
                this.add(ins, cur_level - 1, target_level);
            }
        }
        }
        
    }

    public void print(int i){
        System.out.print(content + " - ");
        if(following.get(i) != null){
        following.get(i).print(i);
        }
        else{
        System.out.println("\n");
        }
    }

    public void print(){
        for(int i=0; i < following.size() - 1; i++){
            if(following.get(i) != null){
            this.print(i);
            }
        }
    }
}
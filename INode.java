// The node class we use for the skip lists

import java.util.ArrayList;

class INode{
    private int content;
    // contains a list of pointers to other nodes in the list 
    // together with the respective "lengths" of these links
    private ArrayList<LNode> following = new ArrayList<LNode>();

    public INode(int content, ArrayList<LNode> following, int level){
        this.content = content;
        if(following == null){
            following = new ArrayList<LNode>();
        }
        this.following = following;
        for(int i=0; i < level; i++){
            this.following.add(new LNode(null, 1));
        }
    }

    public boolean search(int content, int level){
        if(this.content == content){
            return true;
        }
        LNode ln = following.get(level);
        // System.out.println("looking for=" + content +" cur=" + this.content + ", level == " + level);

        if(ln.getN() != null && ln.getN().content <= content){
            // System.out.println(n.content);
            return ln.getN().search(content, level);
        }
        else if ((ln.getN() == null && level > 0) ||
                (ln.getN() != null && ln.getN().content > content && level > 0)){
            return this.search(content, level - 1);
        }
        return false;
    }

    public boolean search(int content){
        return this.search(content, this.following.size() - 1);
    }

    public int add(INode ins, int cur_level, int target_level){
        if(this.content != ins.content){
        // System.out.println(ins.content + " " + cur_level + " " + target_level);
        LNode ln = following.get(cur_level);

        if(ln.getN() != null && ln.getN().content < ins.content){
            // We insert after the next element ln.getN()
            return ln.getLength() + ln.getN().add(ins, cur_level, target_level);
        }
        else if(ln.getN() == null || ln.getN().content > ins.content){
            if(target_level < cur_level){
                ln.setLength(ln.getLength() + 1); // this link grows by one because we are inserting below it.
            }            
            // 
            int offset = (cur_level > 0) ? this.add(ins, cur_level - 1, target_level): 1;
            if(target_level >= cur_level){
            ins.following.set(cur_level, ln);
            if(ln != null){
                // System.out.println("ln :" + ln.getLength() + " - " + offset);
                ln.setLength(1 + ln.getLength() - offset);}
            this.following.set(cur_level, new LNode(ins, offset));
                }
            return offset;  
            }
        }
        return -1;
    }


    public String add_spaces(int length, int i){
        if(i==0) return " - ";
        else{
            return " " + String.format("%0" + (5 * length - 4) + "d", 0).replace("0", "-") + " ";}
    }

    public void print(int i, boolean mode){
        LNode ln = following.get(i);
        if(ln != null){
            String u = (ln.getN() != null) ? String.valueOf(ln.getN().content): "o\n";
            if (!mode) System.out.print(u + "," + ln.getLength() + " - ");
            else {System.out.print(add_spaces(ln.getLength(), i) + u);}
            if(ln.getN() != null){ln.getN().print(i, mode);}
        }
        else{System.out.println(" - o \n");}
    }

    public void print(boolean mode){
        for(int i=following.size() - 1; i >= 0; i--){
            if(following.get(i).getN() != null) {
                System.out.print("o");
                this.print(i, mode);
            }
        }
    }
    
}
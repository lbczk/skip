public class Test{
    public static void main(String[] args){
    Skip s = new Skip(3, 12);

    s.add(6);
    s.add(5);
    s.add(5);
    s.add(5);
    s.add(5);
    s.add(8);
    s.add(1);
    s.add(2);

    Boolean b = s.search(2) && s.search(5) && s.search(6) && s.search(8);
    b = b && !s.search(4);
    String result = b? "All results OK\n": "Test failure\n";
    System.out.println(result);

    s.print();



    }

}
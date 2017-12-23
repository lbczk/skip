public class Test{
    public static void main(String[] args){
    ISkip s2 = new ISkip(-1, 25);
    s2.add(41);
    s2.add(12);
    s2.add(20);
    s2.add(30);
    s2.add(40);
    for(int i = 0; i<20; i++){
        s2.add(50 + 2 * i + 1);
    }
    Boolean b = s2.search(41) && s2.search(12) && s2.search(40) && s2.search(30);
    b = b && !s2.search(100);
    System.out.println(b? "All tests OK":"TEST FAILURE");
    System.out.println(s2.search(63)? "\n":"63 absent\n");
    s2.print(true);

    Boolean c = (69 == s2.get(15)) & (79 == s2.get(20));
    System.out.println(c? "\n": "FAILURE IN GET");
    }

}
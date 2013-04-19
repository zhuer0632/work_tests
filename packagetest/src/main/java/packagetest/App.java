package packagetest;

/**
 * Hello world!
 */
public class App
{


    public static void main(String[] args)
    {

        String s = App.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (s.endsWith(".jar"))
        {
            s = s.substring(0, s.lastIndexOf("/"));
        }

        System.out.println(s);

        System.out.println("Hello World!");



    }


}

package test02;

import java.io.BufferedReader;
import java.io.StringReader;

/**
 * Hello world!
 */
public class App
{


    public static void main(String[] args) throws Exception
    {


        String s = "{\n" +
                "  username_:'admin',\n" +
                "  userpassword_:'123456789'\n" +
                "}";




        //////////////////////////////////
        BufferedReader reader = new BufferedReader(new StringReader(s));
        StringBuilder stringBuilder=new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null)
        {
            stringBuilder.append("\""+line+"\"+  \r\n");
        }

        String out=stringBuilder.substring(0,stringBuilder.length()-5);

        System.out.println(out);

    }


}

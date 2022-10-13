/*SERVER CLASS*/
import java.net.*;
import java.util.Scanner;
import java.io.*;
public class server
{
    public static void main(String args[])throws IOException
    {
        int port = 3333;
        String file = "essai.txt";
        if (args.length == 2){
            // we got the port and the filename
            port = Integer.parseInt(args[0]);
            file = args[1];
        }
        System.out.println("Ouverture de la connexion sur le port : " + port +".\nLecture dans le ecriture dans le fichier: " + file);
        byte b[]=new byte[66000];
        DatagramSocket dsoc=new DatagramSocket(port);
        FileOutputStream f=new FileOutputStream(file);
        while(true)
        {
            DatagramPacket dp=new DatagramPacket(b,b.length);
            dsoc.receive(dp);
            System.out.println(dp.getLength());                             

        }
    }
}


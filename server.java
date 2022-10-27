/*SERVER CLASS*/
import java.net.*;
import java.io.*;
public class server
{
    public static void main(String args[])throws IOException
    {
        int port = 3333;
        String filename = "essai.txt";
        if (args.length == 2){
            // we got the port and the filename
            port = Integer.parseInt(args[0]);
            filename = args[1];
        }
        System.out.println("Ouverture de la connexion sur le port : " + port +".\nEcriture dans le fichier: " + filename);
        byte data[]=new byte[66000];
        DatagramSocket dsoc=new DatagramSocket(port);
        File file = new File(filename);
        FileOutputStream fop=new FileOutputStream(file);
        while(true)
        {
            DatagramPacket dp=new DatagramPacket(data, data.length);
            dsoc.receive(dp);
            fop.write(dp.getData(), 0, dp.getLength());
            System.out.println(dp.getLength());   
        }
        
    }
}


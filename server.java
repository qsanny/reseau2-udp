/*SERVER CLASS*/
import java.net.*;
import java.io.*;
import java.math.BigInteger;
public class server
{
    public static void main(String args[])throws IOException
    {
        int port = 3333;
        String filename = "received.txt";
        if (args.length == 2){
            // we got the port and the filename
            port = Integer.parseInt(args[0]);
            filename = args[1];
        }
        System.out.println("Ouverture de la connexion UDP sur le port : " + port +".\nEcriture dans le fichier: " + filename);
        byte data[]=new byte[66000];
        DatagramSocket dsoc=new DatagramSocket(port);
        File file = new File(filename);
        FileOutputStream fop=new FileOutputStream(file);
        while(true)
        {
            DatagramPacket dp=new DatagramPacket(data, data.length);
            dsoc.receive(dp);
            byte[] npPcketb = new byte[4];
            npPcketb[0] = dp.getData()[0];
            npPcketb[1] = dp.getData()[1];
            npPcketb[2] = dp.getData()[2];
            npPcketb[3] = dp.getData()[3];
            int value = new BigInteger(npPcketb).intValue();
            System.out.println(value);
            fop.write(String.valueOf(value).getBytes());
            fop.write(dp.getData(), 4, dp.getLength());
            // System.out.println(dp.getLength());   
        }
        
    }
}


/*CLIENT CLASS*/
import java.net.*;
import java.io.*;
public class client
{
    public static void main(String args[])throws Exception
    {    
        int serveurPort = 3333;
        InetAddress serverAddress = InetAddress.getLocalHost();
        String file = "test.txt";
        int size = 135000;

        if (args.length == 1){
            size = Integer.parseInt(args[0]);
        }
        if (args.length == 3){
            // we got the port and the filename
            serverAddress = InetAddress.getByName(args[1]);
            serveurPort = Integer.parseInt(args[1]);
            file = args[2];
        }

        byte b[]=new byte[size];
        FileInputStream fop=new FileInputStream(file);
        DatagramSocket dsoc=new DatagramSocket(3331);
        int i=0;
        while(fop.available()!=0)
        {
            b[i]=(byte)fop.read();
            i++;
        }                     
        fop.close();
        dsoc.send(new DatagramPacket(b, i, serverAddress, serveurPort));
    }
}


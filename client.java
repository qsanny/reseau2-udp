/*CLIENT CLASS*/
import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;
public class client
{
    public static void main(String args[])throws Exception
    {    
        int serveurPort = 3333;
        InetAddress serverAddress = InetAddress.getLocalHost();
        String file = "tosend.txt";
        int MAX_SIZE = 135000;

        int size = 0;

        if (args.length == 1){
            size = Integer.parseInt(args[0]);
        }
        if (args.length == 3){
            // we got the port and the filename
            serverAddress = InetAddress.getByName(args[1]);
            serveurPort = Integer.parseInt(args[1]);
            file = args[2];
        }

        byte b[]=new byte[MAX_SIZE];
        FileInputStream fop=new FileInputStream(file);
        DatagramSocket dsoc=new DatagramSocket(3331);
        int i=4;
        int nbPacket = 1;
        while(fop.available()!=0)
        {
            b[i]=(byte)fop.read();
            i++;
            if (i % size == 0) {
                // String l = "";
                // l+=nbPacket;
                ByteBuffer pb = ByteBuffer.allocate(4);
                pb.putInt(nbPacket);
                b[0] =  pb.get(0);
                b[1] =  pb.get(1);
                b[2] =  pb.get(2);
                b[3] =  pb.get(3);
                // send this chunk of packet
                dsoc.send(new DatagramPacket(b, i, serverAddress, serveurPort));
                i=4;
                nbPacket++;
            };
        }
        fop.close();
    }
}


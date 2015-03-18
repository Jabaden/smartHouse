/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

/**
 *
 * @author Andy
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Andy
 */
public class Lights extends Thread
{
    static boolean infinite = true;
    int portNumber;
    ServerSocket sSocket;
    boolean kitchenLights = false;
    boolean porchLights = false;
    boolean bedroomLights = false;
    boolean diningRoomLights = false;
    boolean backyardLights = false;
    String msg1;
    String msg2;
    String msg3;
    String msg4;
    String msg5;
    String msg6;
    
    
    public Lights(int portNumber) throws Exception
    {
        super();
        this.portNumber = portNumber;
        sSocket = new ServerSocket(portNumber);
    }
    
    public void sendReply(String host, int port, String message)
    {
        try
        (
            Socket sender = new Socket(host, port);
            PrintWriter outboundTwo = new PrintWriter(sender.getOutputStream(), true);
        )
        {
        outboundTwo.println(message);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        while(infinite)
        {
            try
            (
                    Socket tempSocket = sSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(tempSocket.getInputStream()));
            )
            {
                String incInput;
                int index = User.ports.indexOf("Server");
                while ((incInput = in.readLine()) != null) 
                {
                    /*//device
                    String action = incInput.substring(0, incInput.indexOf(" "));
                    //value maybe optional
                    String value =  incInput.substring(incInput.indexOf(" ") + 1);
                    //String dProperty = remainder.substring(0, remainder.indexOf(" "));
                    //String content = remainder.substring(remainder.indexOf(" ") + 1);*/
                   String[] msg = incInput.split(" ");
                   System.out.println("recieveing message: " + incInput);
                   msg1 = msg[0];
                   if(msg.length > 1)
                   {
                        msg2 = msg[1];
                        if(msg.length > 2)
                        {
                            msg3 = msg[2];
                            if(msg.length > 3)
                            {
                                msg4 = msg[3];
                                if(msg.length > 4)
                                {
                                    msg5 = msg[4];
                                }
                                if(msg.length > 5)
                                {
                                    msg6 = msg[5];
                                }
                            }
                        }
                    }
                    if(msg2.equalsIgnoreCase("kitchen"))
                    {
                        if(msg3.equalsIgnoreCase("on"))
                        {
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                        }
                        else if(msg3.equalsIgnoreCase("off"))
                        {
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                        }
                    }
                    else if(msg2.equalsIgnoreCase("porch"))
                    {
                        if(msg3.equalsIgnoreCase("off"))
                        {
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                        }
                        else if(msg3.equalsIgnoreCase("on"))
                        {
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                        }
                        
                    }
                    else if(msg2.equalsIgnoreCase("bedroom"))
                    {
                        if(msg3.equalsIgnoreCase("off"))
                        {
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                        }
                        else if(msg3.equalsIgnoreCase("on"))
                        {
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                        }
                    }
                    else if(msg2.equalsIgnoreCase("dining"))
                    {
                        if(msg3.equalsIgnoreCase("off"))
                        {
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                        }
                        else if(msg3.equalsIgnoreCase("on"))
                        {
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                        }
                    }
                    else if(msg2.equalsIgnoreCase("backyard"))
                    {
                        if(msg3.equalsIgnoreCase("off"))
                        {
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                        }
                        else if(msg3.equalsIgnoreCase("on"))
                        {
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                        }
                    }
                }
            }
            catch (SocketTimeoutException e) 
            {
                
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
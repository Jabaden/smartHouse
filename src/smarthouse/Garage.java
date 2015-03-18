/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;
//TODO SEND STATUS TO SERVER
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
public class Garage extends Thread
{
    static boolean infinite = true;
    int portNumber;
    ServerSocket sSocket;
    boolean armed;
    boolean isOpen = false;
    String msg1;
    String msg2;
    String msg3;
    String msg4;
    String msg5;
    String msg6;
    public Garage(int portNumber) throws Exception
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
                int index = User.appliances.indexOf("Server");
                while ((incInput = in.readLine()) != null) 
                {
                    System.out.println("in while loop of garage");
                    System.out.println(incInput);
                    
                    String[] msg = incInput.split(" ");
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
                    /*//device
                    String action = incInput.substring(0, incInput.indexOf(" "));
                    //value maybe optional
                    String value =  incInput.substring(incInput.indexOf(" ") + 1);
                    //String dProperty = remainder.substring(0, remainder.indexOf(" "));
                    //String content = remainder.substring(remainder.indexOf(" ") + 1); */
                    if(msg2.equalsIgnoreCase("door"))
                    {
                        if(msg3.equalsIgnoreCase("open"))
                        {
                            System.out.println("Garage door is open");
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                                    
                        }
                        else if(msg3.equalsIgnoreCase("close"))
                        {
                            System.out.println("garage is closed");
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                            User.garageStatus = false;
                        }
                        if(msg3.equalsIgnoreCase("status"))
                        {
                             if(isOpen == true)
                            {
                                //door is open
                                sendReply("127.0.0.1", User.ports.get(index) , "true");
                            }
                            else if(isOpen == false)
                            {
                                //door is close
                                sendReply("127.0.0.1", User.ports.get(index) , "false");
                            }
                        }
                    }
                      
                }
            }
            catch (SocketTimeoutException e) 
            {
                System.out.println("socket timeout");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
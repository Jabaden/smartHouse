/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Andy
 */
//TODO WHEN THE APPLAINCES TALK TO THE SERVER BACK
public class HouseServer extends Thread 
{
    static boolean infinite = true;
    int portNum;
    ServerSocket sSocket;
    String host = "127.0.0.1";
    int garagePort;
    int securityPort;
    int lightPort;
    int heaterPort;
    int userPort;
    int garageIndex;
    String msg1;
    String msg2;
    String msg3;
    String msg4;
    String msg5;
    String msg6;
    String msg7;
    boolean ack;
    Boolean isTimer = false;
    
    public HouseServer(int portNum) throws Exception
    {
        super();
        this.portNum = portNum;
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
            System.out.println("Waiting to accept commands");
            userPort = User.appliances.indexOf("User");
            try
            (
                //creating the socket for the server
                ServerSocket sSocket = new ServerSocket(portNum);
                Socket tSocket = sSocket.accept();
                //waiting for a command
                BufferedReader in = new BufferedReader(new InputStreamReader(tSocket.getInputStream()));
            )
            {
                //here is where the server parses the user's command
                //the format for reading the messages is DEVICE REQUESTED, DEVICE PROPERTY, COMMAND
                String incInput;
                while ((incInput = in.readLine()) != null) 
                {
                    isTimer = false;
                    String[] msg = incInput.split(" ");
                    System.out.println(incInput);
                    msg1 = msg[0];
                    System.out.println("msg1 is " + msg1);
                    if(msg.length > 1)
                    {
                        msg2 = msg[1];
                        System.out.println("msg2 is " + msg2);
                        if(msg2.equalsIgnoreCase("ACK"))
                        {
                            System.out.println("if one");
                            ack = true;
                        }
                        if(msg.length > 2)
                        {
                            
                            msg3 = msg[2];
                            System.out.println("msg3 is " + msg3);
                            if(msg3.equalsIgnoreCase("ACK"))
                            {
                                System.out.println("if two");
                                ack = true;
                            }
                            if(msg.length > 3)
                            {
                                msg4 = msg[3];
                                if(msg4.equalsIgnoreCase("ACK"))
                                {
                                    System.out.println("if three");
                                    ack = true;
                                }
                                System.out.println("msg4 is " + msg4);
                                if(msg.length > 4)
                                {
                                    System.out.println("msg5 is " + msg5);
                                    msg5 = msg[4];
                                    if(msg5.equalsIgnoreCase("ACK"))
                                    {
                                        System.out.println("if four");
                                        ack = true;
                                    }
                                }
                                if(msg.length > 5)
                                {
                                    msg6 = msg[5];
                                    if(msg6.equalsIgnoreCase("ACK"))
                                    {
                                        System.out.println("if five");
                                        ack = true;
                                    }
                                }
                                if(msg.length > 6)
                                {
                                    ack = true;
                                    msg7 = msg[6];
                                }
                            }
                        }
                    }
                    /*String device = incInput.substring(0, incInput.indexOf(" "));
                    String remainder = incInput.substring(incInput.indexOf(" ") + 1);
                    String dProperty = remainder.substring(0, remainder.indexOf(" "));
                    String content = remainder.substring(remainder.indexOf(" ") + 1);*/
                    if(msg1.equalsIgnoreCase("test"))
                    {
                        int userPort = User.ports.indexOf("User");
                        System.out.println("test note recieved");
                    }
                    else if(msg1.equalsIgnoreCase("garage"))
                    {
                        garagePort = User.appliances.indexOf("Garage");
                        if(msg2.equalsIgnoreCase("door"))
                        {
                            if(msg3.equalsIgnoreCase("open"))
                            {
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the garage");
                                    sendReply("127.0.0.1", User.ports.get(garagePort), incInput);
                                }
                                
                            }
                            else if(msg3.equalsIgnoreCase("close"))
                            {
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the garage");
                                    sendReply("127.0.0.1", User.ports.get(garagePort), incInput);
                                }
                            }
                            //else
                            //{
                              //  sendReply("127.0.0.1", User.ports.get(garagePort), );
                            //}
                        }
                    }
                    else if(msg1.equalsIgnoreCase("Security"))
                    {
                        securityPort = User.appliances.indexOf("Security");
                        if(msg2.equalsIgnoreCase("alarm"))
                        {
                            if(msg3.equalsIgnoreCase("on"))
                            {
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the alarm");
                                    sendReply("127.0.0.1", User.ports.get(securityPort), incInput);
                                }
                            }
                            else if(msg3.equalsIgnoreCase("off"))
                            {
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the alarm");
                                    sendReply("127.0.0.1", User.ports.get(securityPort), incInput);
                                }
                            }
                            /*else
                            {
                                sendReply("127.0.0.1", User.ports.get(securityPort), device + " " + "error");
                            }*/
                        }
                    }
                    else if(msg1.equalsIgnoreCase("Lights"))
                    {
                        lightPort = User.appliances.indexOf("Lights");
                        if(msg2.equalsIgnoreCase("kitchen"))
                        {
                            if(msg3.equalsIgnoreCase("on"))
                            {
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the garage");
                                    sendReply("127.0.0.1", User.ports.get(lightPort), incInput);
                                }
                            }
                            else if(msg3.equalsIgnoreCase("off"))
                            {
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the garage");
                                    sendReply("127.0.0.1", User.ports.get(lightPort), incInput);
                                }
                            }
                            
                        }
                        else if(msg2.equalsIgnoreCase("porch"))
                        {
                            if(msg3.equalsIgnoreCase("on"))
                            {
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the lights");
                                    sendReply("127.0.0.1", User.ports.get(lightPort), incInput);
                                }
                            }
                            else if(msg3.equalsIgnoreCase("off"))
                            {
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the lights");
                                    sendReply("127.0.0.1", User.ports.get(lightPort), incInput);
                                }
                            }
                        }
                        else if(msg2.equalsIgnoreCase("bedroom"))
                        {
                            if(msg3.equalsIgnoreCase("on"))
                            {
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the garage");
                                    sendReply("127.0.0.1", User.ports.get(lightPort), incInput);
                                }
                            }
                            else if(msg3.equalsIgnoreCase("off"))
                            {
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the garage");
                                    sendReply("127.0.0.1", User.ports.get(lightPort), incInput);
                                }
                            }
                        }
                        else if(msg2.equalsIgnoreCase("dining"))
                        {
                            if(msg3.equalsIgnoreCase("on"))
                            {
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the light");
                                    sendReply("127.0.0.1", User.ports.get(lightPort), incInput);
                                }
                            }
                            else if(msg3.equalsIgnoreCase("off"))
                            {
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the lights");
                                    sendReply("127.0.0.1", User.ports.get(lightPort), incInput);
                                }
                            }
                        }
                    }
                    else if(msg1.equalsIgnoreCase("Heater"))
                    {
                        heaterPort = User.appliances.indexOf("Heater");
                        if(msg2.equalsIgnoreCase("heat"))
                        {
                            
                                System.out.println("ack is " + ack);
                                if(ack == true)
                                {
                                    System.out.println("recieved reply");
                                    sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                    ack = false;
                                }
                                else
                                {
                                    System.out.println("sending to the heater");
                                    sendReply("127.0.0.1", User.ports.get(heaterPort), incInput);
                                }
                        }
                        else if(msg2.equalsIgnoreCase("cool"))
                        {
                            System.out.println("ack is " + ack);
                            if(ack == true)
                            {
                                System.out.println("recieved reply");
                                sendReply("127.0.0.1", User.ports.get(userPort), incInput);
                                ack = false;
                            }
                            else
                            {
                                System.out.println("sending to the heater");
                                sendReply("127.0.0.1", User.ports.get(heaterPort), incInput);
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.exit(1);
            }
        }
            
    }
}


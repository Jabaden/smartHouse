/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Andy
 */
public class UserThread extends Thread 
{
    static boolean infinite = true;
    int portNumber;
    ServerSocket sSocket;
    String msg1;
    String msg2;
    String msg3;
    String msg4;
    String msg5;
    String msg6;
    int lightPos;
    
    
    public UserThread(int portNumber) throws Exception
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
        while(infinite) //infinite loop
       {
            System.out.println("in while loop of user");
           try 
           (    //creates a socket
               Socket tempSocket = sSocket.accept();
                   //pauses here until it recieves a message
               BufferedReader in = new BufferedReader(
                new InputStreamReader(tempSocket.getInputStream()));
           )
           {
               String inputLine;
               while ((inputLine = in.readLine()) != null) 
               {
                    /*String type = inputLine.substring(0, inputLine.indexOf(" "));
                    // type is the type of message being sent
                    String remainder = inputLine.substring(inputLine.indexOf(" ") + 1);
                    String applianceName = remainder.substring(0, remainder.indexOf(" "));
                    String content = remainder.substring(remainder.indexOf(" ") + 1);
                    //content is either the status or a messsage being sent*/
                   String[] msg = inputLine.split(" ");
                   System.out.println("recieveing message: " + inputLine);
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
                    if(msg1.equalsIgnoreCase("Garage"))
                    {
                        System.out.println("msg1 = garage");
                        if(msg2.equalsIgnoreCase("door"))
                        {
                            System.out.println("msg2 equals door");
                            if(msg3.equalsIgnoreCase("open"))
                            {
                                System.out.println("garage status is " + User.garageStatus);
                                System.out.println("garage door is open!");
                                User.garageStatus = true;
                                System.out.println("garage status is " + User.garageStatus);
                            }
                            if(msg3.equalsIgnoreCase("close"))
                            {
                                User.garageStatus = false;
                                System.out.println("garage status is " + User.garageStatus);
                            }
                        }
                    }
                    else if(msg1.equalsIgnoreCase("Lights"))
                    {
                        if(msg2.equalsIgnoreCase("kitchen"))
                        {
                            if(msg3.equalsIgnoreCase("on"))
                            {
                                lightPos = User.lightArray.indexOf("kitchen");
                                User.lightStatus[lightPos] = true;
                            }
                            else if(msg3.equalsIgnoreCase("off"))
                            {
                                lightPos = User.lightArray.indexOf("kitchen");
                                User.lightStatus[lightPos] = false;
                            }
                        }
                        else if(msg2.equalsIgnoreCase("porch"))
                        {
                            if(msg3.equalsIgnoreCase("on"))
                            {
                                lightPos = User.lightArray.indexOf("porch");
                                User.lightStatus[lightPos] = true;
                            }
                            else if(msg3.equalsIgnoreCase("off"))
                            {
                                lightPos = User.lightArray.indexOf("porch");
                                User.lightStatus[lightPos] = false;
                            }
                        }
                        else if(msg2.equalsIgnoreCase("bedroom"))
                        {
                            if(msg3.equalsIgnoreCase("on"))
                            {
                                lightPos = User.lightArray.indexOf("bedroom");
                                User.lightStatus[lightPos] = true;
                            }
                            else if(msg3.equalsIgnoreCase("off"))
                            {
                                lightPos = User.lightArray.indexOf("bedroom");
                                User.lightStatus[lightPos] = false;
                            }
                        }
                        else if(msg2.equalsIgnoreCase("dining"))
                        {
                            if(msg3.equalsIgnoreCase("on"))
                            {
                                lightPos = User.lightArray.indexOf("dining");
                                User.lightStatus[lightPos] = true;
                            }
                            else if(msg3.equalsIgnoreCase("off"))
                            {
                                lightPos = User.lightArray.indexOf("dining");
                                User.lightStatus[lightPos] = false;
                            }
                        }
                        else if(msg2.equalsIgnoreCase("backyard"))
                        {
                            if(msg3.equalsIgnoreCase("on"))
                            {
                                lightPos = User.lightArray.indexOf("backyard");
                                User.lightStatus[lightPos] = true;
                            }
                            else if(msg3.equalsIgnoreCase("off"))
                            {
                                lightPos = User.lightArray.indexOf("backyard");
                                User.lightStatus[lightPos] = false;
                            }
                        }
                    }
                    else if(msg1.equalsIgnoreCase("Security"))
                    {
                        if(msg2.equalsIgnoreCase("on"))
                        {
                            User.securityStatus = true;
                        }
                        else if(msg2.equalsIgnoreCase("off"))
                        {
                            User.securityStatus = false;
                        }
                    }
                    else if(msg1.equalsIgnoreCase("Heater"))
                    {
                        if(msg2.equalsIgnoreCase("heat"))
                        {
                             User.temperature += Integer.parseInt(msg3);
                        }
                        else if(msg3.equalsIgnoreCase("cool"))
                        {
                            User.temperature -= Integer.parseInt(msg3);
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

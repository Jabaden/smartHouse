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
                    String device = incInput.substring(0, incInput.indexOf(" "));
                    String remainder = incInput.substring(incInput.indexOf(" ") + 1);
                    String dProperty = remainder.substring(0, remainder.indexOf(" "));
                    String content = remainder.substring(remainder.indexOf(" ") + 1);
                    if(device.equalsIgnoreCase("garage"))
                    {
                        garagePort = User.ports.indexOf("Garage");
                        if(dProperty.equalsIgnoreCase("door"))
                        {
                            if(content.equalsIgnoreCase("up"))
                            {
                                sendReply("127.0.0.1", User.ports.get(garagePort), dProperty + " " + content);
                            }
                            else if(content.equalsIgnoreCase("down"))
                            {
                                sendReply("127.0.0.1", User.ports.get(garagePort), dProperty + " " + content);
                            }
                            else
                            {
                                sendReply("127.0.0.1", User.ports.get(garagePort), device + " " +  "error");
                            }
                        }
                    }
                    else if(device.equalsIgnoreCase("Security"))
                    {
                        securityPort = User.ports.indexOf("Security");
                        if(dProperty.equalsIgnoreCase("alarm"))
                        {
                            if(content.equalsIgnoreCase("on"))
                            {
                                sendReply("127.0.0.1", User.ports.get(securityPort), dProperty + " " + content);
                            }
                            else if(content.equalsIgnoreCase("off"))
                            {
                                sendReply("127.0.0.1", User.ports.get(securityPort), dProperty + " " + content);
                            }
                            else
                            {
                                sendReply("127.0.0.1", User.ports.get(securityPort), device + " " + "error");
                            }
                        }
                    }
                    else if(device.equalsIgnoreCase("Lights"))
                    {
                        lightPort = User.ports.indexOf("Lights");
                        if(dProperty.equalsIgnoreCase("kitchen"))
                        {
                            if(content.equalsIgnoreCase("on"))
                            {
                                sendReply("127.0.0.1", User.ports.get(lightPort), dProperty + " " + content);
                            }
                            else if(content.equalsIgnoreCase("off"))
                            {
                                sendReply("127.0.0.1", User.ports.get(lightPort), dProperty + " " + content);
                            }
                            else
                            {
                                sendReply("127.0.0.1", User.ports.get(lightPort), device + " " + "error");
                            }
                        }
                        else if(dProperty.equalsIgnoreCase("porch"))
                        {
                            if(content.equalsIgnoreCase("on"))
                            {
                                sendReply("127.0.0.1", User.ports.get(lightPort), dProperty + " " + content);
                            }
                            else if(content.equalsIgnoreCase("off"))
                            {
                                sendReply("127.0.0.1", User.ports.get(lightPort), dProperty + " " + content);
                            }
                            else
                            {
                                sendReply("127.0.0.1", User.ports.get(lightPort), device + " " + "error");
                            }
                        }
                        else if(dProperty.equalsIgnoreCase("bedroom"))
                        {
                            if(content.equalsIgnoreCase("on"))
                            {
                                sendReply("127.0.0.1", User.ports.get(lightPort), dProperty + " " + content);
                            }
                            else if(content.equalsIgnoreCase("off"))
                            {
                                sendReply("127.0.0.1", User.ports.get(lightPort), dProperty + " " + content);
                            }
                            else
                            {
                                sendReply("127.0.0.1", User.ports.get(lightPort), device + " " + "error");
                            }
                        }
                        else if(dProperty.equalsIgnoreCase("dining"))
                        {
                            if(content.equalsIgnoreCase("on"))
                            {
                                sendReply("127.0.0.1", User.ports.get(lightPort), dProperty + " " + content);
                            }
                            else if(content.equalsIgnoreCase("off"))
                            {
                                sendReply("127.0.0.1", User.ports.get(lightPort), dProperty + " " + content);
                            }
                            else
                            {
                                sendReply("127.0.0.1", User.ports.get(lightPort), device + " " + "error");
                            }
                        }
                    }
                    else if(device.equalsIgnoreCase("Heater"))
                    {
                        heaterPort = User.ports.indexOf("Heater");
                        if(dProperty.equalsIgnoreCase("cool"))
                        {
                            sendReply("127.0.0.1", User.ports.get(heaterPort), dProperty + " " + content);
                        }
                        else if(content.equalsIgnoreCase("heat"))
                        {
                            sendReply("127.0.0.1", User.ports.get(heaterPort), dProperty + " " + content);
                        }
                        else
                        {
                            sendReply("127.0.0.1", User.ports.get(heaterPort), device + " " + "error");
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

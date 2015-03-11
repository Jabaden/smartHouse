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
                    //device
                    String action = incInput.substring(0, incInput.indexOf(" "));
                    //value maybe optional
                    String value =  incInput.substring(incInput.indexOf(" ") + 1);
                    //String dProperty = remainder.substring(0, remainder.indexOf(" "));
                    //String content = remainder.substring(remainder.indexOf(" ") + 1);
                    if(action.equalsIgnoreCase("kitchen"))
                    {
                        if(value.equalsIgnoreCase("on"))
                        {
                            kitchenLights = true;
                        }
                        else if(value.equalsIgnoreCase("off"))
                        {
                            kitchenLights = false;
                        }
                    }
                    else if(action.equalsIgnoreCase("porch"))
                    {
                        if(value.equalsIgnoreCase("off"))
                        {
                            porchLights = false;
                        }
                        else if(value.equalsIgnoreCase("on"))
                        {
                            porchLights = true;
                        }
                        
                    }
                    else if(action.equalsIgnoreCase("bedroom"))
                    {
                        if(value.equalsIgnoreCase("off"))
                        {
                            bedroomLights = false;
                        }
                        else if(value.equalsIgnoreCase("on"))
                        {
                            bedroomLights = true;
                        }
                    }
                    else if(action.equalsIgnoreCase("dining"))
                    {
                        if(value.equalsIgnoreCase("off"))
                        {
                            diningRoomLights = false;
                        }
                        else if(value.equalsIgnoreCase("on"))
                        {
                            diningRoomLights = true;
                        }
                    }
                    else if(action.equalsIgnoreCase("backyard"))
                    {
                        if(value.equalsIgnoreCase("off"))
                        {
                            backyardLights = false;
                        }
                        else if(value.equalsIgnoreCase("on"))
                        {
                            backyardLights = true;
                        }
                    }
                    else if(action.equalsIgnoreCase("status"))
                    {
                        //TODO WRITE CODE TO DISPLAY STATUS OF EVERYTHING
                        //MAY HAVE TO HAVE LIGHTS IN AN ARRAY TO PULL FROM
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
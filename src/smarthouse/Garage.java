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
public class Garage extends Thread
{
    static boolean infinite = true;
    int portNumber;
    ServerSocket sSocket;
    boolean armed;
    boolean isOpen = false;
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
                int index = User.ports.indexOf("Server");
                while ((incInput = in.readLine()) != null) 
                {
                    //device
                    String action = incInput.substring(0, incInput.indexOf(" "));
                    //value maybe optional
                    String value =  incInput.substring(incInput.indexOf(" ") + 1);
                    //String dProperty = remainder.substring(0, remainder.indexOf(" "));
                    //String content = remainder.substring(remainder.indexOf(" ") + 1);
                    if(action.equalsIgnoreCase("open"))
                    {
                        isOpen = true;
                    }
                    else if(action.equalsIgnoreCase("close"))
                    {
                        isOpen = false;
                    }
                    else if(action.equalsIgnoreCase("status"))
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
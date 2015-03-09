/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//THE THREAD FOR APPLIANCES WILL WORK FOR ALL APPLIANCES
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
public class Security extends Thread
{
    static boolean infinite = true;
    int portNumber;
    ServerSocket sSocket;
    boolean armed;
    
    public Security(int portNumber) throws Exception
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
        //"name" is the person who just logged on
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
                    String device = incInput.substring(0, incInput.indexOf(" "));
                    String action =  incInput.substring(incInput.indexOf(" ") + 1);
                    //String dProperty = remainder.substring(0, remainder.indexOf(" "));
                    //String content = remainder.substring(remainder.indexOf(" ") + 1);
                    if(device.equalsIgnoreCase("Alarm"))
                    {
                        if(action.equalsIgnoreCase("on"))
                        {
                            armed = true;
                        }
                        else if(action.equalsIgnoreCase("off"))
                        {
                            armed = false;
                        }
                        else if(action.equalsIgnoreCase("status"))
                        {
                            if(armed == true)
                            {
                                sendReply("127.0.0.1", index, "true");
                            }
                            else
                            {
                                sendReply("127.0.0.1", index, "false");
                            }
                        }
                        else
                        {
                            sendReply("127.0.0.1", index, "error");
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

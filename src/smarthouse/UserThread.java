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
                    String type = inputLine.substring(0, inputLine.indexOf(" "));
                    // type is the type of message being sent
                    String remainder = inputLine.substring(inputLine.indexOf(" ") + 1);
                    String applianceName = remainder.substring(0, remainder.indexOf(" "));
                    String content = remainder.substring(remainder.indexOf(" ") + 1);
                    //content is either the status or a messsage being sent
                    if(type.equals("Chat"))
                    {
                        System.out.println(applianceName + ": " + content);
                    }
                        //int index = User.appliances.indexOf(applianceName);
                    else 
                    {
                        //TODO depending on what message you get from the server
                        //do stuff here
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

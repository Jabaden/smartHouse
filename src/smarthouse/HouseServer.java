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
public class HouseServer extends Thread 
{
    static boolean infinite = true;
    int portNum;
    ServerSocket sSocket;
    String host = "127.0.0.1";
    
    public HouseServer(int portNum) throws Exception
    {
        super();
        this.portNum = portNum;
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
                    if(incInput.equalsIgnoreCase("help"))
                    {
                        System.out.println("help test");
                        return;
                    }
                    else if(incInput.equalsIgnoreCase("thermometer"))
                    {
                        //print options that have to do with the thermometer
                    }
                    //device
                    String device = incInput.substring(0, incInput.indexOf(" "));
                    String remainder = incInput.substring(incInput.indexOf(" ") + 1);
                    String dProperty = remainder.substring(0, remainder.indexOf(" "));
                    String content = remainder.substring(remainder.indexOf(" ") + 1);
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

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
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
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
    Timer securityTimer;
    String msg1;
    String msg2;
    String msg3;
    String msg4;
    String msg5;
    String msg6;
    String msg7;
    
    
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
                int index = User.appliances.indexOf("Server");
                while ((incInput = in.readLine()) != null) 
                {
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
                                if(msg.length > 6)
                                {
                                    msg7 = msg[6];
                                }
                            }
                        }
                    }
                    if(msg2.equalsIgnoreCase("Alarm"))
                    {
                        if(msg3.equalsIgnoreCase("on"))
                        {
                            if(msg4.equalsIgnoreCase("Timer"))
                            {
                            String hourUn =  msg5;
                            String minUn = msg6;
                            String dayUn = msg7;
                            int hour = Integer.parseInt(hourUn);
                            int min = Integer.parseInt(minUn);
                            int day = Integer.parseInt(dayUn);
                            Calendar timerCal = Calendar.getInstance();
                            timerCal.set(Calendar.DAY_OF_MONTH, day);
                            timerCal.set(Calendar.HOUR_OF_DAY, hour);
                            timerCal.set(Calendar.MINUTE, min);
                            timerCal.set(Calendar.SECOND, 0);
                            Date date = timerCal.getTime();
                            //System.currentTimeMillis()
                            securityTimer = new Timer();
                            securityTimer.schedule(new SecurityTask(this), date);
                            }
                            sendReply("127.0.0.1", User.ports.get(index) , incInput + " " + "ACK");
                            
                        }
                        else if(msg3.equalsIgnoreCase("off"))
                        {
                            if(msg4.equalsIgnoreCase("Timer"))
                            {
                            String hourUn =  msg5;
                            String minUn = msg6;
                            String dayUn = msg7;
                            int hour = Integer.parseInt(hourUn);
                            int min = Integer.parseInt(minUn);
                            int day = Integer.parseInt(dayUn);
                            Calendar timerCal = Calendar.getInstance();
                            timerCal.set(Calendar.DAY_OF_MONTH, day);
                            timerCal.set(Calendar.HOUR_OF_DAY, hour);
                            timerCal.set(Calendar.MINUTE, min);
                            timerCal.set(Calendar.SECOND, 0);
                            Date date = timerCal.getTime();
                            //System.currentTimeMillis()
                            securityTimer = new Timer();
                            securityTimer.schedule(new SecurityTask(this), date);
                            }
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
    private class SecurityTask extends TimerTask
    {
        Security secObject;
        public SecurityTask(Security secObject)
        {
            this.secObject = secObject;
        }
        public void run()
        {
            //this.secObject.sendReply("127.0.0.1", , null);
        }
    }
}

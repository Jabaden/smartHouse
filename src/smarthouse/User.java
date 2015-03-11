/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

import java.io.*;
import java.net.*;
import java.util.*;

public class User 
{
    public static void main(String [] args) throws Exception
    {
       boolean gibberish = false;
       boolean helpMe = false;
       User chatter = new User(args[0]);
       int index = User.appliances.indexOf(args[0]);
       if(args[0].equalsIgnoreCase("server"))
       {
           HouseServer s = new HouseServer(User.ports.get(index));
           s.start();
           System.out.println("Server Online");
       }
       else if(args[0].equalsIgnoreCase("Andy"))
       {
           UserThread u = new UserThread(User.ports.get(index));
           u.start();
           System.out.println("user online");
           System.out.println("Please issue a command");
           System.out.println("For help, type \"help\" ");
           Scanner userInput = new Scanner(System.in);
           while(true)
           {
               gibberish = false;
               String message = userInput.nextLine();
               if(message.equalsIgnoreCase("help"))
               {
                   System.out.println("please enter the appliance you want configure");
                   System.out.print("possible choices include: ");
                   System.out.println("\"Lights\", \" \"Garage\", \"Security\", \"Heater\" ");
                   helpMe = true;
               }
               if(message.equalsIgnoreCase("Garage"))
               {
                  System.out.println("enter a command for the Garage");
                  System.out.println("Type \"Garage\" followed by a command followed by an action");
                  System.out.println("Example: \"Lights door up\" open the garage door ");
                  System.out.print("Choices include \"door up\\down\" ");
                  helpMe = true;
               }
               if(message.equalsIgnoreCase("Security"))
               {
                  System.out.println("enter a command for the home security");
                  System.out.println("Type \"Security\" followed by a command followed by an action");
                  System.out.println("Example: \"security alarm off\" will turn off alarm for the house ");
                  System.out.print("Choices include \"alarm on\\off\"");
                  helpMe = true;
               }
               if(message.equalsIgnoreCase("Lights"))
               {
                  System.out.println("enter a command for the Lights");
                  System.out.println("Type \"Lights\" followed by a command followed by an action");
                  System.out.println("Example: \"Lights kitchen off\" will turn off the kitchen lights ");
                  System.out.print("Choices include \"kitchen on\\off\" \"porch on\\off\" ");
                  System.out.print("\"bedroom on\\off\" \"dining on\\off\" \"status\"");
                  helpMe = true;
               }
               if(message.equalsIgnoreCase("Heater"))
               {
                  System.out.println("enter a command for the Lights");
                  System.out.println("Type \"Heater\" followed by a command followed by an action");
                  System.out.println("Example: \"Heater heat [value]\" will heat the house by an amount ");
                  System.out.print("Choices include \"heat [value]\" \"cool [value]\" ");
                  helpMe = true;
                  
               }
               else
               {
                   gibberish = true;
               }
               if(helpMe == true)
               {
                   continue;
               }
               if(gibberish == true)
               {
                   continue;
               }
               Socket sender = new Socket("127.0.0.1", User.ports.get(index));
               PrintWriter outbound = new PrintWriter(sender.getOutputStream(),true);
               outbound.println(message);
               outbound.close();
               sender.close();
           }
           
       }
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

    String hostName;
    int portNumber;
    Socket cSocket;
    ServerSocket sSocket;
    String host = "127.0.0.1";
    String userName;

    static ArrayList<String> appliances = new ArrayList<>();
    static ArrayList<Integer> ports = new ArrayList<>();
    static ArrayList<Boolean> statuss = new ArrayList<>();
    
    public User(String appliance)
    {
        userName = appliance;
        String userFile = "chatUsers.txt";
        try
        {
            System.out.println(new File(userFile).getAbsolutePath());
            Scanner input = new Scanner(new File(userFile));
            while(input.hasNextLine())
            {
                String line = input.nextLine();
                String[] tempContent = line.split(" ");
                String tempHost = tempContent[0];
                String tempName = tempContent[1];
                String tempPort = tempContent[2];
                appliances.add(tempName);
                ports.add(Integer.parseInt(tempPort));
                statuss.add(false);
            }
            int index = appliances.indexOf(userName);
            statuss.set(index, true);
            input.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
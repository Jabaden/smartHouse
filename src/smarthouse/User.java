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
       User chatter = new User(args[0]);
       int index = User.appliances.indexOf(args[0]);
       if(args[0].equalsIgnoreCase("server"))
       {
           HouseServer s = new HouseServer(User.ports.get(index));
           s.start();
           System.out.println("Server Online");
       }
       else
       {
           //other appliances here
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
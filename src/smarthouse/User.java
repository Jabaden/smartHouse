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
           System.out.println("listening on port: " + User.ports.get(index));
           System.out.println("Server Online");
       }
       else if(args[0].equals("Garage"))
       {
           System.out.println("starting garage");
           Garage garage = new Garage(User.ports.get(index));
           garage.start();
           System.out.println("listening on port: " + User.ports.get(index));
       }
       else if(args[0].equals("Heater"))
       {
           System.out.println("starting Heater Device");
           Heater heater = new Heater(User.ports.get(index));
           heater.start();
           System.out.println("listening on port: " + User.ports.get(index));
       }
       else if(args[0].equals("Lights"))
       {
           System.out.println("starting device to control Lights");
           Lights lights = new Lights(User.ports.get(index));
           lights.start();
           System.out.println("listening on port: " + User.ports.get(index));
       }
       else if(args[0].equals("Lights"))
       {
           System.out.println("starting device to control Lights");
           Lights lights = new Lights(User.ports.get(index));
           lights.start();
           System.out.println("listening on port: " + User.ports.get(index));
       }
       else if(args[0].equals("Security"))
       {
           System.out.println("starting Security Control");
           Security security = new Security(User.ports.get(index));
           security.start();
           System.out.println("listening on port: " + User.ports.get(index));
       }
       else if(args[0].equalsIgnoreCase("User"))
       {
           UserThread u = new UserThread(User.ports.get(index));
           index = User.appliances.indexOf("Server");
           u.start();
           System.out.println("***Welcome to the Smart House Client!***");
           System.out.println("Please Select a Device to manipulate!");
           System.out.println("For help, type \"help\" ");
           Scanner userInput = new Scanner(System.in);
           while(true)
           {
               gibberish = false;
               helpMe = false;
               String message = userInput.nextLine();
               String messageTwo;
               String messageThree;
               String messageFour;
               String messageFive;
               String messageSix;
               String[] messageParse = message.split(" ");
               String partOne = messageParse[0];
               if(partOne.equalsIgnoreCase("status") && messageParse.length ==1)
               {
                    helpMe = true;
                    System.out.println("Status of lights true = on, false = off");
                    for(int i = 0; i < lightArray.size(); i++)
                    {
                        System.out.print(lightArray.get(i));
                        System.out.println(" " + lightStatus[i]);
                    }
                    System.out.println("temperature is: " + temperature);
                    System.out.println("status of the garage is: " + garageStatus +"\n true = up, false = down");
                    System.out.println("status of the alarm is: " + securityStatus +"\n true = on, false = off");
                    
               }
               else if(partOne.equalsIgnoreCase("Garage Door Open"))
               {
                   System.out.println("sending a test message");
               }
               else if(partOne.equalsIgnoreCase("help"))
               {
                   System.out.println("please enter the appliance you want configure");
                   System.out.print("possible choices include: ");
                   System.out.println("\"Lights\", \" \"Garage\", \"Security\", \"Heater\" \"Timer\" ");
                   helpMe = true;
               }
               else if(partOne.equalsIgnoreCase("Garage"))
               {
                  System.out.println("enter a command for the Garage");
                  System.out.println("Choices include:");
                  System.out.println("door");
                  messageTwo = userInput.nextLine();
                  if(messageTwo.equalsIgnoreCase("door"))
                  {
                      message += " " + messageTwo;
                      System.out.println("Enter a command for " + messageTwo);
                      System.out.println("Choices include:");
                      System.out.println("Open");
                      System.out.println("Close");
                      messageThree = userInput.nextLine();
                      if(messageThree.equalsIgnoreCase("Open"))
                      {
                          message += " " + messageThree;
                      }
                      else if(messageThree.equalsIgnoreCase("close"))
                      {
                          message += " " + messageThree;
                      }
                      else gibberish = true;
                  }
                  else
                  {
                      gibberish = true;
                  }
                  //System.out.println("Type \"Garage\" followed by a command followed by an action");
                  //System.out.println("Example: \"Lights door up\" open the garage door ");
                  //System.out.print("Choices include \"door up\\down\" ");
                  //helpMe = true;
               }
               else if(partOne.equalsIgnoreCase("Security"))
               {
                  System.out.println("enter a command for the home security");
                  System.out.println("You will be asked if you would like to add a timer after inputting your commands");
                  //System.out.println("Type \"Security\" followed by a command followed by an action");
                  //System.out.println("Example: \"security alarm off\" will turn off alarm for the house ");
                  System.out.print("Choices include:");
                  System.out.println("Alarm");
                  messageTwo = userInput.nextLine();
                  if(messageTwo.equalsIgnoreCase("Alarm"))
                  {
                      message += " " + messageTwo;
                      System.out.println("Enter a command for the Alarm");
                      System.out.println("Choices include: ");
                      System.out.println("On \nOff");
                      messageThree = userInput.nextLine();
                      if(messageThree.equalsIgnoreCase("On") || messageThree.equalsIgnoreCase("Off"))
                      {
                          message += " " + messageThree;
                          System.out.println("Would you like to schedule this for a future time? Y/N");
                          messageFour = userInput.nextLine();
                          if(messageFour.equalsIgnoreCase("Y") || messageFour.equalsIgnoreCase("yes"))
                          {
                              System.out.println("Today? Y/N");
                              messageFour = userInput.nextLine();
                              if(messageFour.equalsIgnoreCase("yes")|| messageFour.equalsIgnoreCase("Y"))
                              {
                                  message += " " + "Timer";
                                  System.out.println("Enter the hour");
                                  messageFour = userInput.nextLine();
                                  message += " " + messageFour;
                                  System.out.println("Enter the minute");
                                  messageFive = userInput.nextLine();
                                  message += " " + messageFive;
                              }
                              else if(messageFour.equalsIgnoreCase("no") || messageFour.equalsIgnoreCase("n"))
                              {
                                  message += " " + "Timer";
                                  System.out.println("Enter the hour");
                                  messageFour = userInput.nextLine();
                                  message += " " + messageFour;
                                  System.out.println("Enter the minute");
                                  messageFive = userInput.nextLine();
                                  message += " " + messageFive;
                                  System.out.println("What day? \n Enter the day of the month");
                                  messageSix = userInput.nextLine();
                                  message += " " + messageSix;
                              }
                              else
                              {
                                  gibberish = true;
                              }
                          }
                          else if(messageFour.equalsIgnoreCase("N") || messageFour.equalsIgnoreCase("no"))
                          {
                              //send the message
                          }
                          else
                          {
                              gibberish = true;
                          }
                          
                      }
                      else
                      {
                          gibberish = true;
                      }
                      
                  }
                  else
                  {
                      gibberish = true;
                  }
                }
               else if(partOne.equalsIgnoreCase("Lights"))
               {
                  System.out.println("enter a command for the Lights");
                  //System.out.println("Type \"Lights\" followed by a command followed by an action");
                  //System.out.println("Example: \"Lights kitchen off\" will turn off the kitchen lights ");
                  System.out.println("Choices include: kitchen\nporch\nbedroom\ndining");
                  messageTwo = userInput.nextLine();
                  if(messageTwo.equalsIgnoreCase("kitchen") || messageTwo.equalsIgnoreCase("porch") ||
                          messageTwo.equalsIgnoreCase("dining") || messageTwo.equalsIgnoreCase("bedroom"))
                  {
                      message += " " + messageTwo;
                      System.out.println("On or off?");
                      messageThree = userInput.nextLine();
                      if(messageThree.equalsIgnoreCase("on") || messageThree.equalsIgnoreCase("off"))
                      {
                          message += " " + messageThree;
                          System.out.println("Would you like to schedule this for a future time? Y/N");
                          messageFour = userInput.nextLine();
                          if(messageFour.equalsIgnoreCase("Y") || messageFour.equalsIgnoreCase("yes"))
                          {
                              System.out.println("Today? Y/N");
                              messageFour = userInput.nextLine();
                              if(messageFour.equalsIgnoreCase("yes")|| messageFour.equalsIgnoreCase("Y"))
                              {
                                  message += " " + "Timer";
                                  System.out.println("Enter the hour");
                                  messageFour = userInput.nextLine();
                                  message += " " + messageFour;
                                  System.out.println("Enter the minute");
                                  messageFive = userInput.nextLine();
                                  message += " " + messageFive;
                              }
                              else if(messageFour.equalsIgnoreCase("no") || messageFour.equalsIgnoreCase("n"))
                              {
                                  message += " " + "Timer";
                                  System.out.println("What day? \n Enter the day of the month");
                                  messageFour = userInput.nextLine();
                                  message += " " + messageFour;
                                  System.out.println("Enter the hour");
                                  messageFive = userInput.nextLine();
                                  message += " " + messageFive;
                                  System.out.println("Enter the minute");
                                  messageSix = userInput.nextLine();
                                  message += " " + messageSix;
                              }
                              else
                              {
                                  gibberish = true;
                              }
                          }
                          else if(messageFour.equalsIgnoreCase("no") || messageFour.equalsIgnoreCase("n"))
                          {
                              //send message
                          }
                          else
                          {
                              gibberish = true;
                          }
                          
                      }
                      else
                      {
                          gibberish = true;
                      }
                  }
                  else
                  {
                      gibberish = true;
                  }
               }
               else if(partOne.equalsIgnoreCase("Heater"))
               {
                  System.out.println("enter a command for the Heater");
                  System.out.print("Choices include\nheat\ncool");
                  messageTwo = userInput.nextLine();
                  {
                      if(messageTwo.equalsIgnoreCase("heat") || messageTwo.equalsIgnoreCase("cool"))
                      {
                          message += " " + messageTwo;
                          {
                              System.out.println("By how much would you like to increase/decrease the temperature?");
                              messageThree = userInput.nextLine();
                              {
                                  message += " " + messageThree;
                              }
                          }
                      }
                      else
                      {
                          gibberish = true;
                      }
                  }
                  
               }
               else
               {
                   gibberish = true;
                   System.out.println("setting gibberish to true");
               }
               if(helpMe == true)
               {
                   System.out.println("needed help");
                   continue;
               }
               if(gibberish == true)
               {
                   System.out.println("Not a recognized command");
                   continue;
               }
               Socket sender = new Socket("127.0.0.1", User.ports.get(index));
               PrintWriter outbound = new PrintWriter(sender.getOutputStream(),true);
               System.out.println("sending a message on port: " + User.ports.get(index));
               System.out.println("the message im sending is " + message);
               outbound.println(message);
               outbound.close();
               sender.close();
           }
           
       }
       else
       {
           System.err.println("device not found. Consult info.txt for available devices");
           return;
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
    int baseTemp = 68;
    boolean defaultGarage = false;

    static ArrayList<String> appliances = new ArrayList<>();
    static ArrayList<Integer> ports = new ArrayList<>();
    static ArrayList<Boolean> statuss = new ArrayList<>();
    static int temperature = 68;
    static boolean garageStatus = false;
    static boolean securityStatus = false;
    static boolean[] lightStatus = new boolean[5];
    static ArrayList<String> lightArray = new ArrayList<>();
    
    public User(String appliance)
    {
        userName = appliance;
        String userFile = "info.txt";
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
            lightArray.add("kitchen");
            lightArray.add("porch");
            lightArray.add("bedroom");
            lightArray.add("dining");
            lightArray.add("backyard");
            for(int i = 0; i < lightArray.size(); i++)
            {
                lightStatus[i] = false;
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
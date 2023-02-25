import java.util.*;// Importing all the pakages in java.util
import java.io.Console;// importing Console class from java.io

//Bank class
class Bank {
    Scanner sc = new Scanner(System.in);// Creating a scanner object
    String holderName, accNum;
    String userName;

    Map<String, LinkedList<String>> bankData = new HashMap<>();// Creating an hashmap object
    // boolean flag=false;

    // Function for verfying the string syntactically.
    boolean isValidAccNameNum(String holderName,String accNum)
    {
        if (accNum.matches("^[a-zA-Z\\s]*$") || !holderName.matches("^[a-zA-Z\\s]*$"))//Using regex to trace patterns.
        {
            System.out.println("\n! Wrong format \nPlease check and enter again !");
            return false;
        }
        return true;
    }

    // Database Function
    void bankDatabase(String userName)
    {
        double accBal=0;
        System.out.println("\n=========== Bank Details ===========");
        System.out.print("Enter Name of Account Holder: ");
        holderName=sc.nextLine();
        System.out.print("Enter Account Number: ");
        accNum=sc.next();

        // checking if account name or number is inputed correctly or not
        boolean validAccNameNum=isValidAccNameNum(holderName,accNum);
        sc.nextLine();
        if(!validAccNameNum)
            holderName=null; //if not correct input then again it will go to function where it has been called and ask for input again till not correct input

        else
        {
            System.out.println("===================================");
            bankData.put(userName, new LinkedList<>());
            bankData.get(userName).add(holderName);
            bankData.get(userName).add(accNum);
            bankData.get(userName).add(Double.toString(accBal));
            System.out.println("\nBank account created successfully...");
        }
    }
    // Function for features accessible once user logs in.
    void features(String userName)
    {
        boolean flag=true;
        String option;
//        while(true)
        do
        {
            System.out.println("\n============== Features ==============");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Show Your Details");
            System.out.println("4. Log out");
            System.out.println("======================================");
            System.out.print("\nEnter your choice: ");
            option = sc.nextLine();
            // Using switch case for calling different functions as per the user's input
            switch (option) {
                case "1":
                    deposit(userName);
                    sc.nextLine();
                    break;
                case "2":
                    withdraw(userName);
                    sc.nextLine();
                    break;
                case "3":
                    displayDetails(userName);
                    break;
                /*case "4":
                    flag=false;
                    System.out.print("\nLogged out successfully...");
                    break;*/
                default:
                    System.out.println("! Invalid Choice !");
            }
        }while(!option.equals("4"));
        System.out.print("\nLogged out successfully...");
    }

    // Function for depositing the amount in the logged in user's account
    private void deposit(String userName)
    {
        System.out.print("\nEnter the Amount to be Deposited: ");
        double amount = sc.nextDouble();
        if(amount<=0){
            System.out.print("\n! Amount should be greater than 0 !\n");
            return;
        }
        else{
            double sum = Double.parseDouble(bankData.get(userName).get(2)) + amount;
            String name = bankData.get(userName).get(0);
            String num = bankData.get(userName).get(1);
            bankData.put(userName, new LinkedList<String>(Arrays.asList(name, num, Double.toString(sum))));
            System.out.print("\nAmount deposited successfully...\n");
        }
    }

    // Function for withdrawing the amount from the logged in user's account
    private void withdraw(String userName)
    {
        System.out.print("Enter the Amount to be Withdrawn: ");
        double withdrawAmnt = sc.nextDouble();
        double currBal = Double.parseDouble(bankData.get(userName).get(2));
        // Conditions to check before withdrawing
        if(withdrawAmnt<=0){
            System.out.print("! Withdrawal amount should be greater than 0 !");
        }
        else if (withdrawAmnt > currBal)
        {
            System.out.println("\n! You dont have sufficient balance to withdraw !");
            System.out.println("Your Current Balance is: " + currBal);
        }
        else
        {
            currBal -= withdrawAmnt;
            String name = bankData.get(userName).get(0);
            String num = bankData.get(userName).get(1);
            bankData.put(userName, new LinkedList<String>(Arrays.asList(name, num, Double.toString(currBal))));// Using put method to update the balance of the current user
            System.out.print("\n");
            System.out.println(withdrawAmnt + " is withdrawn successfully...");
            System.out.println("Now your updated balance is: " + currBal);
        }
    }

    // Function for displaying details of the logged in user
    private void displayDetails(String userName)
    {
        System.out.println("\n============ Your Details ============");
        System.out.print("Account Holder Name: ");
        System.out.print(bankData.get(userName).get(0));
        System.out.print("\nAccount Number: ");
        System.out.print(bankData.get(userName).get(1));
        System.out.print("\nAccount Balance: ");
        System.out.print(getBalance(userName));
        System.out.println("\n======================================");
    }

    // Function for accessing and returning bank balance of current user
    String getBalance(String userName)
    {
        return bankData.get(userName).get(2);
    }
}

// Registration class which includes registering new user and other functions related to it.
class Registration
{
    Scanner sc = new Scanner(System.in);
    String fullName;
    String userName;
    String password;
    Map<String, LinkedList<String>> data = new HashMap<>();// Creating hashmap data structure
    Bank b = new Bank();

    // Function for checking whether the username is valid or not
    boolean isValidUserName(String name)
    {
        if (data.containsKey(name))
        {
            System.out.println("\nUsername already taken..please try for another username");
            return false;
        }
        else if (!name.equals(name.toLowerCase()))
        {
            System.out.println("\n! Use only lowercase characters in username!");
            return false;
        }
        else if (name.contains(" "))
        {
            System.out.println("\n! No spaces allowed in username !");
            return false;
        }
        else if (!name.matches("^[a-z0-9_]+$"))
        {
            System.out.println("\n! No special characters allowed in username !");
            return false;
        }
        return true;
    }

    // Function for hiding password while entering it and printing asterisk's in place of the input
    String printPassword()
    {
        Console console = System.console();
        char[] p = console.readPassword("Enter Password:");// Reading password from the console while hiding it and not printing it on the screen
        for (int i = 0; i < p.length; i++)
        {
            console.printf("*");
        }
        return String.valueOf(p);// Returning original password
    }

    // Function for registering new user in the system
    public void register() {
        System.out.print("Enter Full Name: ");
        String fullName = sc.nextLine();
        fullName=fullName.trim();
        // Validating full name of the user
        if (!fullName.matches("^[a-zA-Z\\s]+$"))
        {
            System.out.println("\n! Only Characters Allowed in Full Name !");
            return;
        }
        System.out.print("Enter Username: ");
        String userName = sc.nextLine();

        // Validating username of the user
        boolean validUserName = isValidUserName(userName);
        if (!validUserName)
            return;
        String password = sc.nextLine();
        //String password = printPassword();
        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            System.out.print(" \n! Password Format Error !\n");
            System.out.print("\nIt must contain at least one digit (0-9)");
            System.out.print("\nIt must contain at least one lowercase letter (a-z)");
            System.out.print("\nIt must contain at least one uppercase letter (A-Z)");
            System.out.print("\nIt must contain at least one special character (@#$%^&+=)");
            System.out.print("\nIt must not contain any spaces");
            System.out.print("\nIt must be at least 8 characters long");
        }
        else {  //entering data in map
            data.put(userName, new LinkedList<>());
            data.get(userName).add(fullName);
            data.get(userName).add(password);
            System.out.println("\n\nRegistration Successful... ");
        }
    }

    // Function for logging in the user system
    public void login()
    {
        System.out.println("\n================= LOGIN =================");
        System.out.print("Enter Username: ");
        userName = sc.next();
        sc.nextLine();
        // checking if the user has registered in system or not
        //if not then show message to user and redirect to Main menu options
        if (!data.containsKey(userName))
        {
            System.out.print("\n! Username Doesn't Exist !\n");
            //login();
            return;
        }
        //if registered then allow him for entering in Bank system
        String password = sc.next();
       //String password=printPassword();
        System.out.println("\n=========================================");
        boolean check;
        // Checking whether the username and password are valid or not i.e. matching or not
        check = isValidUser(userName, password);
        if (check)
        {
            operations(userName);// Calling operations function if the details are valid and matched.
        }
        else
        {
            System.out.print("\n! Password Invalid !\n");
            System.out.print("Forgot password(Y/N)? ");
            char option = sc.next().charAt(0);
            sc.nextLine();
            if (option == 'y' || option == 'Y')
            {
                forgotPassword(userName);// Calling forgotPassword function in case the user has forgotten the password
            }
            else if (option == 'n' || option == 'N')
            {
                return;
            }
            else
            {
                System.out.println("\nYou entered wrong character....");
            }
        }
    }

    /* This is function from where all operations of Bank class will be called by object for valid user in
  terms of bank database
     */
    private void operations(String userName)
    {
        if (!b.bankData.containsKey(userName))
        {
            System.out.println("\nLogin Successful,\nWelcome " + data.get(userName).get(0));
            System.out.println("\nDo you want to register in bank?");
            System.out.println("1.Yes");
            System.out.println("2.No");
            System.out.print("Enter 1 or 2:");
            char optionForReg = sc.next().charAt(0);
            System.out.println(optionForReg);
            //char c=Character.toLowerCase(optionForReg.charAt(0));
            switch(optionForReg){
                case '1': b.bankDatabase(userName);
                    while(b.holderName==null){
                        b.bankDatabase(userName);
                    }
                    break;
                case '2': return;
                default:System.out.println("\n!!Please enter correct input!!");
                return;
            }
        }
        else
        {
            System.out.println("Welcome back " + data.get(userName).get(0));
        }
        b.features(userName);
    }

    // Function for validating the user and changing the password in case the user forgots the password
    private void forgotPassword(String user)
    {
        /* here we are taking full name for two layer authentication: 1. username 2. Full name */
        System.out.print("Enter Your full name: ");
        String fName = sc.nextLine();
        if (data.containsKey(user)) {
            if (fName.equalsIgnoreCase(data.get(user).get(0)))
            {
                System.out.println("\nValid User");
                //String uPassword = printPassword();
                String uPassword= sc.next();

                // Using regular expressions to check and validate the user'd password syntactically
                if (!uPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"))
                {
                    System.out.print(" \n! Password Format Error !\n");
                    System.out.print("\nIt must contain at least one digit (0-9)");
                    System.out.print("\nIt must contain at least one lowercase letter (a-z)");
                    System.out.print("\nIt must contain at least one uppercase letter (A-Z)");
                    System.out.print("\nIt must contain at least one special character (@#$%^&+=)");
                    System.out.print("\nIt must not contain any spaces");
                    System.out.print("\nIt must be at least 8 characters long\n");
                }
                else
                {
                    data.put(user, new LinkedList<String>(Arrays.asList(fName, uPassword)));
                    System.out.println("\nYour password has been updated successfully...");
                }
            }
            else
            {
                System.out.println("\nFull name not found");
                forgotPassword(user);
            }
        }
        else
        {
            System.out.println("\nUsername not valid");
        }
    }

    // Function for checking whether a user is valid or not
    boolean isValidUser(String userName, String password)
    {
        if (data.containsKey(userName))
        {
            if (data.get(userName).get(1).equals(password))
            {
                return true;
            }
        }
        return false;
    }

    // Function for displaying the user data
    void display()
    {
        System.out.println(data);
    }
}

// Class which includes the welcoming and initial phase statements and functions
class PortalMenu{
    static void options()
    {
        try (Scanner sc = new Scanner(System.in))
        {
            System.out.println("Welcome to a console-based banking system");

            Registration reg = new Registration();
            while (true) {

                System.out.println("\n============ Banking Portal =============");
                System.out.println("1.Register");
                System.out.println("2.Log in");
                System.out.println("3.Exit");
                System.out.println("==========================================");
                System.out.print("\nEnter you choice: ");
                String mainOption = sc.next();
                //sc.nextLine();

                // Using switch case for calling different functions as per the user's input
                switch (mainOption)//we used string variable instead of integer to except from wrong input(String input)
                {
                    case "1":
                        reg.register();
                        break;
                    case "2":
                        reg.login();
                        System.out.println();
                        break;
                    case "3":
                        System.exit(0); //terminate program
                    default:
                        System.out.println("\n! Invalid Choice !");
                }
            }
        }
    }
}

// The main class of the program which will be called automatically once the program is executed
public class Main
{
    public static void main(String[] args) {
        PortalMenu.options();// Calling options function of PortalMenu class without making its object as it an is static method
    }
}

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
class User {
    private String First_name;
    private String Father_name;
    private String Mother_name;
    private String accountNumber;
    private double balance;
    private int pin;
  
    public User(String First_name,String Father_name,String Mother_name, String accountNumber, double balance, int pin) {
      this.First_name = First_name;
      this.Father_name = Father_name;
      this.Mother_name = Mother_name;
      this.accountNumber = accountNumber;
      this.balance = balance;
      this.pin = pin;
    }
  
    public String getFirst_name() {
      return First_name;
    }

    public String getFather_name() {
      return Father_name;
    }

    public String getMother_name() {
      return Mother_name;
    }
  
    public String getAccountNumber() {
      return accountNumber;
    }
  
    public int getPin(){
      return pin;
    }
  
    public double getBalance() {
      return balance;
    }
  
    public void deposit(double amount) {
      balance += amount;
    }
  
    public void withdraw(double amount) {
      balance -= amount;
    }
  }
  
  class Admin {
    private ATM atm;
  
    public Admin(ATM atm) {
      this.atm = atm;
    }
  
    public void createNewUser(String First_name,String Father_name,String Mother_name, String accountNumber, double balance, int pin) {
      atm.createUser(First_name,Father_name,Mother_name, accountNumber, balance, pin);
    }
  
    public void deleteUser(String accountNumber) {
      atm.deleteUser(accountNumber);
    }
  
    public User[] getUsers() {
      return atm.getUsers();
    }
  }
  
  class ATM {
    private User[] users;
    private String adminPassword;
    private int numberOfUsers;
  
    public ATM(String password) {
      users = new User[100];
      adminPassword = password;
      numberOfUsers = 0;
    }
  
    public User getUser(String accountNumber) {
      for (int i = 0; i < numberOfUsers; i++) {
        if (users[i].getAccountNumber().equals(accountNumber)) {
          return users[i];
        }
      }
      return null;
    }
  
    public boolean authenticate(String accountNumber, String pin) {
      User user = getUser(accountNumber);
      if (user != null) {
        
        return true;
      }
      return false;
    }
  
    public void deposit(String accountNumber, double amount) {
      User user = getUser(accountNumber);
      if (user != null) {
        user.deposit(amount);
      }
    }
  
    public void withdraw(String accountNumber, double amount) {
      User user = getUser(accountNumber);
      if (user != null) {
        user.withdraw(amount);
      }
    }
  
    public double checkBalance(String accountNumber) {
      User user = getUser(accountNumber);
      if (user != null) {
        return user.getBalance();
      }
      return -1;
    }
  
    public boolean isAdmin(String password) {
      return password.equals(adminPassword);
    }
  
    public void createUser(String First_name, String Father_name, String Mother_name, String accountNumber, double balance, int pin) {
      if (numberOfUsers < 100) {
        users[numberOfUsers] = new User(First_name,Father_name,Mother_name, accountNumber, balance, pin);
        numberOfUsers++;
      }
    }
  
    public void deleteUser(String accountNumber) {
      for (int i = 0; i < numberOfUsers; i++) {
        if (users[i].getAccountNumber().equals(accountNumber)) {
          users[i] = null;
          for (int j = i; j < numberOfUsers - 1; j++) {
            users[j] = users[j + 1];
          }
          users[numberOfUsers - 1] = null;
          numberOfUsers--;
          break;
        }
      }
    }
  
    public User[] getUsers() {
      return Arrays.copyOf(users, numberOfUsers);
    }
  }
  
  public class Atm3 {
    public static void main(String[] args) throws IOException {
      ATM atm = new ATM("adminabc");
      Admin admin = new Admin(atm);
  
      
      //BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
    /*  String line;
      while ((line = reader.readLine()) != null) {
        String[] values = line.split(",");
        String First_name = values[0];
        String Father_name = values[1];
        String Mother_name = values[2];
        String accountNumber = values[3];
        double balance = Double.parseDouble(values[4]);
        int pin = Integer.parseInt(values[5]);
        atm.createUser(First_name,Father_name,Mother_name, accountNumber, balance, pin);
      }
      reader.close(); */
  
      Scanner scanner = new Scanner(System.in);
      boolean quit = false;
      while (!quit) {
        System.out.println("1. Create new user");
        System.out.println("2. Deposit");
        System.out.println("3. check balance");
        System.out.println("4. withdraw");
        System.out.println("5. View all users");
        System.out.println("6. Delete user");
        System.out.println("7. Exit");
        System.out.print("Enter choice (1-7): ");
        int choice = scanner.nextInt();
  
        switch (choice) {
          case 1:
            System.out.print("Enter First_name: ");
            String First_name = scanner.next();
            System.out.print("Enter Father_name: ");
            String Father_name = scanner.next();
            System.out.print("Enter Mother_name: ");
            String Mother_name = scanner.next();
            System.out.print("Enter account number: ");
            String accountNumber = scanner.next();
            System.out.print("Enter balance: ");
            double balance = scanner.nextDouble();
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();
  
            admin.createNewUser(First_name, Father_name, Mother_name,accountNumber, balance, pin);
            break;
          case 2:
          System.out.print("Enter account number: ");
          accountNumber = scanner.next();
          System.out.print("Enter deposit amount: ");
          double depositAmount = scanner.nextDouble();
          atm.deposit(accountNumber, depositAmount);
          break;
           
          case 3:
          System.out.print("Enter account number: ");
          accountNumber = scanner.next();
           balance = atm.checkBalance(accountNumber);
          if (balance != -1) {
            System.out.println("Balance: " + balance);
          } else {
            System.out.println("Invalid account number.");
          }
          break;
          
          
          case 4:
          System.out.print("Enter account number: ");
          accountNumber = scanner.next();
          System.out.print("Enter withdrawal amount: ");
          double withdrawalAmount = scanner.nextDouble();
          atm.withdraw(accountNumber, withdrawalAmount);
          break;
           
          case 5:
          String str2 , str1;
          Scanner sc = new Scanner (System.in);
          str1 = "adminabc";
          System.out.print("enter admin password:  ");
          str2 = sc.next();
          if (str1.equals(str2)){
            System.out.println("Welcome! ");
            User[] users = admin.getUsers();
            for (int m = 0; m < users.length; m++) {
              if (users[m] != null) {
                System.out.println("First_name: " + users[m].getFirst_name());
                System.out.println("Account number: " + users[m].getAccountNumber());
                System.out.println("Balance: " + users[m].getBalance());
                System.out.println("PIN: " + users[m].getPin());
              }
            }
          }
          else{
            System.out.println("wrong password");
            for (int i =0; i<3; i++){
                System.out.print("enter your password:  ");
                str2 = sc.next();
                if (str1.equals(str2)){
                    System.out.print("Welcome!");
                    User[] users = admin.getUsers();
                    for (int m = 0; m < users.length; m++) {
                      if (users[m] != null) {
                        System.out.println("First_name: " + users[m].getFirst_name());
                        System.out.println("Account number: " + users[m].getAccountNumber());
                        System.out.println("Balance: " + users[m].getBalance());
                        System.out.println("PIN: " + users[m].getPin());
                      }
                    }
                    break;
                }
                else{
                    System.out.println("try again");
                }
            }
        }

           
            break;


          case 6:
          System.out.print("Enter account number: ");
          accountNumber = scanner.next();
          admin.deleteUser(accountNumber);
          break;
           
          case 7:
            quit = true;
            break;
          default:
            System.out.println("Invalid choice.");
            break;
        }
        System.out.println();
      }
      scanner.close();
    }
  }
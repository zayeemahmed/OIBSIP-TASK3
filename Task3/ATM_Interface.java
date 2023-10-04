import java.util.Scanner;

class BankAcc{
    String name;
    String usernameReg;
    String passReg;
    String accno;
    float balance =100000;
    int transactions = 0;
    String transactionHistory = "";

    public void register(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name : ");
        this.name = sc.nextLine();
        System.out.println("Enter your Username : ");
        this.usernameReg = sc.nextLine();
        System.out.println("Enter your password : ");
        this.passReg = sc.nextLine();
        System.out.println("Enter your accno : ");
        this.accno = sc.nextLine();
        System.out.println("Registartion completed....Please Login!!");
    }
    public boolean login(){
        boolean Loginis = false;
        Scanner sc = new Scanner(System.in);
        while(!Loginis){
            System.out.println("Enter your Username : ");
            String usernameLog = sc.nextLine();
            if(usernameLog.equals(usernameReg)){
                while(!Loginis){
                    System.out.println("Enter your Password : ");
                    String passLog = sc.nextLine();
                    if(passLog.equals(passReg)){
                        System.out.println("LOGIN SUCCESSFUL!");
                        Loginis = true;
                    }
                    else{
                        System.out.println("INCORRECT PASSWORD!");
                    }
                }
            }
            else{
                System.out.println("USERNAME NOT FOUND! PLEASE REGISTER!");
            }
        }
        return Loginis;
    }

    public void withdraw(){
        System.out.println("Enter the amount to be withdrawn : ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try{
            if( balance >= amount){
                transactions++;
                balance -= amount;
                System.out.println("WITHDRAWAL SUCCESSFUL");
                String acknowledgement = amount + "WITHDRAWED SUCCESSFULLY\n";
                transactionHistory = transactionHistory.concat(acknowledgement);
            }
            else{
                System.out.println("INSUFFICIENT BALANCE");
            }
        }
        catch(Exception e){

        }
    }

    public void deposit(){
        System.out.println("Enter the amount be deposited");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try{
            if( amount <= 100000){
                transactions++;
                balance = balance + amount;
                System.out.println("DEPOSIT SUCCESSFUL");
                String Ack = amount + "Rs DEPOSITED\n";
                transactionHistory = transactionHistory.concat(Ack);
            }
            else{
                System.out.println("INVALID DEPOSIT(LIMIT Rs1000000)");
            }
        }
        catch(Exception e){

        }
    }

    public void transfer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter reciepent's name : ");
        String reciepent = sc.nextLine();
        System.out.println("Enter the amount to transfer : ");
        float amount = sc.nextFloat();

        try{
            if(balance >= amount){
                if(amount<=50000){
                    transactions++;
                    balance = balance - amount ;
                    System.out.println("SUCCESSFULLY TRANSFERED TO "+ reciepent);
                    String ack = amount + "Rs Transfered to" + reciepent + "\n";
                    transactionHistory = transactionHistory.concat(ack);
                }
                else{
                    System.out.println("INVALID REQUEST(LIMIT IS Rs50000)");
                }
            }
            else{
                System.out.println("INSUFFICIENT BALANCE");
            }
        }
        catch(Exception e){

        }
    }

    public void checkBalance(){
        System.out.println("\nRs" + balance);
    }

    public void TransactionHistory(){
        if(transactions ==0){
            System.out.println("NO TRANSACTIONS MADE!");
        }
        else{
            System.out.println("\n" + transactionHistory);
        }
    }
}

public class ATM_Interface{
    public static int takeIntInput(int limit){
        int input = 0;
        boolean flag = false;
        while(!flag){
            try{
                Scanner sc = new Scanner (System.in);
                input = sc.nextInt();
                flag = true;
                if(flag && input > limit || input<1){
                    System.out.println("Choose the number between 0 and " + limit);
                    flag = false;
                }
            }
            catch(Exception e){
                System.out.println("Enter only integer value");
                flag = false;
            }
        }
        return input;
    }

    public static void main(String[] args){
        System.out.println("\n**WELCOME TO ATM SYSTEM**\n");
        System.out.println("1.Register \n2.Exit");
        System.out.print("Enter Your Choice - ");
        int choice = takeIntInput(2);

        if ( choice == 1 ) {
            BankAcc b = new BankAcc();
            b.register();
            while(true) {
                System.out.println("\n1.Login \n2.Exit");
                System.out.print("Enter Your Choice - ");
                int ch = takeIntInput(2);
                if ( ch == 1 ) {
                    if (b.login()) {
                        System.out.println("\n\n**WELCOME BACK " + b.name + " **\n");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
                            System.out.print("\nEnter Your Choice - ");
                            int c = takeIntInput(6);
                            switch(c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.TransactionHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                }
                else {
                    System.exit(0);
                }
            }
        }
        else {
            System.exit(0);
        }
 }
}
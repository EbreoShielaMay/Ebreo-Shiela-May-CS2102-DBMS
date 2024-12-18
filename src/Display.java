public class Display {
    public void displayMainMenu() {
            System.out.println("\t\t\t=-=-=-=-===***===-=-=-=-=");
            System.out.println("\t\t\t WELCOME TO D'Bookstore! ");
            System.out.println("\t\t\t=-=-=-=-=-=-=-=-=-=-=-=-=");

            System.out.println(" -=-=-=-=-=");
            System.out.println(" || MENU ||");
            System.out.println(" -=-=-=-=-=");
            System.out.println();
            System.out.println("-------------");
            System.out.println("1. Sign up");
            System.out.println("-------------");
            System.out.println("2. Log in");
            System.out.println("-------------");
            System.out.println("3. Exit");
            System.out.println("-------------");
            System.out.print("Enter your choice: ");
    }

    public void displayRS() {
        System.out.println("\n\nx-------------------------------x");
        System.out.println("|  Registration successful. :>  |");
        System.out.println("x-------------------------------x\n\n");
    }

    public void displayError() {
        System.out.println("\n\nx-------------------------------x");
        System.out.println("|  Invalid choice. Try again. |");
        System.out.println("x-------------------------------x\n\n");
    }

    public void displayLS() {
        System.out.println("\n\nx-----------------------x");
        System.out.println("|  Login successful. :>  |");
        System.out.println("x------------------------x\n\n");
    }

    public void logMenu(){
        System.out.println("=-=-=-=-=-=-=-=-=-=-");
        System.out.println("|| Logged in Menu ||");
        System.out.println("=-=-=-=-=-=-=-=-=-=-");
        System.out.println();
        System.out.println("------------------------");
        System.out.println("1. See Book Categories");
        System.out.println("------------------------");
        System.out.println("2. Add Balance");
        System.out.println("------------------------");
        System.out.println("3. View Wallet");
        System.out.println("------------------------");
        System.out.println("4. Exit");
        System.out.println("------------------------");
        System.out.print("Please choose an option: ");
    }

    public void displayBookCategories(){
        System.out.println("=-=-=-=--=-=-=-=-=-=-");
        System.out.println("||   Book Type     ||");
        System.out.println("=-=-=-=--=-=-=-=-=-=-");
    
        System.out.println("(P) Physical Book ");
        System.out.println("(E) eBook");
        System.out.print("What type of book do you want? Choose the corresponding letter or press Enter to return: ");
    }

    public void displayWallet() {
        System.out.println("=-=-=-=-=-=-=");
        System.out.println("|   Wallet  |");
        System.out.println("=-=-=-=-=-=-=");
    }

    public void displayPhysicalBookCategory() {
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("| Available Physical Book Categories: |");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("(1) Programming");
        System.out.println("(2) Hobbies");
        System.out.println("(3) Comics / Manga");
        System.out.println("(4) English Lit");
        System.out.println("(6) Science");
        System.out.println("(7) Kids");
        System.out.print("Choose a category or press Enter to return: ");
    }

    public void displayEbookCategory() {
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("| Available eBook Categories: |");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("(1) Programming");
        System.out.println("(2) Hobbies");
        System.out.println("(3) Comics / Manga");
        System.out.println("(4) English Lit");
        System.out.println("(6) Science");
        System.out.println("(7) Kids");
        System.out.print("Choose a category or press Enter to return: ");
    }
    

}

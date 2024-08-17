import java.util.*;
public class Antventure_game {

    static Scanner scan = new Scanner(System.in);
    static String charName;
    static boolean gameRunning;
    static int Int, Dex, Str, yourHP, maxHP, HPadd, yourATK, stamina, staminaCost, maxStamina = 10, enemyHP, enemyATK, enemyType, choice, killNo, totalKills, deathCount = 0, win;

    static void charStats(){
        yourHP = 10; maxHP = 10; yourATK = 3; stamina = 100; staminaCost = 40; HPadd = 2;
        Int = 1;
        Dex = 1;
        Str = 1;
    }

    static void mainMenu(){
        gameRunning = false;
        System.out.println("Welcome to the Antventure Game!");
        System.out.println("Choose an action:\n[1]Play\n[2]Statistics\n[3]Exit");
        validInput(1, 3);
        switch (choice) {
            case 1: //play
                gameRunning = true;
                charStats();
                story();
                break;
        
            case 2: //Stats
                displayStats();
                mainMenu();
                break;

            case 3: //Exit
                System.exit(0);
                break;
        } 
    }
    
    static void displayStats(){
        scan.nextLine();
        if (charName != null){
            System.out.println("Character Name: " + charName);
            System.out.println("Total Enemies killed: " + totalKills);
            System.out.println("Total Deaths: " + deathCount);
            System.out.println("Total Wins: " + win);
            System.out.println("\nCharacter Stats: ");
            System.out.println("\tStr: " + Str + "\n\tInt: " + Int + "\n\tDex: " + Dex);
        } else {
            System.out.println("You have yet to create a character");
        }

        System.out.println("[1]Back");

        validInput(1, 1);
    }

    static void charSkills(){
        validInput(1, 4);
        
        switch (choice){
            case 1: //Normal Attack
                enemyHP -= yourATK;
                if (stamina<maxStamina){
                    stamina += 10;
                    System.out.println("Stamina +10");
                }
                break;
            case 2: //Magic Attack
                if (stamina < staminaCost){
                    System.out.println("####Your stamina is too low!####"); 
                    charSkills();
                } else {
                    enemyHP -= yourATK+3;
                    stamina -= staminaCost;
                }   
                if (enemyHP<=0 && yourHP<=maxHP){
                    yourHP += HPadd / 3;
                }
                break;
            case 3: //Heal
                if (stamina < staminaCost/2){
                    System.out.println("####Your stamina is too low!####"); 
                    charSkills();
                } else if (yourHP >= maxHP){
                    System.out.println("Your HP is currently at max");
                    charSkills();
                } else {
                    System.out.println("HP + " + HPadd);
                    yourHP += HPadd;
                    stamina -= staminaCost/2;
                }
                break;
            case 4: //Leave
                System.out.println("\"This battle is not for me I guess\"");
                mainMenu();
                break;
        }    
    }

    static void lvlUp(){
        System.out.println("Choose a skill to upgrade:\n(1.)Strength: " + Str + " (Increases ATK)\n(2.)Dexterity: " + Dex + " (Increases HP and HP Increase)\n(3.)Intelligence: " + Int + " (Increases Stamina, Max Stamina, and Magic DMG; Lowers Stamina cost)"); 
        validInput(1, 3);
        switch (choice){
            case 1:
                Str++;
                yourATK++;
                break;
            case 2:
                Dex++;
                maxHP += 2;
                yourHP += 2;
                HPadd += 2;
                break;
            case 3:
                Int++;
                maxStamina += 20;
                stamina += 20;
                staminaCost *= 0.9;
                break;
        } 
    }

    static void enemyStat(int enemyType){
        switch (enemyType) {
            case 1:  //common enemy
                enemyHP = 5; enemyATK = 1;
                break;
            case 2:  //hard enemy
                enemyHP = 15; enemyATK = 3;
                break;
            case 3:  //boss
                enemyHP = 30; enemyATK = 10;
                break;
            default:
                break;
        }
    }

    static void battleSequence(){
        enemyStat(enemyType); //insert enemy stats

        while (yourHP>0 && enemyHP>0){

            System.out.println("\n************************************************");

            System.out.println("The enemy stats are: \nHP: " + enemyHP + "\tATK: " + enemyATK);
            System.out.println("Your stats are: \nHP: " + yourHP + "\tATK: " + yourATK + "\t\tStamina: " + stamina);

            if (stamina>0){
                System.out.println("\nWhat will you do? \n(1.)Normal Attack\t(2.)Magic Attack (ATK+3, Stamina-"+staminaCost+")\n(3.)Heal (HP+"+HPadd+")\t\t(4.)Run away (Exits the program)");
            } else {
                System.out.println("\nWhat will you do? \n(1.)Normal Attack\t(4.)Run away (Restarts the Game)");
            }

            charSkills();

            while (enemyHP>0){ 
                System.out.println("________________________________________________");
                System.out.println("The enemy attacked you! -" + enemyATK + "HP");
                yourHP -= enemyATK;

                if (yourHP<=0){
                    deathCount++;
                    System.out.println("Sorry, you lost...\n(1.)Retry?      (2.)Exit");
                    validInput(1, 2);
                    if (choice == 1){
                        main(null);
                        break;
                    } else if (choice == 2){
                        System.out.println("\"Oh well...\"");
                        mainMenu();
                    }
                    
                } else if (enemyHP<=0){
                    System.out.println(charName + " wins!\n");
                    continue;
                }
                break;
            }
            if (enemyHP<=0){
                switch (enemyType){
                    case 1:
                        System.out.println("\nYou killed " + killNo + " Common Red Ants!");
                        killNo++;
                        totalKills++;
                        continue;
                    case 2:
                        System.out.println("You killed " + (killNo-5) + " Steel Red Ants!");
                        killNo++;
                        totalKills++;
                        continue;
                    case 3: 
                        System.out.println("You killed the Leader Ant!");
                        killNo+=5;
                        totalKills++;
                        win++;
                        continue;
                    default:
                        continue;
                }
            }
        }
    }
    
    static void story(){
        while (gameRunning!=false){
            killNo++; //just to justify the kill count
            System.out.println("Someone: Hello there.\nSomeone: May I ask for your name?");
            scan.nextLine();
            charName = scan.nextLine();

            System.out.println("\nSomeone: Oh hi " + charName + "...");
            System.out.println("**An ambush appeared!**\n5 Common Red Ants appeared!");

            enemyType = 1;
            for (int i = 5; i>0; i--){
                battleSequence();
                System.out.println("Level up!\n");
                lvlUp();
            } 

            System.out.println("\n\n\n\nSomeone: You... you managed to defeat them?");
            System.out.println("Someone: The-theres... more of them...");
            System.out.println("Someone: Here, take this thing\nYou got Steel Claws! (ATK+2)\n\nWhat's your reply?");
            scan.next();
            yourATK += 2;

            System.out.println("\n\n3 Steel Red Ants appeared!");
            enemyType = 2;
            for (int i = 3; i>0; i--){
                battleSequence();
                System.out.println("Level up!\n");
                lvlUp();
            }

            System.out.println("\n\n\nSomeone 2: You killed them???????????");
            System.out.println("Someone 2: Now it's... MY TURN\n----Adrenaline Increased!----\n(ATK+4, Stamina = MAX)");
            yourATK += 4;
            stamina = maxStamina;

            enemyType = 3;
            battleSequence();

            System.out.println("\nNarrator: Okay you win.\nDo you have any reply?");
            scan.next();

            System.out.println("\nA short series of battle ends for this time.");
            gameRunning = false;
        }
    }

    static int validInput(int min, int max){
        choice = -1;

        while (choice < min || choice > max){
            try {
                choice = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Only accepts input of numbers " + min + "-" + max);
                scan.next();
            }
        }
        return choice;
    }
    public static void main (String args[]){
        while (true){
            mainMenu();

            System.out.println("Do you want to play again? \n(1.)Yes\t(2.)No");
            validInput(1, 2);
            if (choice == 2){
                System.exit(0);
            }
            scan.nextLine();
        }
    }
}


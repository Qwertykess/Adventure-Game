import java.util.*;
public class Antventure_game {

    static Scanner scan = new Scanner(System.in);
    static String CharacterName;
    static boolean win = false;
    static int Int, Dex, Str, yourHP, yourATK, stamina, enemyHP, enemyATK, enemyType, choice, killNo = 1, colWidth1 = 30, colWidth2 = 25;

    static void characterStats(){
        yourHP = 10; yourATK = 3; stamina = 10;
        Int = 1;
        Dex = 1;
        Str = 1;
    }

    static void charSkills(int choice){
        switch (choice){
            case 1: //Normal Attack
                enemyHP -= yourATK;
                break;
            case 2: //Magic Attack
                if (stamina < 0){
                    System.out.println("####Your stamina is too low!####\n####You just lost a turn!####"); //modern problems require modern solutions B)
                    break;
                } else {
                    enemyHP -= yourATK+3;
                    stamina -= 6;
                }   
                break;
            case 3: //Heal
                if (stamina < 0){
                    System.out.println("####Your stamina is too low!####\n####You just lost a turn!####"); //modern problems require modern solutions B)
                    break;
                } else {
                    yourHP += 3;
                    stamina -= 4;
                }
                break;
            case 4: //Leave
                System.out.println("\"This battle is not for me I guess\"");
                System.exit(0);
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
                enemyHP = 30; enemyATK = 5;
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
            System.out.println("Your stats are: \nHP: " + yourHP + "\tATK: " + yourATK + "\tStamina: " + stamina);

            if (stamina>=0){
                System.out.println("\nWhat will you do? \n(1.)Normal Attack\t(2.)Magic Attack\n(3.)Heal\t\t(4.)Run away (Exits the program)");
            } else {
                System.out.println("\nWhat will you do? \n(1.)Normal Attack\t(4.)Run away (Exits the program)");
            }

            choice = scan.nextInt();
            charSkills(choice);

            while (enemyHP>0){ 
                System.out.println("________________________________________________");
                System.out.println("The enemy attacked you! -" + enemyATK + "HP");
                yourHP -= enemyATK;

                if (yourHP<=0){
                    System.out.println("Sorry, you lost...\n(1.)Retry?      (2.)Exit");
                    choice = scan.nextInt();
                    if (choice == 1){
                        killNo = 1;
                        choice = 0;
                        main(null);
                    } else if (choice == 2){
                        System.exit(0);
                    }
                    break;
                } else if (enemyHP<=0){
                    System.out.println(CharacterName + " wins!\n");
                    continue;
                }
                break;
            }
            if (enemyHP<=0){
                switch (enemyType){
                    case 1:
                        System.out.println("You killed " + killNo + " Common Red Ants!\t +2 HP!");
                        killNo++;
                        continue;
                    case 2:
                        System.out.println("You killed " + (killNo-5) + " Steel Red Ants!\t +4 HP!");
                        killNo++;
                        continue;
                    case 3: 
                        System.out.println("You killed the Leader Ant!");
                        killNo++;
                        continue;
                    default:
                        continue;
                }
            } else {
                continue;
            }
        }
    }
    
    public static void main (String args[]){
        System.out.println("Someone: Hello there.\nSomeone: May I ask for your name?");
        CharacterName = scan.nextLine();
        characterStats(); //insert your current stats

        System.out.println("\nSomeone: Oh hi " + CharacterName + "...");
        System.out.println("**An ambush appeared!**\n5 Common Red Ants appeared!");

        enemyType = 1;
        for (int i = 5; i>0; i--){
            battleSequence();
            yourHP += 2;
            stamina += 2;
        } 

        System.out.println("\n\n\n\nSomeone: You... you managed to defeat it?");
        System.out.println("Someone: The-theres... more of them...");
        // System.out.println("Someone: Here, take this shit");
        yourATK = 5;

        enemyType = 2;
        for (int i = 3; i>0; i--){
            battleSequence();
            yourHP += 4;
        }

        System.out.println("\n\n\nSomeone 2: You killed them???????????");
        System.out.println("Someone 2: Kill the boss... NOW\n----Adrenaline Increased!----");
        yourATK = 8;

        enemyType = 3;
        battleSequence();
    }

}


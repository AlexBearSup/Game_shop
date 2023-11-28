package org.example.msg;

public enum AllGeneralMsg {
    HEADER_STARTMENU("!========HELLO========!"),
    ENTER_LOGIN("Please enter the login (nickname):"),
    ENTER_PASS("Please enter the password:"),
    CARDTYPE("Please enter card type ( VISA / MASTERCARD )"),
    USER_ADD("User added successfully"),
    ENTER_BIRTHDATE("Entering the date of birth (in the format YYYY-MM-DD)"),
    CREATE_USER_PARAMS("To create a new user, enter the required parameters:"),
    ENTER_NAME("Enter your name:"),
    TOP_UP("How much do you want to top up?"),
    MONEY ("Money in the account:"),
    GREETINGS("===Greetings==="),
    USER_NF("User is not found !"),
    TO_REFILL("To replenish your account you must pass verification !"),
    INCORRECT ("Incorrect input, try again !"),
    EXIT_APP("!==========GOOD BYE==========!"),
    SEPARATION("-------------------------"),
    LIST_GAMES("List of all games: "),
    GAME_BUYING("What games do you want buy ?"),
    NO_MONEY("Not enough money to buy the game:"),
    PURCHASE_OK("Purchase successful."),
    ACCOUNT_MENU("!========ACCOUNT MENU========!"),
    SELECT ("--Select option--"),
    CREATE_USER ("Create new user"),
    LOGIN ("Sign in"),
    VIEW_GAMES ("Show all games"),
    BUY_GAME ("Buy the game"),
    DEPOSIT ("Refill"),
    EXIT ("Exit"),
    TO_HOME("To home page");



    private final String description;

    AllGeneralMsg(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}


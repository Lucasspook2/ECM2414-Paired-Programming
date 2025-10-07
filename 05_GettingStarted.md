## Getting started

As suggested by the coursework specification you should start by reading through all the materials and working out how your backend implementation will be structured (e.g. classes, attributes etc). However I have written the following based on discussions with students in the Monday drop-in support session, to give you some suggested first steps to start the project coding. If you have a query or spot an issue with this document please let me know via email.

### What files you need to code

Your job is to write code that will implement the `GamesLeagueInterface` and form a backend for the `gamesleague` package.

This will consist of a `GamesLeague` class, and additional classes that provide appropriate object types to perform the data storage and processing. For example one sensible decision might be to define a `Player` class to work with player information.

In this way you will need to add code files such as `GamesLeague.java` for the implementation of the `GamesLeagueInterface.java` interface, and additional files like `Player.java` etc that implement the object classes your write.

Note that the `GamesLeague.java` class will manage the whole backend for the program. This means that in addition to implementing the abstract methods of the interface, it will have attributes.

For example you might sensibly add an attribute that is a reference to the list of `Player` instances in the system. When the `GamesLeague` `createPlayer` method is called:

  i.  The constructor of the `Player` class is used to create an `Player` object instance storing the appropriate data
 
  ii. The returned object reference is added to the list of players that exist in the system. 

### Suggested first coding tasks

#### Setting up the workspace 

Ensure that you are working in the GitHub classroom assignment repository (with a name like `gamesleaguecw-.....` that contains `TestSystem` `bin` `build` `doc` `res` `src` `template` folders etc.) and have shared access with your partner: https://classroom.github.com/a/UAOEsRta 

(**Note** make sure you are not trying to work within a "fork" of the instructions repository i.e. https://github.com/My-UofE/ECM1410-2025T2-PairProgrammingCoursework)

You must write a `GamesLeague.java` class that implements `GamesLeagueInterface.java`. 

This must implement all the abstract methods in order to compile. To help you be able to start coding with a valid (but placeholder) implementation I have provided a template `GamesLeague.java` file in the templates directory of your assignment repository.

Copy this file into the `src/gamesleague` directory.

e.g. in the terminal run
```bash
cp ./templates/GamesLeague.java ./src/gamesleague/.
```

 The file contains empty but valid methods so that it will compile.

e.g. the `getPlayerId(email)` method is empty except for a `return 0;` statement.

#### Compiling the binaries

Check that your workspace is set up correctly by compiling the `.java` files of the `./src/gamesleague` folder.

```bash
javac -d ./bin ./src/gamesleague/*.java
```

**Note** When compiling `javac` automatically deals with dependancies so the above is a shortcut to compile all your code, and save the binaries to the `./bin` folder.

If you check the files in the `bin` folder you will see that binaries for the interface (`GamesLeagueInterface`) exceptions (e.g. `InvalidEmailException`), enums (`Status`, `GameType`) and implementation class are created.

#### Testing the package

As you code it is useful to check your code works as expected (compiling != functional). One approach to do this is to make use of a test application that tests the methods you have written.

The following provides starter code for a test application.

In folder `TestSystem` create a file `TestPlayerApp.java`. Within this file put the following code:

```java
import gamesleague.*;

public class TestPlayerApp  {
        public static void main(String[] args) {
            System.out.println("Testing GamesLeague methods for player management");
            GamesLeague gl = new GamesLeague();
        }
}
```

To run the test code use the following commands:

1. Ensure your package is rebuilt with any changes you have made (stores binaries into `bin` folder):

   ```bash
   javac -d ./bin ./src/gamesleague/*.java
   ```

2. Compile the test application (needs the binaries from the `bin` folder):

   ```bash
   javac -cp bin ./TestSystem/TestPlayerApp.java
   ```

3. Run the test application (needs the binaries in the `bin` folder and `TestPlayerApp.class` file):

   ```bash
   java -cp bin:TestSystem TestPlayerApp 
   ```



If you have the code set up correctly you should see the output:

```
Testing GamesLeague methods for player management
```

#### Implementing Player management

To start the project we can note that we don't need to implement the full set of functionality for the whole system, but can work on different aspects and build the project in stages.

As noted above a sensible option is to define a `Player` class to store player information, and my suggestion is to start by writing and testing the methods that deal with player management (ignoring the methods that deal with league management and scoring).

For example you could start by developing the `Player.java` class and code in `GamesLeague.java` the player management code to implement the following methods one-by-one:

 - `createPlayer(email, displayName, name, phone) `
 - `getPlayerIds()`
 - `getPlayerId(email)`
 - `getPlayerEmail(playerId)`
 - `getPlayerDisplayName(playerId)`
 - `updatePlayerDisplayName(playerId, displayName)`
 - `getPlayerJoinDate(playerId)`


**HINT** 

Look at the tutorial for working with an example java project, in which `MyServiceImplementation` manages a set of `Element` objects, which are created with id and name attributes. 

You should be able to use this example to help get started with the player management (e.g. `Player` objects correspond to `Element` objects, and `GamesLeague` manages players in the same way `MyServiceImplementation` manages elements.   

An example of a test application code to check the creation and storage of player data is provided below:

```java
import gamesleague.*;
import java.util.Arrays;

public class TestPlayerApp  {
        public static void main(String[] args) {
            System.out.println("Testing GamesLeague methods for player management");
            GamesLeague gl = new GamesLeague();
            int newPlayerId = gl.createPlayer("noone@noone.com", "Phil", "Philip Lewis", "+44796123456");
            System.out.println("Created new player with id: " + newPlayerId);
            System.out.println("Player list: " + Arrays.toString(gl.getPlayerIds()));
        }
}
```

#### Implementing League management

Once you have the player management working you could next work on implementing the league management system, but ignoring the methods that relate to entering, processing or retrieving scores.

e.g. These might include the following methods:

- `createLeague(owner, name, gameType)`
- `getLeagueName(leagueId)`
- `updateLeagueName(leagueId, newName)`
- `removeLeague(leagueId)`
- `getLeagueOwners(leagueId)`
- `addOwner(leagueId, playerId)`
- `removeOwner(leagueId, playerId)`
- `getPlayerOwnedLeagues(playerId)`
- `getLeagueStatus(leagueId)`
- `setLeagueStartDate(leagueId, day)`
- `setLeagueEndDate(leagueId, day)`

Once you have completed the methods that relate to player and league management, you can then deal with implementing the remaining methods related to scoring, league points, league tables.  

# ECM1410-2025T2-PairProgrammingCoursework

#### Patching instructions

Please run the following commands in your CodeSpace to download and run the patch with the documentation/exception fixes. (Make sure to commit and push any code you have been working on).

 1. Download the patch

```
curl -O -J -L https://github.com/My-UofE/ECM1410-2025T2-PairProgrammingCoursework/raw/refs/heads/main/patch_v1.zip
```

2. unzip into .classroom directory then delete zip

```
unzip -o patch_v1.zip; rm patch_v1.zip
```

3. run patch to automatically update GamesLeague.java.

Please note and where necessary manually update your file with any changes that could not be made.

```
bash .classroom/apply_patch_v1.sh
```

Please see the coursework specification. 

This activity is to write a `gamesleague` package in java with the functionality to set up and calculate leagues in which groups of friends can register scores from games such as Wordle and access the resulting league tables of results.

The complete `gamesleague` package will comprise of:

**1.** frontend code. This is the code to run a user interface for creating player accounts, managing leagues and viewing results. **You are not be required to code this**.

**2.** backend code. This will implement a specified interface (see `GamesLeagueInterface.java`) by which the front end can enter and retrieve system data. Your backend should include a `GamesLeague` class that implements the interface, and defines appropriate OOP object classes to managing, store and process the system data. **This is the task assigned to you and your partner**.

Youu can see how this is structured in the `UserInterface.md` file.

In your code repository you will be provided with the starter code files as stored in the `gamesleague` folder.

In terms of coding your tasks are:

i. analyse the provided materials and implement a suitable classes and methods to 
store and process the player and league information and scores using object orientated programming principles.

ii. write a class `GamesLeague` that implements the interface methods as specified in the `GamesLeagueInterface.java` 


You may not edit any of the provided files (otherwise your code will not meet the specified requirements).

As is common with IT projects it may be necessary to make revisions/corrections to refine the specifications (e.g. if a issue or potential issue with the operation of the proposed structure is identifed ). These will be noted in the `ChangeLog.md` file.

**It is your responsibility to regularly check this file and note any changes to the specification.**



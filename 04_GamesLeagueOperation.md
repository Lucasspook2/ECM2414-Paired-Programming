## How the complete GamesLeague program should work 

The following is provided to provide you with a description of how the GamesLeague program will be used in real life.

Note you are only writing the backend of the package, and not the front end user interface or game code as described below.

In this example we imagine a communal computer in the Computer Science staff room in which the GamesLeague program is set up to run. We will assume that the players visit the staff room 7 days a week(?!).

We will look at how a single league for a daily DICEROLL game is played, with 4 players: ALICE, BEN, CASPER, DOT

Each day during a coffee break the players in the league can log in to the communal computer and select to play their daily game(s) and/or view the league status.

### The DICEROLL game

A round of a DICEROLL game is similar to a turn in Yatzee, and game players roll six dice, three times.

On the first roll they roll all six dice.

On the second and third rolls they can choose which dice to roll and which dice to hold.

After the final roll players can remove any dice that form a consecutive sequence of three or more numbers.

The score for the day is the total of the remaining dice.

Some example dice rolls and scores are below:

 - roll: `1,2,2,3,3,6`  scores  `11`  (seq `1,2,3` removed)
 - roll: `1,1,2,3,4,6`  scores   `7`  (seq `1,2,3,4` is removed)
 - roll: `1,2,2,3,3,4`  scores  ` 0`  (seq `1,2,3` and `2,3,4` removed)
 - roll: `1,1,2,2,5,6`  scores  `17`  (no dice can be removed)
 - roll: `1,2,3,4,5,6`  scores   `0`  (seq `1,2,3,4,5,6` removed)


Each day a round of play takes place. 

Players who do not participate in a round are assigned a score of 36. 

League points are allocated using the game score results. 

In the DICEROLL game the player with the lowest game score wins (i.e. ranking is in order of score low to high). 

The player(s) in the group with the lowest game score are awarded 3 league points, and the player(s) in the group with the next lowest score are awarded 1 league point. 


### Example of daily play:

##### MONDAY 8:30am PROGRAM START UP

8:30am System is turned on and the GamesLeague program is started.

The first action the program takes each day is to run through any rounds in progress (i.e. where a player in the league did not play the previous day). 

It will then finalise the round scores and call `registerDayScores()` with the previous day's results.

-----

##### MONDAY 10:15am BEN PLAYS
BEN logs in, plays daily DICEROLL league game, with result `1,2,4,4,5,6` scoring `7`.

DICEROLL games engine calls `registerGameReport()` with string indicating BEN's roll and score.

DICEROLL checks if daily round is complete i.e. all players have a registered score (not yet!)

-----

##### MONDAY 10:30am ALICE PLAYS
10:30am ALICE logs in, plays daily DICEROLL league game, with result `1,4,4,4,5,5` scoring `23`.

DICEROLL games calls `registerGameReport()` with string indicating ALICE's roll and score.

DICEROLL checks if daily round is complete i.e. all players have a registered score (not yet!)

-------

##### MONDAY 10:35am CASPER PLAYS
CASPER logs in, plays daily DICEROLL league game, with result `1,4,5,5,6,6` scoring 12

DICEROLL games calls `registerGameReport()` with string indicating CASPER's roll and score.
DICEROLL checks if daily round is complete i.e. all players have a registered score (not yet!)

------

##### MONDAY 11:45am DOT PLAYS
11:45am DOT logs in, plays daily DICEROLL league game, with result `1,1,3,4,5,6` scores `2`.

DICEROLL game engine calls `registerGameReport()` with string indicating DOT's roll and score.

DICEROLL checks if daily round is complete i.e. all players have a registered score (Yes!)

DICEROLL calls `registerDayScores()` with the scores ALICE 23, BEN 7, CASPER 12, DOT 2

After playing DOT views the daily league results.

 - DICEROLL game calls `getDayScores()` to show daily game scores:   
   ```
   ALICE 23, BEN  7, CASPER 12, DOT  2
   ```
 - DICEROLL game calls `getDayPoints()` to show daily league points: 
   ```
   ALICE 3, BEN  0, CASPER 1, DOT  0
   ```
 - DICEROLL game calls `getDayRankings()` to show daily league ranking which is based on league points: 
   ```
   ALICE 1, BEN  3, CASPER 2, DOT  3
   ```

### Example results after one week of play

**Daily scores (league points in brackets)**

|Player |  ALICE  | BEN  |  CASPER | DOT  | 
|:--|:-:|:-:|:-:|:-:|
| Monday      | 23 (0) | 7 (1) | 12 (0) |  2 (3) | 
| Tuesday     | 3 (3) | 19 (1) | 19 (1) |  19  (1) | 
| Wednesday   | 9 (0) | 2 (1) | 1 (3) |  11 (0) | 
| Thursday    | 13 (1) | 9 (0) | 14 (0) |  12 (3) | 
| Friday      | 0 (3) | 5 (0) | 2 (0) |  1  (1) | 
| Saturday    | 2 (3) | 2 (3) | 7 (1) |  7  (1) | 
| Sunday      | 8 (1) | 0 (3) | 18 (0) |  18 (0) | 

<br>

------

<br>

**Weekly league points table**

| Player: |  ALICE  | BEN  |  CASPER | DOT  | 
|:--|:-:|:-:|:-:|:-:|
| Monday      | 0 | 1  | 0  |  3 | 
| Tuesday     | 3  | 1  | 1  |  1 | 
| Wednesday   | 0  | 1  | 3  |  0 | 
| Thursday    | 1  | 0  | 0  |  3 | 
| Friday      | 3  | 0  | 0  |  1 | 
| Saturday    | 3  | 3  | 1  |  1 | 
| Sunday      | 1  | 3  | 0  |  0 | 
| TOTAL       | 12  | 12  | 5  |  9 | 

<br>

------
<br>

**Weekly ranking**

- **1st** ALICE, BEN (tie on 12 points)
- **3rd** DOT (9 points)
- **4th** CASPER (5 points)


## About patch_v1

This patch makes some fixes to clarify the documentation and some inconsistencies in the listed exceptions.

To patch your `GamesLeague.java` file first ensure you have committed your code and pushed to GitHub.

Next run the following in the terminal (while within the main directory):

```
bash ./.classroom apply_patch_v1.sh
```

This script uses find/replace targets to update the documentation/exceptions.

v 0.3.1 to v 0.3.3

If the patch does not work you can manually update your GamesLeague.java file using the following:

-------

UPDATES: For consistency with other methods `getPlayerEmail` method should throw `IDInvalidException` (not `IllegalEmailException`).

```java
     /**
     * Get the player's email.
     * 
     * @param playerId The ID of the player being queried.
     * @return The email of the player.
     * @throws IDInvalidException If the email does not match to any player in the system.
     * 
     */
    public String getPlayerEmail(int playerId) throws IDInvalidException{"""
```
    
-------

UPDATES: `getPlayerId` method should return -1 if player does not exist. Method will not throw exception.

```java
    /**
     * Get the player id from the email.
     *
     * @param email The email of the player.
     * @return The ID of the player in the system or -1 if the player does not exist.
     */
    public int getPlayerId(String email){
 ```

-------

UPDATE: `createLeague` corrected method definition with correct exceptions to match documentation

```java
    public int createLeague(int owner, String name, GameType gameType ) 
        throws  IDInvalidException, 
                InvalidNameException, 
                IllegalNameException{
```

--------

UPDATE: `getLeagueStatus` clarification of the the league status method

```java
    /**
     * Get the status of a league. Your code should look at the current local date
     * as epoch day and compare it with any start and end dates that have been set for the league
     * Note that leagues are created without a specified start/end date
     * 
     *  - If the league has start date is in the future (or no start date specified)
     *    the status should be PENDING
     * 
     *  - If the league has a specified start date in the past and 
     *    a specified end date in the future (or no specified end date) then 
     *    the status should be IN_PROGRESS
     * 
     *  - If the league has a specified end date in the past then
     *    the status should be CLOSED
     * 
     * @param leagueId The ID of the league being queried.
     * 
     * @return The status of the league as enum as above
     *          PENDING       not yet started
     *          IN_PROGRESS   league is active
     *          CLOSED        current date is after specified league end date 
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public Status getLeagueStatus(int leagueId ) 
        throws IDInvalidException{
```

--------

UPDATE: `setLeagueEndDate` corrected method definition with correct exceptions to match documentation

```java
    public void setLeagueEndDate(int leagueId, int day) 
        throws IDInvalidException, IllegalOperationException{
```

--------

UPDATE: `cloneLeague` corrected method definition with correct exceptions to match documentation

```java
    public int cloneLeague(int leagueId, String newName) 
        throws IDInvalidException, IllegalNameException{
```

--------

UPDATE: `isLeaguePlayerActive` removed unnecessary exception

```java
    /**
     * Checks if player is active in the league.
     * 
     * @param leagueId The ID of the league.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the league.
     */
    public boolean isLeaguePlayerActive(int leagueId, int playerId) 
        throws IDInvalidException{
```

---------

UPDATE: `setLeaguePlayerInactive` corrected method definition with correct exceptions to match documentation

```java
public void setLeaguePlayerInactive(int leagueId, int playerId) 
    throws IDInvalidException, IllegalOperationException {
```

--------

UPDATE: `setLeaguePlayerActive` corrected method definition with correct exceptions to match documentation

```java
public void setLeaguePlayerActive(int leagueId, int playerId) 
    throws IDInvalidException, IllegalOperationException{

```

---------

UPDATE: `getDayStatus` Minor typo correction

```java
     * @return The status of the day as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        gameplay ended as all games played or day ended   
     */
    public Status getDayStatus(int leagueId, int day ) 
```

----------

OTHER UPDATES: 

 - corrected documentation to remove references to WordLeaguePortal / GamesLeaguePortal which should refer to GamesLeague instance.
 - added missing `@throws` in some of the documentation parts

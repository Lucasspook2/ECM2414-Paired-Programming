import os, sys
from pathlib import Path

        
T0 = """/**
 * GamesLeague Class Template
 * You can use this template and add the corect method code.
 * At present it is a skeleton template with placeholder methods 
 * so that it can compile and implements all methods required by GamesLeagueInterface
 *
 * @author Philip Lewis
 * @version 0.3.1
 */"""

T0FIX = """/**
 * GamesLeague Class Template
 * You can use this template and add the correct method code.
 * At present it is a skeleton template with placeholder methods 
 * so that it can compile and implements all methods required by GamesLeagueInterface
 *
 * @author Philip Lewis
 * @version 0.3.3
 */"""

T1 = """/**
     * Get the player's email.
     * 
     * @param playerId The ID of the player being queried.
     * @return The email of the player.
     * @throws IllegalEmailException If the email does not match to any player in the system.
     * 
     */
    public String getPlayerEmail(int playerId) throws IllegalEmailException{"""

T1FIX = """/**
     * Get the player's email.
     * 
     * @param playerId The ID of the player being queried.
     * @return The email of the player.
     * @throws IDInvalidException If the email does not match to any player in the system.
     * 
     */
    public String getPlayerEmail(int playerId) throws IDInvalidException{"""


T2 = """    /**
     * Permenantly deactivates player account.
     * <p>
     * Note to preserve the integrity of the league tables the removal process should follow:
     * i) all personal player anonymised with playerId placeholders
     * ii) all player gameplay reports are set to empty strings
     * iii) player is set to in in all league memberships
     *
     * @param playerId The ID of the player to be deactivated.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * @throws IllegalOperationException If the player is the sole owner of a league.
     */"""

T2FIX = """    /**
     * Permenantly deactivates player account.
     * <p>
     * Note to preserve the integrity of the league tables the removal process should follow:
     * i) all personal player anonymised as below:
     *     - name & displayName to "anonymousplayerX" where X is playerId
     *     - email is set to "" and phone is set to ""
     * ii) all player gameplay reports are set to empty strings
     * iii) player is set as inactive in all their league memberships
     *
     * @param playerId The ID of the player to be deactivated.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * @throws IllegalOperationException If the player is the sole owner of a league.
     */"""

T3 = """    /**
     * Get the player id from the email.
     *
     * @param email The email of the player.
     * @throws IDInvalidException If the email does not match to any player in the system.
     * @return The ID of the player in the system or null if the player does not exist.
     */"""


T3FIX = """    /**
     * Get the player id from the email.
     *
     * @param email The email of the player.
     * @return The ID of the player in the system or -1 if the player does not exist.
     */"""


T4 = """    public int createLeague(int owner, String name, GameType gameType ) throws IDInvalidException{"""

T4FIX = """    public int createLeague(int owner, String name, GameType gameType ) 
        throws  IDInvalidException, 
                InvalidNameException, 
                IllegalNameException{"""

T5 = """    /**
     * Get the status of a league 
     * 
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return The status of the league as enum
     *          PENDING       not yet played
     *          IN_PROGRESS   active 
     *          CLOSED        ended  
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public Status getLeagueStatus(int leagueId ) throws IDInvalidException{"""

T5FIX = """    /**
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
        throws IDInvalidException{"""

T6 = """    public void setLeagueEndDate(int leagueId, int day) throws IDInvalidException{"""


T6FIX = """    public void setLeagueEndDate(int leagueId, int day) 
        throws IDInvalidException, IllegalOperationException{"""

T7 = """    public int cloneLeague(int leagueId, String newName) throws IDInvalidException{"""

T7FIX = """    public int cloneLeague(int leagueId, String newName) 
        throws IDInvalidException, IllegalNameException{"""

T8 = """    /**
     * Checks if player is active in the league.
     * 
     * @param leagueId The ID of the league.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the ID does not match to any player in the league.
     */
    public boolean isLeaguePlayerActive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalArgumentException{"""

T8FIX = """    /**
     * Checks if player is active in the league.
     * 
     * @param leagueId The ID of the league.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the league.
     */
    public boolean isLeaguePlayerActive(int leagueId, int playerId) 
        throws IDInvalidException{"""


T9 = """    public void setLeaguePlayerInactive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalArgumentException {"""

T9FIX = """    public void setLeaguePlayerInactive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalOperationException {"""


T10 = """    public void setLeaguePlayerActive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalArgumentException{"""



T10FIX = """    public void setLeaguePlayerActive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalOperationException{"""



T11 = """     * Get the status of league games for a given day.
     * with results ordered to match player array returned by getLeaguePlayers().
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return The status of the day as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        ended all games played or month ended"""

T11FIX = """     * Get the status of league games for a given day.
     * with results ordered to match player array returned by getLeaguePlayers().
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return The status of the day as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        when all players have played or day has ended"""

T12    = """*         InvalidDateException"""


T12FIX = """* @throws InvalidDateException"""

T13 = """GamesLeaguePortal"""

T13FIX = """GamesLeague instance"""

T14 = """     * The state of this WordLeaguePortal must be unchanged if any
     * exceptions are thrown."""


T14FIX = """     * """



patches = [[T0,T0FIX],
           [T1,T1FIX],
           [T2,T2FIX],
           [T3,T3FIX],
           [T4,T4FIX],
           [T5,T5FIX],
           [T6,T6FIX],
           [T7,T7FIX],
           [T8,T8FIX],
           [T9,T9FIX],
           [T10,T10FIX],
           [T11,T11FIX],
           [T12,T12FIX],
           [T13,T13FIX],
           [T14,T14FIX]
           ]

def do_patch_v1(filepath):
     # Read in the file
     with open(filepath, 'r') as file:
          filedata = file.read()

     # Replace the target string
     for patch in patches:
          filedata_patched = filedata.replace(patch[0], patch[1])
          if filedata_patched==filedata and patch[1] not in filedata:
               print("Issue changing from")
               print(patch[0])
               print("To:")
               print(patch[1])
          filedata = filedata_patched

     # Write the file out again
     with open(filepath, 'w') as file:
          file.write(filedata)

filepath = Path('./src/gamesleague/GamesLeague.java')
do_patch_v1(filepath)



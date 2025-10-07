### User Interface diagram

The front end for interacting with the Games League system is shown below. 

**You do not need to implement this interface, but it is provided so that you can understand how the overall system is intended to work.**


- welcome menu: 
    |
    |--------------> create account
    |
    |--------------> log in


- create account: 
    |
    |-----> enter display name 
        --> enter email 
        --> enter name
        --> accept terms and conditions
              |
              |----> confirm ----> main menu           
              |
              |----> cancel  ----> welcome menu


- log in: 
     |
     |----> enter (valid) player email ----> main menu      


- main menu: 
    |
    |----> active rounds (play/view) ----> play menu
    |
    |----> view invitations ----> invitation menu       
    |
    |----> view my leagues ----> league selection menu
    |
    |----> view my stats
    | 
    |----> create league
    |
    |----> account management
    |
    |----> log out ---> welcome menu
 

- view invitations
    |
    show open invitations and request action   
        |    
        |----> accept invitation         
        |
        |----> reject invitation              
        |       
        |----> back to main menu


- create league
    |
    |----> select game
       --> set league options
       --> invite users
            |
            |----> confirm ----> main menu      
            |
            |----> cancel  ----> main menu


- play menu ----> show active leagues and request action            
                |                  
                |                     
                |     YOU DO NOT HAVE      ---> view round (results)
                |   TO IMPLEMENT THE GAME!              
                |           ^
                |           |                                        
                |----> link to play game 
                |
                |----> view round (results)
                |                
                |----> back to main menu


- view round ----> shows current round          
                            |
                            |----> back 1 round
                            |
                            |----> forward 1 round
                            | 
                            |----> view league table (week)
                            |
                            |----> exclude round from scores (owner only)
                            |
                            |----> back to main menu
 

- view league table ---> shows current league table (defaults to weekly Mon-Sun)
          |
          |----> back
          |
          |----> forward
          |
          |----> toggle timespan between week Mon-Sun (default) / month / all-time
          |
          |----> view round (current)
          |
          |----> back to main


- league management ----> select league (show all owned)
                               |
                               |----> set league start date
                               |
                               |----> set league end date
                               |
                               |----> reset league
                               |
                               |----> send invite
                               |
                               |----> set player inactivate / active
                               | 
                               |----> add owner
                               |
                               |----> delete owner
                               |
                               |----> delete league
                               |
                               |----> back to main


- account management 
    |
    |----> update display name
    |
    |----> update email
    |
    |----> update name
    |
    |----> update league status (toggle between active and inactive) 
    |
    |----> delete account (removes personal data and permenantly deactivates player)
    |
    |----> back to main
    



# My Personal Project : Formula 1 Fantasy

This application is essentially a fantasy sport game where users can create teams and select drivers to add to their team.
Users are allowed to make as much teams as they would like.

Features of this application include allowing users to view a _created team_, its _selected drivers_,
the _driver's statistics_, and _load existing data_ of a previously created team.

Growing up I find myself interested in playing video games. I used to spend hours everyday playing
games in my PC. As I get older, I think about how the games I play are made, wondering upon the millions of code lines that
are written "behind the scene".
Recently, I've found myself watching and keeping up with Formula 1 Racing. Despite people asking me,
_"What's so fun watching people drive around in circles?"_ I really enjoy the sport and every race weekend excites me 
as I hear the V6 engines of the fastest cars ever built. 

I have decided for this project that I would like to combine these
two interests of mine and make a fantasy application for Formula 1.
This application won't be as complex and sophisticated as the games I've enjoyed playing in the past (Minecraft, Fortnite, Valorant, etc),
but I like to think of this personal project as baby steps to who knows? perhaps a possible future as a game developer.


## User Stories:

- As a user, I want to be able to create a new F1 team and add it to a list of F1 teams (a league)
- As a user, I want to be able to select an F1 team and add a new driver to the team or remove an existing driver from the team
- As a user, I want to be able to select a team and view a list of drivers on that team
- As a user, I want to be able to view my current team value (in millions $)
- As a user, I want to have the option to save the drivers I added to a team
- As a user, I want to have the option to load my saved teams and drivers
- 

Phase 4: Task 2 - Sample of event log

Thu Nov 30 14:25:56 PST 2023
Created league: F1 Fantasy

Thu Nov 30 14:26:08 PST 2023
Created team: Team 1

Thu Nov 30 14:26:08 PST 2023
Successfully added team: Team 1

Thu Nov 30 14:26:17 PST 2023
Successfully added Max Verstappen to Team 1

Thu Nov 30 14:26:18 PST 2023
Successfully added Sergio Perez to Team 1

Thu Nov 30 14:26:20 PST 2023
Successfully added Lewis Hamilton to Team 1

Thu Nov 30 14:26:22 PST 2023
Failed to add Lewis Hamilton to Team 1

Thu Nov 30 14:26:24 PST 2023
Failed to add George Russell to Team 1

Thu Nov 30 14:26:27 PST 2023
Successfully added Oscar Piastri to Team 1

Thu Nov 30 14:26:31 PST 2023
Created team: Team 2

Thu Nov 30 14:26:31 PST 2023
Successfully added team: Team 2

Thu Nov 30 14:26:37 PST 2023
Successfully added Charles Leclerc to Team 2

Thu Nov 30 14:26:39 PST 2023
Successfully added Carlos Sainz to Team 2

Thu Nov 30 14:26:41 PST 2023
Successfully added Fernando Alonso to Team 2

Thu Nov 30 14:26:44 PST 2023
Removed Fernando Alonso

Process finished with exit code 0

Phase 4: Task 3

If given more time to work on the project, I think several areas could use some refactoring and improvement. 
One possible refactoring could be implementing a more streamlined communication between the UI components and the data model.
Currently, the FantasyUI class handles a significant amount of logic, including UI setup, event handling, and interactions with the
League, Team, and Driver classes. I want to refactor my code by using a design model that improves 
code readability and maintainability by reducing coupling. The design would involve separating the data and logic from the UI,
as well as the event handling. This change would also facilitate easier modifications or extensions in the future.

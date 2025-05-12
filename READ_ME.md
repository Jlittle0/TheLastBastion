The Last Bastion:

High Level Overview:
At a high level, my project loads the user into the menu screen, which is constantly updating
based on user key and mouse input, allowing them to either enter the game, exit the game,
open the tutorial, or open the settings. Though currently the only two "working" buttons are
for the game and for quitting, the other two still technically function. Once the user begins 
playing the game, they are greeted with the first level, which consists of 1 wave made which
contains a single enemy wolf. This enemy wolf follows the path and ideally would reach the
end square before decreasing the life counter on the top left of the screen, however, due to
time constaints, it does not and bugs out after moving part of the way. The user is also unable
to place towers.

The core mechanics that actually exist in the game are 
- Updating and Redrawing the screen based on the current state of the game 
- Reading in wave files and creating the corresponding wave objects for each level
- Generating the enmies inside each wave file and giving them animations
- Allowing the enemies to move in accordance to the path on a given level/map and turn.

Missing mechanics that *should* have been implemented:
- Multiple waves
- The ability to place towers on the grid
- Proper pathfinding for the enemies that allows them to reach the end of the path
- Tutorial menu and settings menu
- Multiple enemy and tower types


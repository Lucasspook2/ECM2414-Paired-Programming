#!bash

timestamp=$(date -d "today" +"%Y%m%d%H%M%S")

if which python3 &>/dev/null
then 
mkdir -p ./.backups
cp ./src/gamesleague/GamesLeague.java ./.backups/GamesLeague${timestamp}.java
cp ./src/gamesleague/GamesLeagueInterface.java ./.backups/GamesLeagueInterface${timestamp}.java
cp ./.classroom/GamesLeagueInterface.java ./src/gamesleague/.
cp ./.classroom/GamesLeague.java ./templates/.

python3 .classroom/patch_v1.py
echo Patched ./src/gamesleague/GamesLeague.java 
echo Original file can be found in .backups folder 
else echo Python not installed
echo Please run the following to install Python on CodeSpaces 
echo sudo apt update; sudo apt install python3
fi

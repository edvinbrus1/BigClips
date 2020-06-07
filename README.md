# BigClips

Big-Clips is a game programmed in Java and JavaFX. The game contains a story which the player the player can read throughout the games progression. The idea behind it is to create a larger game that controls several smaller games that the player must play before "unlocking" the next part of the story.

So far three smaller games have been implemented, Space Invaders and Hänga gubbe programmed in JavaFX and Tre i rad, programmed in Java. The main game is built with JavaFX in which the user interface is made in Scene Builder (FXML documents) and is controlled through a controller class. The game is designed to be scalable so that the story can be built upon and several other smaller games can be added without too much hassle. 

IMPORTANT: The game is optimized for 60hz and it could therefore be a good idea to turn it down to that refresh rate if you are playing the game on a 144hz monitor.

INSTRUKTIONER FÖR ATT STARTA SPELET: Det första man måste göra för att kunna starta spelet är att ladda ner projektet som en Zip fil ifrån github. Efter att detta är gjort kan man extrahera filen och skapa ett nytt projekt i IntelliJ "new project from existing sources". Sen måste man se till att ladda ner och installera javaFX från följande sida: https://openjfx.io/openjfx-docs/#modular . När detta är gjort ska man sedan navigera sig till File -> Project Structure -> Libraries och sedan lägga till javaFX mappens lib mapp. Klicka sedan på Apply och OK. 

Slutligen måste man navigera sig till Run -> Edit configurations och lägga till följande VM Options för applikationen StartGame:
--module-path C:\Users\NAMN PÅ ANVÄNDARE\MAPPEN DÄR JAVAFX FINNS\javafx-sdk-11.0.2\lib --add-modules=javafx.controls,javafx.fxml

Utförligare instruktioner för detta finns i länken ovan, och under Release RS3 i projektets Releases.

Sedan kan man starta Big-Clips från antingen Launcher klassen eller StartGame klassen i bigClips mappen.

Länk till GitHub repository: https://github.com/edvinbrus1/BigClips




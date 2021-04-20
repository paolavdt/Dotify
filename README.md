# Dotify HW2 - Activities & Recycler View
Name: Paola Vanegas

## Description
This app currently displays a list of songs -- containing title, artist, and album cover image for each song in the list. Songs are clickable, and when pressed will open a mini player which will show the current song's title and artist, along with a shuffle button. The list of songs can be shuffled if button is pressed. Additionally, when clicking on the mini player, a new screen representing the song player activity will be shown -- displaying basic song information and media controls. 

### Song List
**Shows**
- Song album cover
- Song title 
- Song artist name

**Mini Player**
- Song title 
- Song artist name
- Shuffle button

**Mini Player Actions**
- Mini player container: launch song player 
- Shuffle button: shuffle list of songs

### Song Player
**Shows**
- Song album cover
- Song title 
- Song artist name
- Number of plays 

**Media Controls**
- Play button: increases number of song plays
- Skip previous button: toast message 
- Skip next button: toast message

**Back arrow**
- Navigate back to song list

## Extra Credit Attempted
- Stock Android back arrow button, ←, in the Header/Toolbar of Activity B (Song player)
- When a user presses shuffle, the list should animate the changes using DiffUtil

## Images of App Running in Emulator
<image alt="Image of Dotify song list on emulator" src="./images/SongList.png" heigth="500" />
<image alt="Image of Dotify mini player on emulator" src="./images/MiniPlayer.png" heigth="500" />   
<image alt="Image of Dotify song player on emulator" src="./images/SongPlayer.png" heigth="500" />

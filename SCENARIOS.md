Story: create new games
-----------------------

Scenario: when first visit the game
When I visit the game for the first time
Then I am offered to create a new game

Scenario: when I create a new game
When I choose to create a new game
Then a new game is created
 And I can start playing the new game
 
Scenario: when a game is in progress
Given I am playing a game
When I choose to start a new game
Then the current game is erased
 and a new game starts



Story: deal four cards
----------------------
When the game starts, the dealer deals four face-up cards in the center of the table.

Story: deal three cards to each player
--------------------------------------
When the game starts, the dealer deals three card face-down to both players (the player hands).

Story: play a card
------------------
When I play a card in my hand to the table, if it matches a card, then I conquer both cards.  Otherwise it stays on the table.

### Scenario: non-matching card
Given I have the three of clubs in my hand
  And there is a two of cups on the table
  And my pile is empty
 When I play the three of clubs
 Then the three of clubs and the two of cups are on the table
  And I have nothing in my hand
  And my pile is still empty
  
### Scenario: Capturing a matching card
Given I have the five of clubs in my hand
  And there is a five of cups on the table
 When I play the five of clubs
 Then the table is empty
  And I have nothing in my hand
  And I have the two cards in my pile
  
### Scenario: Capturing two cards by sum
Given I have the five of clubs in my hand
  And there are the two of cups and the three of coins on the table
 When I play the five of clubs
 Then the table is empty
  And I have nothing in my hand
  And I have the three cards in my pile

### Scenario: Two choices for capture
Given I have the five of clubs in my hand
  And there are the five of cups and the five of coins on the table
 When I play the five of clubs 
 Then I can choose to capture either the five of coins or the five of cups

### Scenario: Forced choice for capture
Given I have the five of clubs in my hand
  And there are the five of cups and the two of coins and the three of coins on the table
 When I play the five of clubs 
 Then I have to capture the five of cups



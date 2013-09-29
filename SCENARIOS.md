
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
 When I play the three of clubs
 Then the three of clubs and the two of cups are on the table
  And I have nothing in my hand
  
### Scenario: Capturing a matching card
Given I have the five of clubs in my hand
  And there is a five of cups on the table
 When I play the five of clubs
 Then the table is empty
  And I have nothing in my hand
  And I have the two cards in my deck
  
### Scenario: Capturing two cards by sum
Given I have the five of clubs in my hand
  And there are the two of cups and the three of coins on the table
 When I play the five of clubs
 Then the table is empty
  And I have nothing in my hand
  And I have the three cards in my deck

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



<#macro cardImage source name>
  <img src="/images/cards/${source}.jpg" alt="${name}" title="${name}" width="100px"/>
</#macro>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />    
    <title>Scopa!</title>
    <link rel="stylesheet" href="/stylesheets/style.css" type="text/css" media="screen" />
  </head>
  <body>
    <div id="content">
      <h1>Welcome to Scopa!!!</h1>
      
      <p id="playerCaptures" class="cardPile">
        You have captured ${game.countOfCapturedCards} cards
      </p>

      <p id="deck">
        The deck contains <span id="cards-left-in-the-deck">${game.countOfCardsLeftInTheDeck}</span> cards
      </p>

      <div id="table" class="cardSet">
        <h2>Table</h2>
        <ul>
          <#list game.table as card>
          <li class='card'>
            <@cardImage source=card name=card />
          </li>          
          </#list>
        </ul>
      </div>

      <div id="playerHand" class="cardSet">
        <h2>Your hand</h2>
        <ul>
          <#list game.playerHand as card>
          <li>
            <form method="post">
              <input type="hidden" name="card" value="${card}" />
              <button>
                <@cardImage source=card name=card />
              </button>
            </form>
          </li>
          </#list>
        </ul>
      </div>

      
      <form method="post">
        <input type="submit" name="new-game-command" value="Start New Game" id="new-game-command"/>
      </form>
      
      <address style="font-size: xx-small">
        Cards By Poulpy <a href="http://creativecommons.org/licenses/by-sa/3.0">CC-BY-SA-3.0</a>, via Wikimedia Commons
      </address>
  </div>  
  </body>
</html>

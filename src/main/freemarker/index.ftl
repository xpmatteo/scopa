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
      
      <div id="table" class="cardSet">
        <h2>Table</h2>
        <ul>
          <#list table as card>
          <li class='card'>
            <@cardImage source=card name=card />
          </li>          
          </#list>
        </ul>
      </div>

      <div id="playerHand" class="cardSet">
        <h2>Your hand</h2>
        <ul>
          <#list playerHand as card>
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

      <div id="playerCaptures" class="cardPile">
        You have captured 0 cards
      </div>
      
      <address style="font-size: xx-small">
        Cards By Poulpy <a href="http://creativecommons.org/licenses/by-sa/3.0">CC-BY-SA-3.0</a>, via Wikimedia Commons
      </address>
  </div>  
  </body>
</html>

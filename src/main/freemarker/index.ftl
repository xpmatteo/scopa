<#macro card source name>
  <img src="/images/cards/${source}" alt="${name}" width="100px"/>
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
      
      <img src="/images/cards/coins-01.jpg" alt="Asso di Denari" width="100px"/>
      <img src="/images/cards/coins-02.jpg" alt="Due di Denari" width="100px"/>
      <img src="/images/cards/coins-03.jpg" alt="Tre di Denari" width="100px"/>
      <@card "coins-04.jpg" "Quattro di Denari" />
      <address style="font-size: xx-small">
        Cards By Poulpy <a href="http://creativecommons.org/licenses/by-sa/3.0">CC-BY-SA-3.0</a>, via Wikimedia Commons
      </address>
  </div>  
  </body>
</html>

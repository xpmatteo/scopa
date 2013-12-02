<#macro cardImage theCard>
  <img src="/images/cards/${theCard.toParam()}.jpg" alt="${theCard.name()}" title="${theCard.name()}" width="60" />
</#macro>

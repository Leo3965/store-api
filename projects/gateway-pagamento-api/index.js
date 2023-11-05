const express = require('express')
const bodyParser = require('body-parser')
const app = express()
const port = 3000

const jsonParser = bodyParser.json()
const urlencodedParser = bodyParser.urlencoded({ extended: false })

app.get('/', (req, resp) => {
  resp.send({
    status: 'Running',
    message: 'Yoooooo!',
  })
})

const millisecondsToWait = 3000
app.post('/', jsonParser, (req, resp) => {
  const card = req.body.card
  setTimeout(function () {
    resp.send({
      valid: true,
      card,
    })
  }, millisecondsToWait)
})

app.listen(port, () => {
  console.log(`Listening on port ${port}`)
})

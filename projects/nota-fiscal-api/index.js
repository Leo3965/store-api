const express = require('express')
const bodyParser = require('body-parser')
const app = express()
const port = 3001

const jsonParser = bodyParser.json()

app.get('/', (req, resp) => {
  resp.send({
    status: 'Running - fiscal note',
    message: 'Yoooooo!',
  })
})

const millisecondsToWait = 1200
app.post('/', jsonParser, (req, resp) => {
  setTimeout(function () {
    resp.send({
      created: true,
      fiscalNote: Date.now(),
    })
  }, millisecondsToWait)
})

app.listen(port, () => {
  console.log(`Listening on port ${port}`)
})

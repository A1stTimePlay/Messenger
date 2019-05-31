const express = require('express')
const app = express()
const bodyParser = require('body-parser')
require('dotenv').load()
const port = process.env.PORT || 3000

http = require('http'),
server = http.createServer(app),
io = require('socket.io').listen(server);

app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json())

let routes = require('./api/routes') //importing route
routes(app)

io.on('connection', (socket) => {

    console.log('user connected')

    socket.on('messagedetection', (senderID, receiverID, messageContent, sentDate) => {

       //log the message in console

       console.log(senderID+" :" +messageContent)
        //create a message object
       let  message = {"senderID": senderID, "receiverID":receiverID, "messageContent": messageContent, "sentDate": sentDate}
          // send the message to the client side
       io.emit('message', message );

    });

    socket.on('disconnect', function() {
        console.log( ' user has left ')
    });

});

app.use(function(req, res) {
    res.status(404).send({url: req.originalUrl + ' not found'})
})

server.listen(3000,()=>{

console.log('Node app is running on port 3000');

});
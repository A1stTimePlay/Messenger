'use strict';
module.exports = function(app) {
  let accountCtrl = require('./controllers/AccountController');
  let messageCtrl = require('./controllers/MessageController');


  app.route('/Account')
    .get(accountCtrl.get)
    .post(accountCtrl.store);

  app.route('/Account/:AccountId')
    .get(accountCtrl.detail)
    .put(accountCtrl.update)
    .delete(accountCtrl.delete);

  app.route('/Message')
    .get(messageCtrl.get)
    .post(messageCtrl.store);

  app.route('/Message/Receiver:ReceiverID')
    .get(messageCtrl.GetReceiver);

  app.route('/Message/Sender:SenderID')
    .get(messageCtrl.GetSender);

  app.route('/Message/Receiver:ReceiverID/Sender:SenderID')
    .get(messageCtrl.getMessageForReceiverWhenSender)
};
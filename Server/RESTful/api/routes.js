'use strict';
module.exports = function(app) {
  let accountCtrl = require('./controllers/AccountController');
  let messageCtrl = require('./controllers/MessageController');
  let friendlistCtrl = require('./controllers/FriendListController');


  app.route('/Accounts')
    .get(accountCtrl.get)
    .post(accountCtrl.store);

  app.route('/Account/:AccountId')
    .get(accountCtrl.detail)
    .put(accountCtrl.update)
    .delete(accountCtrl.delete);

  app.route('/Messages')
    .get(messageCtrl.get)
    .post(messageCtrl.store);

  app.route('/Message/Receiver:ReceiverID')
    .get(messageCtrl.GetReceiver);

  app.route('/Message/Sender:SenderID')
    .get(messageCtrl.GetSender);

  app.route('/Message')
    .get(messageCtrl.getMessageListBetweenAB);
	
  app.route('/FriendList')
    .get(friendlistCtrl.getFriendList);

};
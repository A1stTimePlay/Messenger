'use strict';
module.exports = function(app) {
  let accountCtrl = require('./controllers/AccountController');
  let messageCtrl = require('./controllers/MessageController');

  // todoList Routes
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

  app.route('/Message/:MessageId')
    .get(messageCtrl.detail);
};
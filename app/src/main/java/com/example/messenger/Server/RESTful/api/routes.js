'use strict';
module.exports = function(app) {
  let accountCtrl = require('./controllers/AccountController');

  // todoList Routes
  app.route('/Account')
    .get(accountCtrl.get)
    .post(accountCtrl.store);

  app.route('/Account/:AccountId')
    .get(accountCtrl.detail)
    .put(accountCtrl.update)
    .delete(accountCtrl.delete);
};
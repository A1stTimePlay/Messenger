'use strict'

const util = require('util')
const mysql = require('mysql')
const db = require('./../db')

module.exports = {
    // Lấy danh sách bạn bè của account
    getFriendList: (req, res) => {
        let sql = 'Select * from FriendList where AccountID= ?'
        db.query(sql, [req.query.AccountID], (err, response) => {
            if (err) throw err
            res.json(response)
        })
    },
}
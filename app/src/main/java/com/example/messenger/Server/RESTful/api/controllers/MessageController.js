'use strict'

const util = require('util')
const mysql = require('mysql')
const db = require('./../db')

module.exports = {
    get: (req, res) => {
        let sql = 'SELECT * FROM message'
        db.query(sql, (err, response) => {
            if (err) throw err
            res.json(response)
        })
    },

    GetReceiver: (req, res) => {
        let sql = 'SELECT * FROM message WHERE ReceiverID = ?'
        db.query(sql, [req.params.ReceiverID], (err, response) => {     // db.query dc truyen vao lenh sql, "?" dc thay bang tham so trong ngoac vuong
            if (err) throw err
            res.json(response)
        })
    },

    GetSender: (req, res) => {
        let sql = 'SELECT * FROM message WHERE SenderID = ?'
        db.query(sql, [req.params.SenderID], (err, response) => {
            if (err) throw err
            res.json(response)
        })
    },

    getMessageForReceiverWhenSender: (reg, res) => {
        let sql = 'SELECT * FROM message Where ReceiverID = ? and SenderID = ?'
        db.query(sql, [reg.params.ReceiverID, reg.params.SenderID], (err, response) => {
            if (err) throw err
            res.json(response)
        })
    },


    store: (req, res) => {
        let data = req.body;
        let sql = 'INSERT INTO message SET ?'
        db.query(sql, [data], (err, response) => {
            if (err) throw err
            res.json({message: 'Insert success!'})
        })
    },
}
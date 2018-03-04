// initialize front-end
var express = require('express');
const favicon = require('express-favicon');

var path = require('path');
var serveStatic = require('serve-static');
app = express();
app.use(serveStatic(__dirname + "/dist"));
app.use(favicon(__dirname + '/static/favicon.png'));
var port = process.env.PORT || 5000;
app.listen(port);
console.log('server started '+ port);
var path = require('path')

function resolve (dir) {
  return path.join(__dirname, '..', dir)
}

var webpackConfig = {
  resolve: {
    alias: {
      'src': resolve('src'),
      'assets': resolve('src/assets'),
      'components': resolve('src/components')
    }
  },
  module: {
    rules: []
  }
}


if(process.env.ENABLE_ESLINT && process.env.ENABLE_ESLINT === 'true'){
  webpackConfig.module.rules.unshift(esLintRule) //add eslint
}

module.exports = webpackConfig

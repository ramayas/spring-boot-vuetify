module.exports = {
  devServer: {
    proxy: {
      '/api/teachers': {
        target: 'http://localhost:8099',
        secure: false
      }
    }
  }
}

const PROXY_CONFIG = [
  {
    context: [
      '/api/**',
      '/**'
    ],
    target: "http://localhost:8700",
    secure: false
  }
]
module.exports = PROXY_CONFIG;

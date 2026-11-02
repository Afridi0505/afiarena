module.exports = {
  '/api': {
    target: 'http://localhost:8080',
    secure: false,
    changeOrigin: true,
    pathRewrite: { '^/api': '' },
    configure(proxy) {
      // Angular 19 uses Vite's proxy. Spring httpBasic adds this header on a
      // 401; removing it prevents the browser's native credentials popup.
      proxy.on('proxyRes', (proxyRes) => delete proxyRes.headers['www-authenticate']);
    },
  },
};

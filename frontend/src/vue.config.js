module.exports = {
    "transpileDependencies": [
      
    ],
  
    "runtimeCompiler": true,
  
    devServer: {
      port:8081,
      proxy: {
        '^/api': {
          target: 'http://localhost:8083',
          ws: true,
         
          changeOrigin: true
        }
      }
    },
  
    outputDir: '../../../target/front/public',
  
  }
    
  
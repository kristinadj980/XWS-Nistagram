module.exports = {
    "transpileDependencies": [
      
    ],
  
    "runtimeCompiler": true,
  
    devServer: {
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
    
  
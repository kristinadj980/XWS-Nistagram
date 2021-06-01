import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueRouter from 'vue-router';

Vue.use(VueAxios, axios)
Vue.use(VueRouter)

Vue.prototype.$axios = axios;
Vue.config.productionTip = false

const baseURL = 'http://localhost:8081/api';
if (typeof baseURL !== 'undefined') {
  Vue.axios.defaults.baseURL = baseURL;
}

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

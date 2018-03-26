import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import axios from 'axios';
import VueAxios from 'vue-axios'
import LightBootstrap from './light-bootstrap-main'
import routes from './routes/routes'
import BootstrapVue from 'bootstrap-vue'
import http from 'http'

Vue.use(VueAxios, axios);
Vue.use(VueRouter)
Vue.use(LightBootstrap)
Vue.use(BootstrapVue);
Vue.use(VueAxios, axios);
Vue.use(require('@websanova/vue-upload'));
Vue.axios.defaults.baseURL = 'https://peaceful-hamlet-75445.herokuapp.com'; //update to backend after testing

// configure router
const router = new VueRouter({
  routes,
  linkActiveClass: 'nav-item active',
  mode: 'history'
})

Vue.router = router

Vue.use(require('@websanova/vue-auth'), {
    auth: require('@websanova/vue-auth/drivers/auth/bearer.js'),
    http: require('@websanova/vue-auth/drivers/http/axios.1.x.js'),
    router: require('@websanova/vue-auth/drivers/router/vue-router.2.x.js'),
    refreshData: {url: '', method: 'GET', enabled: false, interval: 30},
    loginData: {url: 'login', method: 'POST', redirect: 'admin/user', fetchUser: false},
    fetchData: {url: 'login', method: 'POST', enabled: false},
    logoutData: {url: 'logout', method: 'POST', redirect: '/logout', makeRequest: true},
    registerData: {url: '/register', method: 'POST', redirect: '/login'},
    authRedirect: {path: 'login'},
    token: [{request: 'Authorization', response: 'Authorization', authType: 'bearer', foundIn: 'header'}, {request: 'token', response: 'token', authType: 'bearer', foundIn: 'response'}],
    tokenExpired: false,
});

new Vue({
  el: '#app',
  render: h => h(App),
  router,
  data: {
    selfId: -1,
    // serverURL: 'http://localhost:8080'
    serverURL: 'https://peaceful-hamlet-75445.herokuapp.com'
  }
})

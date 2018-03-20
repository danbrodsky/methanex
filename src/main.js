import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import axios from 'axios';
import VueAxios from 'vue-axios'
import VueAuth from 'vue-auth'
import BootstrapVue from 'bootstrap-vue'

Vue.use(VueAxios, axios);
Vue.axios.defaults.baseURL = 'https://peaceful-hamlet-75445.herokuapp.com'; //update to backend after testing

// Vue.axios.defaults.baseURL = 'https://api-demo.websanova.com/api/v1'; //update to backend after testing

// LightBootstrap plugin
import LightBootstrap from './light-bootstrap-main'

// router setup
import routes from './routes/routes'
// plugin setup
Vue.use(VueRouter)
Vue.use(LightBootstrap)
Vue.use(BootstrapVue);

// configure router
const router = new VueRouter({
  routes, // short for routes: routes
  linkActiveClass: 'nav-item active',
  mode: 'history'
})

Vue.router = router

// Vue.use(VueAxios, axios)
// Vue.use(VueAuth, {
//     auth: require('@websanova/vue-auth/drivers/auth/bearer.js'),
//     http: require('@websanova/vue-auth/drivers/http/axios.1.x.js'),
//     router: require('@websanova/vue-auth/drivers/router/vue-router.2.x.js')
// });

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
    tokenExpired: false
});

new Vue({
  el: '#app',
  render: h => h(App),
  router,
  data: {
    // serverURL: 'http://localhost:8080'
    serverURL: 'https://peaceful-hamlet-75445.herokuapp.com'
  }
})

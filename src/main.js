import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import VueRouter from 'vue-router'
import App from './App.vue'
import axios from 'axios';
import VueAxios from 'vue-axios'
import LightBootstrap from './light-bootstrap-main'
import routes from './routes/routes'
import BootstrapVue from 'bootstrap-vue'
import * as Cookies from 'js-cookie'
import VuejsDialog from "vuejs-dialog"

Vue.use(VueAxios, axios);
Vue.use(VuejsDialog);
Vue.use(VueRouter);
Vue.use(LightBootstrap);
Vue.use(BootstrapVue);
Vue.use(VueAxios, axios);
Vue.use(require('@websanova/vue-upload'));
Vue.axios.defaults.baseURL = 'https://peaceful-hamlet-75445.herokuapp.com';
//Vue.axios.defaults.baseURL = 'http://localhost:8080'
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
    loginData: {url: 'login', method: 'POST', redirect: '', fetchUser: false},
    fetchData: {url: 'login', method: 'POST', enabled: false},
    logoutData: {url: 'logout', method: 'POST', redirect: '/login', makeRequest: true},
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
    cookies: Cookies,
    serverURL: 'https://peaceful-hamlet-75445.herokuapp.com'
    // serverURL: 'http://localhost:8080'
  }
})

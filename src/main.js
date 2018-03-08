import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import axios from 'axios';
import VueAxios from 'vue-axios'
import VueAuth from '@websanova/vue-auth'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(VueAxios, axios);
Vue.axios.defaults.baseURL = 'https://api-demo.websanova.com/api/v1'; //update to backend after testing

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

Vue.use(VueAxios, axios)
Vue.use(VueAuth, {
    auth: require('@websanova/vue-auth/drivers/auth/bearer.js'),
    http: require('@websanova/vue-auth/drivers/http/axios.1.x.js'),
    router: require('@websanova/vue-auth/drivers/router/vue-router.2.x.js')
});

new Vue({
  el: '#app',
  render: h => h(App),
  router
})

<template>
  <div>
    <h1>Login</h1>

    <b>Test users:</b> (admin / password)

    <hr/>
    <div>
      <b-alert :show=showInvalidLoginBanner dismissible variant="danger">
        <h4 class="alert-heading">Invalid Credentials</h4>
        <p>
          Please log in with a valid username and/or password.
        </p>
      </b-alert>
    </div>

    <form v-on:submit.prevent="login()">
      <table>
        <tr>
          <td>Username:</td>
          <td><input v-model="data.body.username"/></td>
        </tr>
        <tr>
          <td>Password:</td>
          <td><input v-model="data.body.password" type="password"/></td>
        </tr>
        <tr>
          <td></td>
          <td><label><input v-model="data.rememberMe" type="checkbox"/> Remember Me</label></td>
        </tr>
        <tr>
          <td></td>
          <td><label><input v-model="data.fetchUser" type="checkbox"/> Fetch User (test)</label></td>
        </tr>
        <tr>
          <td></td>
          <td>
            <button type="submit">Login</button>
            <router-link :to="{name: 'Register'}">register</router-link>
          </td>
        </tr>
      </table>

      <hr/>

      <div v-show="error" style="color:red; word-wrap:break-word;">{{ error }}</div>
    </form>
  </div>
</template>

<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        showInvalidLoginBanner: false,
        context: 'login context',
        data: {
          body: {
            username: 'admin',
            password: 'password'
          },
          rememberMe: false,
          fetchUser: false
        },
        error: null
      };
    },
    methods: {
      login() {
        let info = this;
        this.$auth.login({
          data: this.data.body, // Axios
          fetchUser: this.data.fetchUser
        })
          .then(() => {
            info
              .axios
              .get(info.$root.serverURL + "/user?username=" + info.data.body.username)
              .then((response) => {
                console.log(response.data);
                sessionStorage.setItem("selfId", response.data);
              })
              .catch(() => console.log("error getting resource"));
            info.$auth.redirect();
          }, (res) => {
            this.showInvalidLoginBanner = true;
          });
      }
    }
  }
</script>

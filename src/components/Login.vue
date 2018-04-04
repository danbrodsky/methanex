<template>
  <div class="container">
  <div class="card card-container">
    <h2>Login</h2>

    <b>Test users:</b> (a@b.com / test1234)

    <hr/>
    <div>
      <b-alert :show=showInvalidLoginBanner dismissible variant="danger">
        <h4 class="alert-heading">Invalid Credentials</h4>
        <p>
          Please log in with a valid username and/or password.
        </p>
      </b-alert>
    </div>

    <form v-on:submit.prevent="login()" class="form-signin">
        <input v-model="data.body.username" placeholder="Username" id="inputEmail" type="text" required autofocus/>
        <input v-model="data.body.password" placeholder="Password" id="inputPassword" type="password" required/>

      <div id="remember" class="checkbox">
        <label id="checkbox-label1"><input v-model="data.rememberMe" type="checkbox"/> Remember Me</label>
      </div>
      <hr/>
      <button type="submit" class="btn btn-primary btn-lg btn-block btn-fill">Login</button>
      <router-link :to="{name: 'Register'}" id="login_register_btn">register</router-link>

      <div v-show="error" style="color:red; word-wrap:break-word;">{{ error }}</div>
    </form>
  </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        showInvalidLoginBanner: false,
        context: 'login context',
        data: {
          body: {
            username: 'a@b.com',
            password: 'test1234'
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
                console.log("fsfsdfsf");
                info.$root.$data.cookies.set('user',response.data);
                info.$router.push("admin");
              })
              .catch(() => console.log("error getting resource ID"));
          }, (res) => {
            this.showInvalidLoginBanner = true;
          });
      }
    }
  }
</script>

<style>

  .card-container.card {
    max-width: 350px;
    padding: 40px 40px;
  }

  .card {
    background-color: #fafafa;
    /* just in case there no content*/
    padding: 20px 25px 30px;
    margin: 0 auto 25px;
    margin-top: 50px;
    /* shadows and rounded borders */
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 2px;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  }

  .form-signin #inputEmail,
  .form-signin #inputPassword {
    direction: ltr;
    height: 44px;
    font-size: 16px;
    padding: 10px;
  }

  .form-signin input[type=password],
  .form-signin input[type=text] {
    width: 100%;
    display: block;
    margin-bottom: 10px;
    z-index: 1;
    position: relative;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
  }


  #login_register_btn {
    float: right;
    padding: 10px;
  }

  #checkbox-label1, #checkbox-label2 {
    padding: 10px;
    margin-bottom: 0px;
  }


</style>

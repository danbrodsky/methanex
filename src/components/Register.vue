<template>
  <div class="container">
    <div>
      <b-alert :show=data.accountFoundBanner dismissible variant="warning">
        <h4 class="alert-heading">An account was found with these login credentials. Please use try a different username or password.</h4>
      </b-alert>
    </div>
    <div class="card card-container">
        <h2>Register</h2>
        <hr/>

        <form v-on:submit.prevent="register()" class="form-signin">
          <input v-model="data.body.username" placeholder="Username" id="inputEmail" type="text" required autofocus/>
          <input v-model="data.body.password" placeholder="Password" id="inputPassword" type="password" required/>

          <div>

          </div>

            <hr/>
          <button type="submit" class="btn btn-primary btn-lg btn-block btn-fill">Register</button>
            <router-link :to="{name: 'Login'}" id="login_register_btn">back to login</router-link>
            <div v-show="error" style="color:red; word-wrap:break-word;">{{ error }}</div>
            </form>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
        data() {
            return {
                context: 'register context',

                data: {
                  accountFoundBanner: false,
                    body: {
                        username: '',
                        password: '',
                        avatar: null
                    }
                },
                formData: new FormData(),
                error: null
            };
        },
        methods: {
            setAvatar(e) {
                var _this = this,
                    file = (e.target.files || e.dataTransfer.files)[0],
                    reader = new FileReader();
                reader.onload = (e) => {
                    _this.data.body.avatar = e.target.result;
                };

                reader.readAsDataURL(file);
            },
            register() {
                var newUser = {};
                let info = this;
                newUser['username'] = this.data.body.username;
                newUser['password'] = this.data.body.password;
              axios
                .post(this.$root.serverURL + "/register", newUser)
                .then(() => info.$router.push("login"))
                .catch(() => info.data.accountFoundBanner = true);
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

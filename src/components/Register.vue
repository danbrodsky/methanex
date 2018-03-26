<template>
    <div>
        <h1>Register</h1>
        <hr/>

        <form v-on:submit.prevent="register()">
            <table><tr>
            <tr>
                <td>Username:</td>
                <td><input v-model="data.body.username" /></td>
            </tr><tr>
                <td>Password:</td>
                <td><input v-model="data.body.password" type="password" /></td>
            </tr><tr>
                <td></td>
                <td><label><input v-model="data.autoLogin" type="checkbox" /> Auto Login</label></td>
            </tr><tr>
                <td></td>
                <td><label><input v-model="data.rememberMe" type="checkbox" /> Remember Me</label></td>
            </tr><tr>
                <td></td>
                <td><button type="submit">Register</button></td>
            </tr></table>

            <hr/>

            <div v-show="error" style="color:red; word-wrap:break-word;">{{ error }}</div>
            </form>
    </div>
</template>

<script>
  import axios from 'axios'
  export default {
        data() {
            return {
                context: 'register context',

                data: {
                    body: {
                        username: '',
                        password: '',
                        avatar: null
                    },
                    autoLogin: false,
                    rememberMe: false
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
                newUser['username'] = this.data.body.username;
                newUser['password'] = this.data.body.password;
                // this.$auth.register({
                //     data: newUser,
                //     autoLogin: this.data.autoLogin,
                //     rememberMe: this.data.rememberMe,
                //     success: function () {
                //         console.log('success ' + this.context);
                //     },
                //     error: function (res) {
                //         console.log('error ' + this.context);
                //         this.error = res.data;
                //     }
                // });
              axios
                .post(this.$root.serverURL + "/register", newUser)
                .then(() => console.log("success"))
                .catch(() => console.log("fail"));
            }
        }
    }
</script>

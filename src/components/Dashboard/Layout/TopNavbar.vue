<template>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <div class="collapse navbar-collapse justify-content-end">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a href="#" class="nav-link">
                <span v-show="$auth.check()">
                  <a v-on:click="logout()" href="javascript:void(0);">Logout</a>
              </span>
              <span v-show="$auth.impersonating()">
                &bull;
                <a v-on:click="unimpersonate()" href="javascript:void(0);">(logout)</a>
              </span>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
  export default {
    computed: {
      routeName() {
        const {name} = this.$route
        return this.capitalizeFirstLetter(name)
      }
    },
    data() {
      return {
        activeNotifications: false
      }
    },
    methods: {
      capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1)
      },
      toggleNotificationDropDown() {
        this.activeNotifications = !this.activeNotifications
      },
      closeDropDown() {
        this.activeNotifications = false
      },
      toggleSidebar() {
        this.$sidebar.displaySidebar(!this.$sidebar.showSidebar)
      },
      hideSidebar() {
        this.$sidebar.displaySidebar(false)
      },
      logout() {
        // this.$auth.logout({
        //   makeRequest: true,
        //   success() {
        //     console.log('success ' + this.context);
        //   },
        //   error() {
        //     console.log('error ' + this.context);
        //   }
        // });
        localStorage.removeItem('default_auth_token');
        this.$router.push("/login");
      },
      unimpersonate() {
        this.$auth.unimpersonate({
          success() {
            console.log('success ' + this.context);
          },
          error() {
            console.log('error ' + this.context);
          }
        });
      }
    }
  }

</script>
<style>

</style>

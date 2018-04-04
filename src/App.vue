<template>
  <div :class="{'nav-open': $sidebar.showSidebar}">
    <notifications></notifications>
<template>
    <div>
            <router-view></router-view>
    </div>
</template>
  </div>
</template>

<script>
  export default {}
</script>
<style lang="scss">

  @import url('https://fonts.googleapis.com/css?family=Raleway');

  html, body, h1, .h1, h2, .h2, h3, .h3, h4, .h4, h5, .h5, h6, .h6, p, .navbar, .brand, .btn-simple, .alert, a, .td-name, td, button.close {
    font-family: 'Raleway', sans-serif !important;
  }

  p {
    margin-bottom: 0;
  }

  .vgt-wrap {
    margin-bottom: 10px;
  }

  .main-panel {
    background-color: transparent;
  }

  .content {
    background-image: url("/footer-bg.png");
    background-repeat: no-repeat;
    background-size: auto;
  }

  .vue-notifyjs.notifications{
    .list-move {
      transition: transform 0.3s, opacity 0.4s;
    }
    .list-item {
      display: inline-block;
      margin-right: 10px;

    }
    .list-enter-active {
      transition: transform 0.2s ease-in, opacity 0.4s ease-in;
    }
    .list-leave-active {
      transition: transform 1s ease-out, opacity 0.4s ease-out;
    }

    .list-enter {
      opacity: 0;
      transform: scale(1.1);

    }
    .list-leave-to {
      opacity: 0;
      transform: scale(1.2, 0.7);
    }
  }
</style>

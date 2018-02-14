<template>
  <div class="wrapper">
    <side-bar>
      <sidebar-link to="/admin/portfolio-selection">
        <i class="nc-icon nc-bullet-list-67"></i>
        <p>Portfolio Selection</p>
      </sidebar-link>
      <sidebar-link to="/admin/overview">
        <i class="nc-icon nc-chart-pie-35"></i>
        <p>Portfolio Manager</p>
      </sidebar-link>
      <sidebar-link to="/admin/user">
        <i class="nc-icon nc-circle-09"></i>
        <p>Profile</p>
      </sidebar-link>
      <sidebar-link to="/admin/table-list">
        <i class="nc-icon nc-notes"></i>
        <p>resource list</p>
      </sidebar-link>
      <sidebar-link to="/admin/project1">
        <i class="nc-icon nc-single-copy-04"></i>
        <p>Project 1</p>
      </sidebar-link>
      <sidebar-link to="/admin/project2">
        <i class="nc-icon nc-single-copy-04"></i>
        <p>Project 2</p>
      </sidebar-link>
      <sidebar-link to="/admin/notifications">
        <i class="nc-icon nc-bell-55"></i>
        <p>Notifications</p>
      </sidebar-link>
    </side-bar>
    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content @click="toggleSidebar">

      </dashboard-content>

      <content-footer></content-footer>
    </div>
  </div>
</template>
<style lang="scss">

</style>
<script>
  import TopNavbar from './TopNavbar.vue'
  import DashboardContent from './Content.vue'
  export default {
    components: {
      TopNavbar,
      DashboardContent
    },
    methods: {
      toggleSidebar () {
        if (this.$sidebar.showSidebar) {
          this.$sidebar.displaySidebar(false)
        }
      }
    }
  }

</script>

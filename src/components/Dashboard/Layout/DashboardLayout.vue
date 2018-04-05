<template>
  <div class="wrapper">
    <side-bar>
      <sidebar-link to="/admin/portfolio-selection">
        <i class="fa fa-globe"></i>
        <p>Portfolio Selection</p>
      </sidebar-link>
      <sidebar-link to="/admin/project-selection">
        <i class="fa fa-briefcase"></i>
        <p>Project Selection</p>
      </sidebar-link>
      <sidebar-link to="/admin/user">
        <i class="fa fa-user-circle-o"></i>
        <p>Profile</p>
      </sidebar-link>
      <sidebar-link to="/admin/skills">
        <i class="fa fa-magic"></i>
        <p>Skill</p>
      </sidebar-link>
      <sidebar-link to="/admin/table-list">
        <i class="fa fa-address-book-o"></i>
        <p>resource list</p>
      </sidebar-link>
      <sidebar-link to="/admin/notifications">
        <i class="fa fa-bell"></i>
        <p>Notifications</p>
      </sidebar-link>
      <sidebar-link to="/admin/reports">
        <i class="fa fa-file-text"></i>
        <p>Reports</p>
      </sidebar-link>
      <sidebar-link to="/admin/statistics">
        <i class="fa fa-line-chart"></i>
        <p>Statistics</p>
      </sidebar-link>
    </side-bar>
    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content @click="toggleSidebar">

      </dashboard-content>

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

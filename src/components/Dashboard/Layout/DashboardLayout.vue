<template>
  <div class="wrapper">
    <side-bar>
      <sidebar-link to="/admin/portfolio-selection">
        <i class="nc-icon nc-bullet-list-67"></i>
        <p>Portfolio Selection</p>
      </sidebar-link>
      <sidebar-link to="/admin/user">
        <i class="nc-icon nc-circle-09"></i>
        <p>Profile</p>
      </sidebar-link>
      <sidebar-link to="/admin/skills">
        <i class="nc-icon nc-ruler-pencil"></i>
        <p>Skill</p>
      </sidebar-link>
      <sidebar-link to="/admin/table-list">
        <i class="nc-icon nc-notes"></i>
        <p>resource list</p>
      </sidebar-link>
      <sidebar-link to="/admin/notifications">
        <i class="nc-icon nc-bell-55"></i>
        <p>Notifications</p>
      </sidebar-link>
      <sidebar-link to="/admin/reports">
        <i class="nc-icon nc-notes"></i>
        <p>Reports</p>
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

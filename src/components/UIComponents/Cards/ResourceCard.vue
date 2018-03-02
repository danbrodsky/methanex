<template>
  <div class="card" v-on:click="goToResource">
    <div class="card-header">
      <div name="header" style="align: center"><span style="font-weight:bold">{{ resourceName }}</span></div>
      <div name="header" style="font-weight:bold; color:#888888"></div>
    </div>
    <div class="card-body">
      <div class="body-data">
          <span>Status: {{ status }}</span>
      </div>
      <div class="body-data">
          <span><i class="fa fa-globe"></i> {{ location }}</span>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'resource-card',
    props: {
        resourceName: {
            type: String,
            default: 'John Doe'
        },
        status: {
            type: String,
            default: 'Available'
        },
        location: {
            type: String,
            default: 'Canada'
        }
    },
    data () {
        return {}
    },
    methods: {
      goToResource () {
        this.$router.push({path: '/admin/table-list'});
      }

    }
  }

</script>
<style scoped>
    div.card {
        min-width: 280px;
    }
    div.body-data {
        padding-top: 0.5em;
        padding-bottom: 0.5em;
    }
</style>

<template>
  <div class="card" v-on:click="goToResource">
    <div class="card-header">
      <div name="header" style="align: center"><span style="font-weight:bold">{{ resourceName }}</span></div>
      <div name="header" style="font-weight:bold; color:#888888"></div>
    </div>
    <div class="card-body">
      <div class="body-data">
          <span>Status: {{ getResourceStatus }}</span>
      </div>
      <div class="body-data">
          <span><i class="fa fa-globe"></i> {{ getLocation }}</span>
      </div>
      <div class="body-data">
        <span>
          Group: {{ getGroup }}
        </span>
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
        },
        group: {
            type: String,
            default: 'N/A'
        }
    },
    data () {
        return {}
    },

    computed: {
      getResourceStatus: function(){
        if(!this.status){
          return "N/A";
        }
        return this.status;
      },

      getLocation: function(){
        if(!this.location){
          return "N/A";
        }
        return this.location;
      },

      getGroup: function(){
        if(!this.group){
          return "N/A";
        }
        console.log("this.group:");
        console.log(this.group);
        console.log(this.group == "null");
        return this.group;
      }
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

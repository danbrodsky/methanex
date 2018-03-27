<template>
  <div class="content">
    <div class="container-fluid">
      <card>
        <template slot="header">
          <h4 class="card-title">Notifications</h4>
        </template>
        <div class="row">
          <div class="col-md-12">
            <NotificationRow v-for="(notification,index) of receivedNotifications"
                             v-bind:skillName="notification.skill.name"
                             v-bind:skillId="notification.skill.id"
                             v-bind:resourceName="notification.resource.name"
                             v-bind:resourceId="notification.resource.id"
                             v-bind:managerId="notification.manager.id"
                             v-bind:key="notification.id"
                             v-bind:id="index"
                             v-bind:notification="notification"
                             v-on:notification-remove="remove"
                             v-bind="notifications">
            </NotificationRow>
          </div>
        </div>
      </card>
    </div>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import NotificationRow from 'src/components/UIComponents/Cards/NotificationRow.vue'
  import axios from 'axios'

  export default {
    components: {
      Card,
      NotificationRow
    },
    created() {
      this.fetchData();
    },
    data() {
      return {
        type: ['', 'info', 'success', 'warning', 'danger'],
        notifications: {
          topCenter: false
        },
        receivedNotifications: []
      }
    },
    methods: {
      fetchData() {
        let info = this;
        axios.get(this.$root.serverURL + "/api/notifications?managerId=" + JSON.parse(info.$root.$data.cookies.get('user')).id) // add manager id here when there's login
          .then(response => {
            console.log(response.data);
            this.receivedNotifications = response.data;
          })
      },
      remove(id) {
        this.receivedNotifications.splice(id, 1);
      }
    }
  }

</script>
<style lang="scss">

</style>

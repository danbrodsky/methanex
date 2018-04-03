<template>
  <div class="content">
    <div class="container-fluid">
      <card>
        <div>
          <b-alert :show=noNotificationsBanner dismissible>
            <p> There are currently no notifications for you.</p>
          </b-alert>
        </div>
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
        noNotificationsBanner: false,
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
        axios.get(this.$root.serverURL + "/api/notifications?managerId=" + JSON.parse(info.$root.$data.cookies.get('user')).resource.id)
          .then(response => {
            info.receivedNotifications = response.data;
            if (this.receivedNotifications.length == 0) {
              info.noNotificationsBanner = true;
            }
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

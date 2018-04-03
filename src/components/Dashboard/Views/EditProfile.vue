<template>
  <div class="content" style="background-color: #FF69B4">
    <div>
      <b-alert :show=updatedResourceSuccessBanner dismissible variant="success">
        <h4 class="alert-heading">Your information has been updated</h4>
        <p>
          Please refresh the page to view any changes
        </p>
      </b-alert>
    </div>
    <div>
      <b-alert :show=notificationSendBanner dismissible variant="warning">
        <h4 class="alert-heading">A notification was just sent to your manager</h4>
        <p>
          Please wait for approval for your skill to be added
        </p>
      </b-alert>
    </div>
    <card>
      <h4 slot="header" class="card-title">Edit Resource</h4>
      <form>
        <div class="row">
          <div class="col-md-3">
            <fg-input type="text"
                      label="Resource ID"
                      :disabled="true"
                      placeholder="Resource ID"
                      v-model="resource.id">
            </fg-input>
          </div>
          <div class="col-md-5">
            <fg-input type="text"
                      label="Location"
                      placeholder="Location"
                      v-model="resource.location">
            </fg-input>
          </div>
          <div class="col-md-4">
            <label class="typo__label">Status:</label>
            <multiselect v-model="values"
                         placeholder="Pick a status"
                         label="status"
                         track-by="status"
                         :options="statuses"
                         :multiple="true"></multiselect>
            <pre class="language-json"></pre>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <fg-input type="text"
                      label="Name"
                      placeholder="Name"
                      v-model="resource.name">
            </fg-input>
          </div>
          <div class="col-md-6">
            <fg-input type="email"
                      label="Email"
                      placeholder="Email"
                      v-model="resource.email">
            </fg-input>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4">
            <label class="typo__label">Skills:</label>
            <multiselect v-model="selectedSkills"
                         placeholder="Pick a skill(s)"
                         label="name"
                         track-by="name"
                         :options="skills"
                         :multiple="true">
            </multiselect>
            <pre class="language-json"></pre>
          </div>
          <div class="col-md-4">
            <label class="typo__label">Group:</label>
            <multiselect v-model="selectedGroups"
                         placeholder="Pick a group"
                         label="group"
                         track-by="group"
                         :options="groups">
            </multiselect>
            <pre class="language-json"></pre>
          </div>
          <div class="col-md-4">
            <label class="typo__label">Peer Group:</label>
            <multiselect v-model="selectedPeerGroups"
                         placeholder="Pick a peer group"
                         label="peerGroup"
                         track-by="peerGroup"
                         :options="peerGroups">
            </multiselect>
            <pre class="language-json"></pre>
          </div>
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-info btn-fill float-right" style="background-color: #FF69B4" @click.prevent="updateProfile">
            Update Profile
          </button>
        </div>
        <div class="clearfix"></div>
      </form>
    </card>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import Multiselect from 'vue-multiselect'
  import axios from 'axios'

  export default {
    components: {
      Card,
      Multiselect
    },
    data() {
      return {
        updatedResourceSuccessBanner: false,
        notificationSendBanner: false,
        brandImage: null,
        options1: [],
        options2: [],
        values: [],
        resource: {
          id: null,
          name: '',
          email: '',
          location: '',
          group: null,
          peerGroup: null,
          manager: null,
          status: null,
          skills: []
        },
        selectedSkills: [],
        selectedGroups: [],
        selectedPeerGroups: [],
        skills: [],
        groups: [],
        peerGroups: [],
        statuses: []
      }
    },
    created() {
      this.fetchData();
    },
    methods: {
      fetchData() {
        let info = this;
        let pathId = this.$route.params.resourceId;
        pathId = pathId != undefined ? pathId : JSON.parse(info.$root.$data.cookies.get('user')).resource.id;
        axios
          .get(info.$root.serverURL + "/api/resources/" + pathId)
          .then(response => {
            info.resource = response.data;
            info.values = info.resource.skills;
          })
          .catch(error => {
            info.resource = JSON.parse(info.$root.$data.cookies.get('user')).resource;
            info.values = info.resource.skills;
          });
        axios
          .get(info.$root.serverURL + "/api/technicalSkills")
          .then(response => {
            info.skills = response.data;
          })
          .catch(error => console.log(error));
        axios
          .get(info.$root.serverURL + "/api/groups")
          .then(response => {
            info.groups = response.data;
          })
          .catch(error => console.log(error));
        axios
          .get(info.$root.serverURL + "/api/peergroups")
          .then(response => {
            info.peerGroups = response.data;
          })
          .catch(error => console.log(error));
        axios
          .get(info.$root.serverURL + "/api/resourcestatuses")
          .then(response => {
            info.statuses = response.data;
          })
          .catch(error => console.log(error));
      },
      updateProfile() {
        var info = this;
        axios.put(info.$root.serverURL + "/api/resources/" + info.resource.id, {
          "id": info.resource.id,
          "name": info.resource.name,
          "email": info.resource.email,
          "location": info.resource.location,
          "group": info.resource.group,
          "manager": info.resource.manager,
          "status": info.resource.status
        })
          .then(function () {
            info.fetchData();
            let notifications = [];
            let currentSkills = info.resource.skills;
            info.updatedResourceSuccessBanner = true;
            if (info.resource.manager != null) {
              info.values.forEach(value => {
                let res = currentSkills.every(skill => skill.id != value.id);
                if (res) {
                  let notification = {};
                  notification['skill'] = value;
                  notification['resource'] = info.resource;
                  notifications.push(notification);
                }
              });
              if (notifications.length > 0) {
                axios.post(info.$root.serverURL + "/api/notifications", notifications)
                  .then(function (res) {
                    info.notificationSendBanner = true;
                  })
                  .catch(() => console.log("error while adding notifications"));
              }
            }
            else {
              alert("bro you have no manager");
            }
          })
          .catch(() => console.log("error while updating resource"));
      },
    }
  }

</script>
<style>
</style>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>

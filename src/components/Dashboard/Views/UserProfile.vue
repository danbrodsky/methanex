<template>
  <div class="content">
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
            <label for="selStatus">Status</label>
            <select v-model="resource.status" class="form-control" id="selStatus">
              <option selected>Normal</option>
              <option>On Vacation</option>
              <option>Training</option>
              <option>Terminated</option>
            </select>
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
            <fg-input type="text"
                      label="Role"
                      placeholder="Role"
                      v-model="resource.role">
            </fg-input>
          </div>
          <div class="col-md-4">
            <label for="selGroup">Group</label>
            <select v-model="resource.group" class="form-control" id="selGroup">
              <option>Alpha</option>
              <option>Bravo</option>
              <option selected>Charlie</option>
              <option>Delta</option>
            </select>
          </div>
          <div class="col-md-4">
            <label for="selPeerGroup">Peer Group</label>
            <select v-model="resource.peerGroup" class="form-control" id="selPeerGroup">
              <option selected>Full-Stack</option>
              <option>QA</option>
              <option>Dev-ops</option>
            </select>
          </div>
        </div>
        <div>
          <label class="typo__label">Skills:</label>
          <multiselect v-model="values"
                       placeholder="Pick a skill(s)"
                       label="name"
                       track-by="name"
                       :options="options1"
                       :multiple="true"></multiselect>
          <pre class="language-json"></pre>
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-info btn-fill float-right" @click.prevent="updateProfile">
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
          id: -1,
          name: '',
          email: '',
          location: '',
          group: '',
          manager: '',
          status: '',
          skills: []
        },
        files: [],
        headers: {}
      }
    },
    created() {
      var token = 'Bearer ' + this.$auth.token('default_auth_token');
      this.headers['Authorization'] = token;
      this.fetchData();
    },
    methods: {
      fetchData() {
        let info = this;
        axios
          .get(info.$root.serverURL + "/api/resources/" + JSON.parse(info.$root.$data.cookies.get('user')).id)
          .then(response => {
            info.resource = response.data;
            console.log("info.values");
            console.log(info.values);
            info.values = info.resource.skills;
          })
          .catch(error => {
            info.resource = JSON.parse(info.$root.$data.cookies.get('user'));
            info.values = info.resource.skills;
          })
        axios
          .get(info.$root.serverURL + "/api/technicalSkills")
          .then(response => {
            info.options1 = response.data;
            console.log("info.options1");
            console.log(info.options1);
          })
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
            info.values.forEach(value => {
              let res = currentSkills.every(skill => skill.id != value.id);
              if (res) {
                let notification = {};
                notification['skill'] = value;
                notification['resource'] = info.resource;
                notifications.push(notification);
              }
            });
            axios.post(info.$root.serverURL + "/api/notifications", notifications)
              .then(function (res) {
                info.notificationSendBanner = true;
              })
              .catch(() => console.log("error while adding notifications"));
          })
          .catch(() => console.log("error while updating resource"));
      },
    }
  }

</script>
<style>
</style>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>

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
      <h4 slot="header" class="card-title">Profile</h4>
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
                      :disabled="true"
                      label="Location"
                      placeholder="Location"
                      v-model="resource.location">
            </fg-input>
          </div>
          <div class="col-md-4">
            <fg-input type="status"
                      :disabled="true"
                      label="Status"
                      placeholder="Status"
                      v-model="resource.status">
            </fg-input>
          </div>
        </div>

        <div class="row">
          <div class="col-md-4">
            <fg-input type="text"
                      :disabled="true"
                      label="Name"
                      placeholder="Name"
                      v-model="resource.name">
            </fg-input>
          </div>
          <div class="col-md-4">
            <fg-input type="text"
                      :disabled="true"
                      label="Manager"
                      placeholder="Manager"
                      v-model="resource.manager">
            </fg-input>
          </div>
          <div class="col-md-4">
            <fg-input type="text"
                      :disabled="true"
                      label="Email"
                      placeholder="Email"
                      v-model="resource.email">
            </fg-input>
          </div>
        </div>
        <div class="col-md-4">
          <fg-input type="group"
                    :disabled="true"
                    label="Group"
                    placeholder="Group"
                    v-model="resource.group">
          </fg-input>
        </div>
        <div class="col-md-4">
          <fg-input type="peerGroup"
                    :disabled="true"
                    label="Peer Group"
                    placeholder="Peer Group"
                    v-model="resource.peerGroup">
          </fg-input>
        </div>
        <div class="row">
          <div class="col-md-4">
            <label class="typo__label">Skills:</label>
            <ul id="example-1" v-if="resource.skills.length > 0">
              <li v-for="item in resource.skills">
                {{ item.name }}
              </li>
            </ul>
            <ul id="example-2" v-else>
              <li>
                No skills
              </li>
            </ul>
          </div>
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
        let pathId = this.$route.params.resourceId;
        pathId = pathId != undefined ? pathId : JSON.parse(info.$root.$data.cookies.get('user')).resource.id;
        axios
          .get(info.$root.serverURL + "/api/resources/" + pathId)
          .then(response => {
            info.resource = response.data;
            info.values = info.resource.skills;
            if (info.resource.manager) {
              info.resource.manager = info.resource.manager.name;
            }
            else {
              info.resource.manager = "N/A";
            }
            if (info.resource.status) {
              info.resource.status = info.resource.status.name;
            }
            else {
              info.resource.status = "N/A";
            }
            if (info.resource.group) {
              info.resource.group = info.resource.group.name;
            }
            else {
              info.resource.group = "N/A";
            }
            if (info.resource.peerGroup) {
              info.resource.peerGroup = info.resource.peerGroup.name;
            }
            else {
              info.resource.peerGroup = "N/A";
            }
          })
          .catch(error => {
            info.resource = JSON.parse(info.$root.$data.cookies.get('user')).resource;
            info.values = info.resource.skills;
          });
        axios
          .get(info.$root.serverURL + "/api/technicalSkills")
          .then(response => {
            info.options1 = response.data;
          })
          .catch(error => console.log(error));
      },
      updateProfile() {
        this.$router.push({path: `/admin/editUser`});
      },
    }
  }

</script>
<style>
</style>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>

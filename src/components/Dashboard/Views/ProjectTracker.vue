<template>
  <div class="content">
    <div>
      <b-alert :show=updatedProjectSuccessBanner dismissible variant="success">
        <h4 class="alert-heading">Project information has been updated</h4>
      </b-alert>
    </div>
    <card>
      <h4 slot="header" class="card-title">Edit Project</h4>
      <form>
        <div class="row">
          <div class="col-md-3">
            <fg-input type="text"
                      label="Project ID"
                      :disabled="true"
                      placeholder="Project ID"
                      v-model="project.id">
            </fg-input>
          </div>
          <div class="col-md-5">
            <fg-input type="text"
                      label="Name"
                      placeholder="Name"
                      v-model="project.name">
            </fg-input>
          </div>
          <div class="col-md-5">
            <fg-input type="text"
                      label="Est. Remaining Cost"
                      placeholder="Estimated remaining cost"
                      v-model="project.estimatedRemainingCost">
            </fg-input>
          </div>
          <div class="col-md-5">
            <fg-input type="text"
                      label="Percentage Complete"
                      placeholder="% Complete"
                      v-model="project.percentageComplete">
            </fg-input>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <fg-input type="text"
                      label="Budget"
                      placeholder="Budget"
                      v-model="project.budget">
            </fg-input>
          </div>
          <div class="col-md-6">
            <fg-input label="Project Owner"
                      placeholder="Project Owner"
                      v-model="project.projectOwner.name">
            </fg-input>
          </div>
        </div>
        <div class="text-center">
          <div class="btn-toolbar float-right">
            <button type="submit" class="btn btn-info btn-fill float-right" style="margin-right: 5px;" @click.prevent="addResources">
              Add Resource
            </button>
            <button type="submit" class="btn btn-info btn-fill float-right" style="margin-right: 5px;" @click.prevent="removeResources">
              Remove Resource
            </button>
            <button type="submit" class="btn btn-info btn-fill float-right" @click.prevent="updateProject">
              Update Project
            </button>
          </div>

        </div>
        <div class="clearfix"></div>
      </form>
    </card>
    <vue-good-table
      :columns="columns"
      :paginate="true"
      :rows="resources"
      :globalSearch="false"
      styleClass="table table-striped condensed">
    </vue-good-table>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import axios from 'axios'

  export default {
    components: {
      Card,
    },
    data() {
      return {
        updatedProjectSuccessBanner: false,
        columns: [
          {
            label: 'Name',
            field: 'name',
            filterable: true,
          },
          {
            label: 'Email',
            field: 'email',
            type: 'string',
            filterable: true,
          },
          {
            label: 'Location',
            field: 'location',
            filterable: true,
          },
          {
            label: 'Group',
            field: 'group',
            filterable: true,
          },
          {
            label: 'Manager',
            field: 'manager',
            filterable: true
          }
        ],
        project: {
          id: 0,
          name: '',
          email: '',
          location: '',
          group: null,
          manager: null,
          status: null,
          projectOwner: null,
          estimatedRemainingCost: null,
          percentageComplete: null,
        },
        resources: []
      }
    },
    created() {
      this.fetchData();
    },
    methods: {
      fetchData() {
        let info = this;
        axios
          .get(info.$root.serverURL + "/api/projects/" + this.$route.params.projectId)
          .then(response => {
            info.project = response.data;
            axios.get(info.$root.serverURL + "/api/projects/" + this.$route.params.projectId + "/resources")
              .then((response) => {
                info.resources = response.data;
                console.log(info.resources);
                info.resources.forEach((resource) => {
                });
              })
              .catch(() => console.log("error getting project resources"));
          })
          .catch(() => console.log("error fetching project"));
      },
      updateProject() {
        var info = this;
        axios.put(info.$root.serverURL + "/api/projects/" + info.project.id, info.project)
          .then(response => {
            info.updatedProjectSuccessBanner = true;
            info.project = response.data;
          })
          .catch(() => console.log("problem updating project"));
      },

      addResources(){
        var projectId = this.$route.params.projectId;
        this.$router.push({name: "addResources"}, {projectId: projectId});
      },

      removeResources(){
        var projectId = this.$route.params.projectId;
        this.$router.push({name: "removeResources"}, {projectId: projectId});
      }
    }
  }
</script>
<style>
</style>

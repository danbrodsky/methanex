<template>
  <div class="content" >
    <card>
      <h4 slot="header" class="card-title">Create a Project</h4>
      <b-alert :show=addedProjectBanner dismissible variant="success">
        <h4 class="alert-heading">A project was added</h4>
      </b-alert>
      <form>
        <div class="row">
          <div class="col-md-5">
            <fg-input type="text"
                      label="name"
                      placeholder="Name"
                      v-model="project.name">
            </fg-input>
          </div>
          <div class="col-md-8">
            <label class="typo__label">Manager:</label>
            <multiselect v-model="project.manager"
                         placeholder="Pick a manager"
                         label="name"
                         track-by="manager"
                         :options="resources"
                         :multiple="false">
            </multiselect>
            <pre class="language-json"></pre>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <label class="typo__label">Owner:</label>
            <multiselect v-model="project.projectOwner"
                         placeholder="Pick a manager"
                         label="name"
                         track-by="manager"
                         :options="resources"
                         :multiple="false">
            </multiselect>
            <pre class="language-json"></pre>
          </div>
          <div class="col-md-6">
            <fg-input type="date"
                      label="start"
                      placeholder="Start Date"
                      v-model="project.startDate">
            </fg-input>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4">
            <fg-input type="date"
                      label="End Date"
                      placeholder="end date"
                      v-model="project.endDate">
            </fg-input>
          </div>
          <div class="col-md-4">
            <fg-input type="number"
                      label="budget"
                      placeholder="Budget"
                      v-model="project.budget">
            </fg-input>
          </div>
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-info btn-fill float-right"  @click.prevent="saveProject">
            Save
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
        addedProjectBanner: false,
        resources: [],
        project: {
          name: '',
          manager: null,
          projectOwner: null,
          startDate: null,
          endDate: null,
          budget: null
        }
      }
    },
    created() {
      this.fetchData();
    },
    methods: {
      saveProject() {
        let info = this;
        axios
          .post(info.$root.serverURL + "/api/projects", info.project)
          .then(response => {
            info.addedProjectBanner = true;
          })
          .catch(error => console.log(error));
      },
      fetchData() {
        let info = this;
        axios
          .get(info.$root.serverURL + "/api/resources")
          .then(response => {
            info.resources = response.data;
          })
          .catch(error => console.log(error));
      },
    }
  }

</script>
<style>
</style>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>


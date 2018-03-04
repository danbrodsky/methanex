<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12" id="addProjectForm">
          <card>
          <!-- <img src="https://d33wubrfki0l68.cloudfront.net/85975663ee3d10baa062c8406db6c5f10627a601/0a978/images/chart-title.png" alt="Mountain View"> -->
            <label for="addProjectForm" class="col-form-label">ID: 54324 </label>
            <div class="row">
              <div class="form-group align-items-left">
                <label for="status" class="col-form-label">Project Name</label>
                    <input type="title" style="width:25%;" class="form-control form-control-lg" id="status" v-model="project.name">
                <label class="mt-2" for="status">Status</label>
                <select v-model="project.effort" class="form-control mr-sm-2" style="width:15%;" id="exampleFormControlSelect1">
                  <option value="approved">Approved</option>
                  <option value="pending">Pending</option>
                  <option value="pipline">Pipeline</option>
                  <option value="active">Active</option>
                  <option value="complete">Complete</option>
                </select>

                <label for="addProjectForm" class="col-form-label">Business Owner</label>
                    <input type="title" style="width:15%;" class="form-control form-control-sm" value="Bob">
                <label for="addProjectForm" class="col-form-label">Classification</label>
                    <input type="title" style="width:15%;" class="form-control form-control-sm" id="classificationInput" value="Infrastructure">
                <label for="addProjectForm" class="col-form-label">Resource Breakdown</label>
                    <input type="" style="width:15%;" class="form-control form-control-sm" id="resourceBreakdownInput" value="Planning 8mm">
              </div>
              <div class="form-group">
                <button type="button" v-if="isNewProject" v-on:click="addNewProject" style="display:block" class="btn">Add New Project</button>
                <button type="button" v-if="!isNewProject" style="display:block;" class="btn btn-success">Update Project</button>
                <button type="button" class="mt-2 btn btn-danger">Delete Project</button>
              </div>
            </div>
          </card>
        </div>
      </div>
      <div class="row">
        <filter-bar
          style="width: 100%;margin: 0.5%;box-shadow: 5px 5px 5px grey"
          v-bind:sortingOptions="sortingOptions"
          v-bind:filterOptions="filterOptions">
        </filter-bar>
      </div>
      <div class="row">
        <resource-card></resource-card>
        <resource-card></resource-card>
        <resource-card></resource-card>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
import ResourceCard from 'src/components/UIComponents/Cards/ResourceCard.vue'
import FilterBar from 'src/components/UIComponents/FilterBar.vue'

export default {
  components: {
    ResourceCard,
    FilterBar
  },
  created () {
    this.fetchData();
  },
  data () {
    return {
      isNewProject: false,
      portfolioId: -1, // this if for new/undefined projects only
      project: {
        id: -1,
        name: "",
        manager: "",
        effort: "",
        startDate: new Date(),
        endDate: new Date(),
        budget: 0,
        ragStatus: "",
        portfolios:	[],
        resources:	[]
      },
      sortingOptions: [
          { value: 'id', text: 'ID' },
          { value: 'name', text: 'Name' },
          { value: 'status', text: 'Status' },
          { value: 'location', text: 'Location' }
        ],
        filterOptions: [
          { value: {category: 'id', type: Number}, text: 'ID' },
          { value: {category: 'name', type: String}, text: 'Name' },
          { value: {category: 'status', type: String}, text: 'status' },
          { value: {category: 'location', type: String}, text: 'Location' }
        ]
    }
  },
  methods: {
    fetchData() {
      var info = this;
      if (this.$route.params.projectId === undefined) {
        this.isNewProject = true;
        this.portfolioId = this.$route.query.portfolioId;
        return;
      }
      axios.get(this.$root.serverURL + "/api/projects/"+ this.$route.params.projectId)
        .then(response => {
          console.log(response.data);
          info.projects = response.data;
        })
    },
    addNewProject() {
      console.log("posting to: " + this.$root.serverURL + `/api/portfolios/${this.portfolioId}/projects/`);
      axios.post(this.$root.serverURL + `/api/portfolios/${this.portfolioId}/projects/`, {
        "name": this.project.name,
        "budget": this.project.budget,
        "effort": this.project.effort
      })
      .then(function (res){
        console.log(res);
      });
    },
    updateProject() {
      axios.put(this.$root.serverURL + "/api/projects/" + this.project.id, {
        "name": this.project.name,
        "budget": this.project.budget,
        "effort": this.project.effort
      })
      .then(function (res){
        console.log(res);
      });
    }
  }
}

</script>
<style>
</style>

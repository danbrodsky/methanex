<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-6" id="addProjectForm">
          <!-- <img src="https://d33wubrfki0l68.cloudfront.net/85975663ee3d10baa062c8406db6c5f10627a601/0a978/images/chart-title.png" alt="Mountain View"> -->
            <label for="addProjectForm" class="col-form-label">ID: {{project.id}} </label>
              <div class="form-group align-items-left" style="min-width:380px">
                <div class="col-xs-6">
                  <label for="status" class="col-form-label">Project Name</label>
                  <textarea type="title" class="form-control form-control-lg" id="status"
                            v-model="project.name" v-bind:disabled="!project.isProjectManager && !editMode"></textarea>
                </div>
                <label class="mt-2" for="status">Status</label>
                <select class="form-control mr-sm-2" style="width:20%;" id="exampleFormControlSelect1"
                        v-bind:disabled="!project.isProjectManager && !editMode" v-model="project.status">
                  <option>Approved</option>
                  <option>Pending</option>
                  <option>Pipeline</option>
                  <option>Active</option>
                  <option>Complete</option>
                </select>
                <div class="col-xs-6">
                  <label for="addProjectForm" class="col-form-label">Business Owner</label>
                  <input type="title" style="width:25%;" class="form-control form-control-sm-4"
                         v-bind:value="project.businessOwner" v-bind:disabled="!project.isProjectManager && !editMode">
                  <label for="addProjectForm" class="col-form-label">Classification</label>
                  <input type="title" style="width:25%;" class="form-control form-control-sm-4"
                         id="classificationInput" v-bind:value="project.classification" v-bind:disabled="!project.isProjectManager && !editMode">
                </div>
              </div>
        </div>
        <div class="col-2">
          <div class="form-group" style="width: 100px">
            <button type="button" v-if="isNewProject" v-on:click="addNewProject" style="display:block" class="btn btn-block">Add New Project</button>
            <button type="button" v-if="!isNewProject && isProjectManager && !editMode" v-on:click="enableEdit" style="display:block" class="btn btn-success btn-block">Edit</button>
            <button type="button" v-else-if="!isNewProject && isProjectManager" style="display:block;" class="btn btn-success btn-block" v-on:click="updateProject">Update</button>
            <button type="button" class="mt-2 btn btn-danger btn-block" v-if="!isNewProject && isProjectManager" >Delete</button>
          </div>
        </div>
        <div class ="col-4" style="height:50%">
          <resource-breakdown v-bind:resourceData="resourceData"></resource-breakdown>
        </div>
      </div>
      <div class="row">
      </div>
      <div class="row">
        <filter-bar
          style="width: 100%;margin: 0.5%;box-shadow: 5px 5px 5px grey"
          v-model="filterFcn"
          v-bind:sortingOptions="sortingOptions"
          v-bind:filterOptions="filterOptions"
          v-on:newSearch="performSearch">
        </filter-bar>
      </div>
      <div class="row">
        <resource-card
          v-for="resource of resourcesDisplayed"
          v-bind:key="resource.resourceId"
          v-bind:resourceName="resource.resourceName"
          v-bind:status="resource.status"
          v-bind:location="resource.location"></resource-card>

      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
import ResourceCard from 'src/components/UIComponents/Cards/ResourceCard.vue'
import FilterBar from 'src/components/UIComponents/FilterBar.vue'
import GanttChart from 'src/components/UIComponents/PortfolioComponents/GanttChart.vue'
import ResourceBreakdown from 'src/components/UIComponents/PortfolioComponents/ResourceBreakdown.vue'
import PieChart from 'src/components/UIComponents/PieChart.js'

export default {
  components: {
    ResourceCard,
    ResourceBreakdown,
    GanttChart,
    FilterBar,
    PieChart
  },


   mounted() {
    this.fetchData();
  },

  data () {
    return {
      isNewProject: false,
      isProjectManager: false,
      editMode: false,

      project: {
        "id": 0,
        "name": "",
        "email": "",
        "location": "",
        "manager": null,
        "businessOwner": "",
        "status": null
      },
      projectResouces: [],
      resourceData: [],
      resourcesDisplayed: [],

      /*statusList: {
      <option>Approved</option>
      <option>Pending</option>
      <option>Pipeline</option>
      <option>Active</option>
      <option>Complete</option>

        {value: 'Approved'},
        {value: }
      },*/

      sortingOptions: [
          { value: 'resourceName', text: 'Name' },
          { value: 'status', text: 'Status' },
          { value: 'location', text: 'Location' }
        ],
        filterOptions: [
          { value: {category: 'resourceName', type: String}, text: 'Name' },
          { value: {category: 'status', type: String}, text: 'Status' },
          { value: {category: 'location', type: String}, text: 'Location' }
        ],
        filterFcn: function (list) { return list;}
    }
  },
  methods: {
    fetchData() {
      console.log("fetch data");
      var info = this;
      if (this.$route.params.projectId === undefined) {
        this.isNewProject = true;
        this.portfolioId = this.$route.query.portfolioId;
        return;
      }

      axios.get(this.$root.serverURL + "/api/projects/"+ this.$route.params.projectId)
        .then(response => {
          console.log(response.data);
          info.project = response.data;
        })
      //currently portfolio controller has not been setup so i dont have access to project id
      // fetchProjectResources();
      // this.resourcesDisplayed = projectResources();
      /*axios.get(this.$root.serverURL + "/api/projects/"+ this.$route.params.projectId+"/resources")
        .then(response => {
          console.log(response.data);
          info.resourcesDisplayed = response.data;
        })*/

      this.resourceData = [
        {
          resourceId: 1,
          resourceName: "Lecia",
          status: "Available",
          location: "Nalhagrande"
        },

        {
          resourceId: 2,
          resourceName: "Rackam",
          status: "MIA",
          location: "Somewhere"
        },

        {
          resourceId: 3,
          resourceName: "Rosetta",
          status: "Available",
          location: "Nalhagrande"
        }

      ],

      this.resourcesDisplayed = this.resourceData.splice();
      this.resourcesDisplayed.sort();
      //
      /*this.project.id = this.$route.params.projectId;
      this.status = "Pipeline";
      this.project.name = "Granblue Fantasy";
      this.project.location = "Nalhagrande";
      this.project.manager = "Djeeta";
      this.project.classfication = "Distributed Systems";
      this.project.businessOwner = "Djeeta";*/

      this.isProjectManager = true;

      console.log(this.project.id);
    },

    fetchProjectResources() {
      //backend needs to support query parameters so that we can pass in the project id
      //and have the api return a list of resources that are in a project with this project id
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

      this.editMode = false;
    },

    enableEdit(){
      this.editMode = true;
    },

    performSearch() {
      console.log("hello");
      this.resourcesDisplayed = this.filterFcn(this.resourceData);
    }
  }
}

</script>
<style>
</style>

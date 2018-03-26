<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
        <project-status-bar width="100%"></project-status-bar>
        </div>
      </div>
      <div class="row">
        <div class="col-7" id="addProjectForm">
          <label for="addProjectForm" class="col-form-label">ID: {{project.id}} </label>
          <div class="form-group align-items-left" style="min-width:380px">
            <div class="col-xs-6">
              <label for="status" class="col-form-label">Project Name</label>
              <input type="text" class="form-control form-control-lg" id="status"
                        v-model="project.name" v-bind:disabled="!project.isProjectManager && !editMode" />
            </div>
            <label class="mt-2" for="status">Status</label>
            <select class="form-control mr-sm-2" id="exampleFormControlSelect1"
                    v-bind:disabled="!project.isProjectManager && !editMode" v-model="project.status">
              <option>Approved</option>
              <option>Pending</option>
              <option>Pipeline</option>
              <option>Active</option>
              <option>Complete</option>
            </select>

            <label for="addProjectForm" class="col-form-label">Business Owner</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.businessOwner" v-bind:disabled="!project.isProjectManager && !editMode">
            <label for="addProjectForm" class="col-form-label">Classification</label>
            <input type="text" class="form-control form-control-sm-4"
                   id="classificationInput" v-model="project.classification" v-bind:disabled="!project.isProjectManager && !editMode">

            <label for="addProjectForm" class="col-form-label">Project Manager</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.manager" v-bind:disabled="!project.isProjectManager && !editMode">
            <label for="addProjectForm" class="col-form-label">RAG Status</label>
            <select type="text" class="form-control form-control-sm-4"
                   v-model="project.rag_status" v-bind:disabled="!project.isProjectManager && !editMode">
              <option value="green" selected="selected">Green</option>
              <option value="yellow">Yellow</option>
              <option value="red">Red</option>
            </select>
            <label for="addProjectForm" class="col-form-label">Budget</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.budget" v-bind:disabled="!project.isProjectManager && !editMode">
            <label for="addProjectForm" class="col-form-label">Budget Used</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.budget_used" v-bind:disabled="!project.isProjectManager && !editMode">
            <label for="addProjectForm" class="col-form-label">Estimated Cash Needed To Complete</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.est_cash_needed_to_complete" v-bind:disabled="!project.isProjectManager && !editMode">
            <label for="addProjectForm" class="col-form-label">Start Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.start_date" v-bind:disabled="!project.isProjectManager && !editMode">
            <label for="addProjectForm" class="col-form-label">End Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.end_date" v-bind:disabled="!project.isProjectManager && !editMode">
            <label for="addProjectForm" class="col-form-label">Expected Preapproval Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.exp_preapproval_date" v-bind:disabled="!project.isProjectManager && !editMode">

            <label for="addProjectForm" class="col-form-label">Expected Seeking Funding Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.exp_seeking_funding_date" v-bind:disabled="!project.isProjectManager && !editMode">

            <label for="addProjectForm" class="col-form-label">Expected Pipeline Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.exp_pipeline_date" v-bind:disabled="!project.isProjectManager && !editMode">

            <label for="addProjectForm" class="col-form-label">Expected To Confirm Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.exp_to_confirm_date" v-bind:disabled="!project.isProjectManager && !editMode">

            <label for="addProjectForm" class="col-form-label">Expected Closing Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.exp_closing_date" v-bind:disabled="!project.isProjectManager && !editMode">

            <label for="addProjectForm" class="col-form-label">Expected Closed Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.exp_closed_date" v-bind:disabled="!project.isProjectManager && !editMode">



            <!--<label for="addProjectForm" class="col-form-label">Actual Preapproval Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.actual_preapproval_date" v-bind:disabled="!project.isProjectManager && !editMode">

            <label for="addProjectForm" class="col-form-label">Actual Seeking Funding Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.actual_seeking_funding_date" v-bind:disabled="!project.isProjectManager && !editMode">

            <label for="addProjectForm" class="col-form-label">Actual Pipeline Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.actual_pipeline_date" v-bind:disabled="!project.isProjectManager && !editMode">

            <label for="addProjectForm" class="col-form-label">Actual To Confirm Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.actual_to_confirm_date" v-bind:disabled="!project.isProjectManager && !editMode">

            <label for="addProjectForm" class="col-form-label">Actual Closing Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.actual_closing_date" v-bind:disabled="!project.isProjectManager && !editMode">

            <label for="addProjectForm" class="col-form-label">Actual Closed Date</label>
            <input type="text" class="form-control form-control-sm-4"
                   v-model="project.actual_closed_date" v-bind:disabled="!project.isProjectManager && !editMode">-->

          </div>
        </div>
        <!--<div class="col-2">-->
          <!--<div class="form-group" style="width: 100px">-->
            <!--<button type="button" v-if="isNewProject" v-on:click="addNewProject" style="display:block" class="btn btn-block">Add New Project</button>-->
            <!--<button type="button" v-if="!isNewProject && isProjectManager && !editMode" v-on:click="enableEdit" style="display:block" class="btn btn-success btn-block">Edit</button>-->
            <!--<button type="button" v-else-if="!isNewProject && isProjectManager" style="display:block;" class="btn btn-success btn-block" v-on:click="updateProject">Update</button>-->
            <!--<button type="button" class="mt-2 btn btn-danger btn-block" v-if="!isNewProject && isProjectManager" >Delete</button>-->
          <!--</div>-->
        <!--</div>-->
        <div class ="col-5" style="height:100%">
          <resource-breakdown v-bind:resourceData="resourceData"></resource-breakdown>
        </div>
      </div>
      <div class="row">
          <button type="button" v-if="isNewProject" v-on:click="addNewProject" style="display:block" class="btn btn-block">Add New Project</button>
          <button type="button" v-if="!isNewProject && isProjectManager && !editMode" v-on:click="enableEdit" style="display:block" class="btn btn-success btn-block">Edit</button>
          <button type="button" v-else-if="!isNewProject && isProjectManager" style="display:block;" class="btn btn-success btn-block" v-on:click="updateProject">Update</button>
          <span style="align:center">{{ updatingStatus }}</span>
      </div>
      <div class="row">
        <button type="button" class="btn btn-primary btn-sm btn-fill float-right" v-on:click="addResources">
          Add Resources
        </button>
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
      <!-- <div class="col-2">
      <div class="form-group" style="width: 100px">
      <button type="button" v-if="isNewProject" v-on:click="addNewProject" style="display:block" class="btn btn-block">Add New Project</button>
      <button type="button" v-if="!isNewProject && isProjectManager && !editMode" v-on:click="enableEdit" style="display:block" class="btn btn-success btn-block">Edit</button>
      <button type="button" v-else-if="!isNewProject && isProjectManager" style="display:block;" class="btn btn-success btn-block" v-on:click="updateProject">Update</button>
      <button type="button" class="mt-2 btn btn-danger btn-block" v-if="!isNewProject && isProjectManager" >Delete</button>
      </div>
      </div> -->
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
import ProjectStatusBar from 'src/components/UIComponents/ProjectStatusBar.vue'

export default {
  components: {
    ResourceCard,
    ResourceBreakdown,
    GanttChart,
    FilterBar,
    PieChart,
    ProjectStatusBar
  },


   created() {
    this.setIsNewAndPermissions();
    this.fetchData();
  },

  data () {
    return {
      isNewProject: false,
      isProjectManager: false,
      editMode: false,
      updatingStatus: "",
      project: {
        "id": -1,
        "portfolio_id": -1,
        "name": "",
        "manager": null,    // stored as id of manager?
        "project_owner": -1,
        "rag_status": -1,
        "budget": -1,
        "budget_used": -1,
        "est_cash_needed_to_complete": -1,
        "start_date": new Date(),
        "end_date": new Date(),

        "exp_preapproval_date": new Date(),
        "exp_seeking_funding_date": new Date(),
        "exp_pipeline_date": new Date(),
        "exp_to_confirm_date": new Date(),
        "exp_closing_date": new Date(),
        "exp_closed_date": new Date(),

        "actual_preapproval_date": new Date(),
        "actual_seeking_funding_date": new Date(),
        "actual_pipeline_date": new Date(),
        "actual_to_confirm_date": new Date(),
        "actual_closing_date": new Date(),
        "actual_closed_date": new Date(),

        "current_status_percent": -1,
        "current_status": ""
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
    setIsNewAndPermissions() {
      if (this.$route.params.projectId === undefined) {
        this.isNewProject = true;
        this.portfolioId = this.$route.query.portfolioId;
        this.isProjectManager = true;
        this.editMode = true;
      }
    },
    fetchData() {
      console.log("fetch data");
      var info = this;
      if (this.$route.params.projectId === undefined) return;

      axios.get(this.$root.serverURL + "/api/projects/"+ this.$route.params.projectId)
        .then(response => {
          console.log(response.data);
          info.project = response.data;
        })
      //currently portfolio controller has not been setup so i dont have access to project id
      // fetchProjectResources();
      // this.resourcesDisplayed = projectResources();
      axios.get(this.$root.serverURL + "/api/projects/"+ this.$route.params.projectId+"/resources")
        .then(response => {
          console.log("Received response from resource api")
          console.log(response.data);
          info.resourcesDisplayed = response.data;
        })

      // for wip purposes display stub data if there is no resource data available for the project
      // if(this.resourcesDisplayed.length == 0){
      //   this.resourceData = [];
      //   this.resourceData.push({
      //     resourceId: 1,
      //     resourceName: "Lecia",
      //     group: "Wind",
      //     peerGroup: "Sword",
      //     status: "Available",
      //     location: "Nalhagrande"
      //   });
      //
      //   this.resourceData.push({
      //     resourceId: 2,
      //     resourceName: "Rosetta",
      //     group: "Wind",
      //     peerGroup: "Dagger",
      //     status: "Available",
      //     location: "Nalhagrande"
      //   });
      //
      //   this.resourceData.push({
      //     resourceId: 3,
      //     resourceName: "Beatrix",
      //     peerGroup: "Sword",
      //     group: "Fire",
      //     status: "Sick",
      //     location: "Somewhere"
      //   });
      //
      //   this.resourceData.push({
      //     resourceId: 4,
      //     resourceName: "Diantha",
      //     group: "Water",
      //     peerGroup: "Harp",
      //     status: "On Leave",
      //     location: "Phantagrande"
      //   });
      //
      //   this.resourceData.push({
      //     resourceId: 5,
      //     resourceName: "Ilsa",
      //     group: "Earth",
      //     peerGroup: "Gun",
      //     status: "MIA",
      //     location: "Nalhagrande"
      //   });
      //
      //   this.resourceData.push({
      //     resourceId: 6,
      //     resourceName: "Sorn",
      //     group: "Light",
      //     peerGroup: "Bow",
      //     status: "Available",
      //     location: "With Silva"
      //   });
      //
      //   this.resourceData.push({
      //     resourceId: 7,
      //     resourceName: "Silva",
      //     group: "Water",
      //     peerGroup: "Gun",
      //     status: "Available",
      //     location: "With Sorn"
      //   });
      //
      //   this.resourcesDisplayed = this.resourceData.slice();
      //   this.resourcesDisplayed.sort();
      //
      // }

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

      this.updatingStatus = "Saving...";

      var info = this;

      console.log("Project looks like:");
      console.log(info.project);

      axios.post(this.$root.serverURL + `/api/portfolios/${this.portfolioId}/projects/`, {
        "name": info.project.name,
        "budget": info.project.budget,
        "rag_status": info.project.rag_status
        // "status": info.project.status,
        // "budget_used": info.project.budget_used,
        // "budget_est_needed": info.project.est_cash_needed_to_complete,
        // "start_date": info.project.start_date,
        // "end_date": info.project.end_date
      })
      .then(function (res){
        console.log(res);
        console.log(info.project.name);
        info.updatingStatus = "Saved!";
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

    addResources() {
      //this.$router.push({path: '/admin/project/' + this.$route.params.projectId + '/addResources'});
      this.$router.push({name: 'addResources', params: {projectId: this.$route.params.projectId, existingResources: this.resourceData} } );
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

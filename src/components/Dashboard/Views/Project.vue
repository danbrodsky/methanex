<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div id="project_id">Project id: {{ this.$router.params.projectId}}</div>
        <gantt-chart style="width: 58%;margin: 0.5%;box-shadow: 5px 5px 5px grey;"></gantt-chart>
        <resource-breakdown style="width: 40%;margin: 0.5%;box-shadow: 5px 5px 5px grey;"></resource-breakdown>
      </div>
      <div class="row">
        <filter-bar style="width: 100%;margin: 0.5%;box-shadow: 5px 5px 5px grey"
                    v-model="filterFcn"
                    v-bind:sortingOptions="sortingOptions"
                    v-bind:filterOptions="filterOptions"
                    v-on:newSearch="performSearch">
        </filter-bar>
      </div>
      <div class="row" id="projectDetails">

      </div>
      <div class="row">
        <resource-card style="margin: 0.5%;box-shadow: 5px 5px 5px grey;"
                      v-for="resource of displayResources"
                      v-bind:key="resource.name"
                      v-bind:resourceStatus="project.status"
                      v-bind:resourceLocation="project.location">
          <button id="addResourceButtonPlaceholder">Add Resource</button>
          <!-- v-bind="project" -->
        </resource-card>
      </div>

    </div>
  </div>
</template>

<script>
  import ChartCard from 'src/components/UIComponents/Cards/ChartCard.vue'
  import StatsCard from 'src/components/UIComponents/Cards/StatsCard.vue'
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import LTable from 'src/components/UIComponents/Table.vue'
  import Checkbox from 'src/components/UIComponents/Inputs/Checkbox.vue'
  import axios from 'axios'
  import GanttChart from 'src/components/UIComponents/PortfolioComponents/GanttChart.vue'
  import ResourceBreakdown from 'src/components/UIComponents/PortfolioComponents/ResourceBreakdown.vue'
  import FilterBar from 'src/components/UIComponents/FilterBar.vue'

  export default {
    name: "project",

    components: {
      Checkbox,
      Card,
      LTable,
      ChartCard,
      StatsCard,
      GanttChart,
      ResourceBreakdown,
      ResourceCard,
    },

    methods: {
      Created() {
        this.fetchData();
      },

      data() {
        return {
          id: this.$router.params.projectId,
          projectName: "",
          projectStatus: "",
          projectManager: "",
          projectOwner: "",
          complete: "",
          startDate: "",
          endDate: "",
          RAGstatus: "",
          financials: [],
          resources: [],
          displayResources: [],
          filterOptions: [
            { value: {category: 'resourceName', type: String}, text: 'Name' },
            { value: {category: 'status', type: String}, text: 'StatusID' },
            { value: {category: 'location', type: String}, text: 'Completion Progress' },
          ],
          filterFcn: function (list) { console.log("qwer"); return list;}
        }
      },

      fetchData() {
        //fetch actual project data from the server instead
        //for display stub data
        // var info = this;
        // axios.get(this.$root.serverURL + "/api/project/" + this.$router.param.projectId)
        //   .then(response => {
        //     console.log(response.data);
        //     info.projects = response.data;
        //     info.disp
        //     response.data;
        //   })
        this.projectName = "Granblue Fantasy";
        this.projectStatus = "pipelined";
        this.projectManager = "Djeeta";
        this.projectOwner = "Djeeta";
        this.complete = "99.05";
        this.startDate = "01/01/2018";
        this.endDate =  "01/12/2018";
        this.RAGstatus = "RED";
        this.financials =  [
          {budget: 1000000},
          {spendToDate: 100000},
          {estimateToComplete: 0}
        ],
        this.resources = [
          {
            resourceName: 'Lecia',
            status: "Available",
            location: "Nalhegrande"
          },

          {
            resourceName: 'Rosetta',
            status: "Forever JK",
            location: "Nalhegrande"
          }

        ];
        this.displayProjects = this.resources.slice();
      },

      addResourceToProject() {

      },

      editProject() {

      },

      performSearch() {
        this.displayResources = this.filterFcn(this.resources);
      }
    }
  }

</script>

<style scoped>
  div.filters {
    text-align: center;
    width: 100%;
    padding: 1em;
    border: 1px solid grey;
    border-radius: 2px;
    margin-bottom: 1em;
    color: white;
    background-color: grey;
    margin: 0.5%;
    box-shadow: 5px 5px 5px grey;
  }
</style>

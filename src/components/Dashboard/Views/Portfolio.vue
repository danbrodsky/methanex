<template>
  <div class="content" >
    <div class="container-fluid">
      <b-alert :show=noProjectsBanner dismissible variant="warning">
        <h4 class="alert-heading">This portfolio contains no projects at this time</h4>
      </b-alert>
      <div v-if="hasAccess()" class="row">
        <button v-b-modal.addPortfolioModal class="btn btn-info btn-fill float-right" v-on:click="createProject" >
          <b style="font-size: large">+</b>
        </button>
      </div>
      <div class="row" style="margin-left: 5%;">
        <pulse-loader color="#00A6CD" :loading="isLoadingProjects"></pulse-loader>
        <project-card style="margin: 0.5%;box-shadow: 5px 5px 5px grey;"
                      v-for="project of displayProjects"
                      v-bind:role="role"
                      v-bind:key="project.id"
                      v-bind:project="project"
                      v-bind:portfolioId="$route.params.portfolioId"
                      v-on:portfolio-remove="remove">
        </project-card>
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
  import ProjectCard from 'src/components/UIComponents/Cards/ProjectCard.vue'
  import AddProjectCard from 'src/components/UIComponents/Cards/AddProjectCard.vue'
  import axios from 'axios'
  import GanttChart from 'src/components/UIComponents/PortfolioComponents/GanttChart.vue'
  import ResourceBreakdown from 'src/components/UIComponents/PortfolioComponents/ResourceBreakdown.vue'
  import FilterBar from 'src/components/UIComponents/FilterBar.vue'
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue'


  export default {
    components: {
      Checkbox,
      Card,
      LTable,
      ChartCard,
      StatsCard,
      GanttChart,
      ResourceBreakdown,
      FilterBar,
      ProjectCard,
      AddProjectCard,
      PulseLoader
    },
    created() {
      let that = this;
      axios.get(this.$root.serverURL + "/user/" + JSON.parse(that.$root.$data.cookies.get('user')).id + "/roles")
        .then(response => {
          that.role = response.data[0].name;
        });
      this.fetchData();
    },
    data() {
      return {
        noProjectsBanner: false,
        isLoadingProjects: true,
        checkedIds: [],
        role: '',
        portfolioId: -1,
        name: "",
        projects: [],
        displayProjects: [],
        sortingOptions: [
          {value: 'id', text: 'ID'},
          {value: 'name', text: 'Name'},
          {value: 'projectStatus', text: 'StatusID'},
          {value: 'projectProgress', text: 'Completion Progress'},
          {value: 'projectManager', text: 'Manager'},
          {value: 'numPeopleOnTeam', text: 'Team Size'},
          {value: 'startDate', text: 'Start Date'},
          {value: 'endDate', text: 'End Date'},
          {value: 'budget', text: 'Budget'},
          {value: 'budgetUsed', text: 'Budget Used'}
        ],
        filterOptions: [
          {value: {category: 'id', type: Number}, text: 'ID'},
          {value: {category: 'name', type: String}, text: 'Name'},
          {value: {category: 'projectStatus', type: String}, text: 'StatusID'},
          {value: {category: 'projectProgress', type: String}, text: 'Completion Progress'},
          {value: {category: 'projectManager', type: String}, text: 'Manager'},
          {value: {category: 'numPeopleOnTeam', type: Number}, text: 'Team Size'},
          {value: {category: 'startDate', type: Date}, text: 'Start Date'},
          {value: {category: 'endDate', type: Date}, text: 'End Date'},
          {value: {category: 'budget', type: Number}, text: 'Budget'},
          {value: {category: 'budgetUsed', type: Number}, text: 'Budget Used'}
        ],
        filterFcn: function (list) {
          return list;
        }
      }
    },
    methods: {
      goToProject(id) {
        this.$router.push({path: `/admin/project/${id}`});
      },
      fetchData() {
        var info = this;
        if (this.$route.params.portfolioId === undefined) {
          this.isNewPortfolio = true;
          axios.get(this.$root.serverURL + "/api/projects")
            .then(response => {
              info.projects = response.data.slice();
              info.displayProjects = response.data.slice();
              info.isLoadingProjects = false;
              if (info.projects.length == 0) {
                info.noProjectsBanner = true;
              }
            })
            .catch(error => {
              console.log(error);
              info.isLoadingProjects = false;
            })
        }
        else {
          this.portfolioId = this.$route.params.portfolioId;
          axios.get(this.$root.serverURL + "/api/portfolios/" + this.portfolioId + "/projects")
            .then(response => {
              info.projects = response.data.slice();
              info.displayProjects = response.data.slice();
              info.isLoadingProjects = false;
              if (info.projects.length == 0) {
                info.noProjectsBanner = true;
              }
            })
            .catch(error => {
              console.log(error);
              info.isLoadingProjects = false;
            })
        }
      },
      hasAccess() {
        return this.role == "ROLE_ADMIN";
      },
      createProject() {
        this.$router.push({path: `/admin/addProjects/${this.portfolioId}`});
      },
      performSearch() {
        this.displayProjects = this.filterFcn(this.projects);
      },
      remove (id) {
        let info = this;
        info.projects.splice(id,1);
        info.displayProjects = info.projects.slice();
      },
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

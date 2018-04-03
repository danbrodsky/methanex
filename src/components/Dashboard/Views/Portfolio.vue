<template>
  <div class="content" style="background-color: #FF69B4">
    <b-navbar toggleable="md" type="light">

      <b-collapse is-nav id="nav_collapse">
        <b-navbar-nav>
          <button v-b-modal.addPortfolioModal class="btn btn-info btn-fill float-right" v-on:click="createProject" style="background-color: #FF69B4">
            <b style="font-size: large">+</b>
          </button>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">

          <b-nav-form>
            <b-form-input size="sm"  class="mr-sm-2" type="text" placeholder="Search"/>
            <button class="btn btn-dark btn-fill" style="background-color: #FF69B4" size="sm" type="submit">Search</button>
          </b-nav-form>

        </b-navbar-nav>

      </b-collapse>
    </b-navbar>
    <div class="container-fluid">
      <div class="row">
      </div>
      <div class="row" style="margin-left: 5%;">
        <project-card style="margin: 0.5%;box-shadow: 5px 5px 5px grey;"
                      v-for="project of displayProjects"
                      v-bind:key="project.id"
                      v-bind:project="project">
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
            })
        }
        else {
          this.portfolioId = this.$route.params.portfolioId;
          axios.get(this.$root.serverURL + "/api/portfolios/" + this.portfolioId + "/projects")
            .then(response => {
              info.projects = response.data.slice();
              info.displayProjects = response.data.slice();
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

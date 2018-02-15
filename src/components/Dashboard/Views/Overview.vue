<template>
  <div class="content">
    <div class="container-fluid">
      <!-- <div class="row">
        <h2>{{ portfolioName }}</h2>
      </div> -->
      <div class="row">
        <gantt-chart style="width: 60%"></gantt-chart>
        <resource-breakdown style="width: 40%"></resource-breakdown>
      </div>
      <div class="row">
        <div class="filters">
          <span>Sort By: </span>
          <select v-model="sortBy" @change="sortProjects()">
            <option v-for="option in sortingOptions" v-bind:key="option.value" v-bind:value="option.value">{{ option.text }}</option>
          </select>
        </div>
      </div>
      <div class="row">
        
          <project-card 
            v-for="project of projects" 
            v-bind:key="project.projectId"
            v-bind="project">
          </project-card>
          <add-project-card></add-project-card>
          
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
  import GanttChart from 'src/components/UIComponents/PortfolioComponents/GanttChart.vue'
  import ResourceBreakdown from 'src/components/UIComponents/PortfolioComponents/ResourceBreakdown.vue'

  export default {
    components: {
      Checkbox,
      Card,
      LTable,
      ChartCard,
      StatsCard,
      ProjectCard,
      AddProjectCard,
      GanttChart,
      ResourceBreakdown
    },
    data () {
      return {
        portfolioName: 'Portfolio 1',
        projects: [
          {
            projectId: 1,
            projectName: 'Project 1',
            projectStatus: 'Pipeline',
            projectProgress: 25,
            projectManager: 'Justin',
            numPeopleOnTeam: 50,
            startDate: '2/12/2018',
            endDate: '2/12/2019',
            budget: 5000,
            budgetUsed: 3000
          },
          {
            projectId: 2,
            projectName: 'Project 2',
            projectStatus: 'Pre Approval',
            projectProgress: 70,
            projectManager: 'Mark',
            numPeopleOnTeam: 32,
            startDate: '2/12/2018',
            endDate: '2/12/2019',
            budget: 9001,
            budgetUsed: 2000
          },
          {
            projectId: 3,
            projectName: 'Top Secret Project',
            projectStatus: 'Underway',
            projectProgress: 90,
            projectManager: 'Ali',
            numPeopleOnTeam: 7,
            startDate: '1/4/2018',
            endDate: '4/5/2018',
            budget: 50000,
            budgetUsed: 23500
          },
          {
            projectId: 4,
            projectName: 'BlockChain Project (no objective)',
            projectStatus: 'Seeking Funding',
            projectProgress: 36,
            projectManager: 'Daniel',
            numPeopleOnTeam: 1,
            startDate: '11/30/2017',
            endDate: '8/22/2024',
            budget: 0,
            budgetUsed: 0
          },
          {
            projectId: 5,
            projectName: 'Make Canada Great Again',
            projectStatus: 'Pre Approval',
            projectProgress: 55,
            projectManager: 'Kirsten',
            numPeopleOnTeam: 10,
            startDate: '12/25/2018',
            endDate: '2/14/2020',
            budget: 500,
            budgetUsed: 100
          }
        ],
        sortBy: 'ID',
        sortingOptions: [
          { value: 'projectId', text: 'ID' },
          { value: 'projectName', text: 'Name' },
          { value: 'projectStatus', text: 'Status' },
          { value: 'projectProgress', text: 'Completion Progress' },
          { value: 'projectManager', text: 'Manager' },
          { value: 'numPeopleOnTeam', text: 'Team Size' },
          { value: 'startDate', text: 'Start Date' },
          { value: 'endDate', text: 'End Date' },
          { value: 'budget', text: 'Initial Budget' },
          { value: 'budgetUsed', text: 'Budget Spent' }
        ]
      }
    },
    methods: {
      sortProjects () {
        this.projects = this.projects.sort((a,b) => { return a[this.sortBy] > b[this.sortBy] ? 1 : -1; });
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
  }
</style>

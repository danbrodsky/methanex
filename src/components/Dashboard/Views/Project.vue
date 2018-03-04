<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
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

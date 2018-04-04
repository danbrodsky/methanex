<template>
  <div class="content">
    <b-card>
      <label for="monthlyResourceChart">Amount of Resources Occupied By Month</label>
      <line-chart id="monthlyResourceChart" :chart-data="monthlyResourceDataCollection"></line-chart>
    </b-card>
    <b-card>
      <label for="resourceSkillCountChart">Amount of Resources By Skill By Project</label>
      <label class="typo__label">Project:</label>
      <multiselect v-model="projectsSkill"
                   placeholder="Pick a project for which to display information"
                   label="name"
                   track-by="skill"
                   :options="projects"
                   :multiple="false"></multiselect>
      <pie-chart id="resourceSkillCountChart" :chart-data="skillResourceCountDataCollection"></pie-chart>
    </b-card>
    <b-card>
      <label for="resourceGroupCountChart">Amount of Resources By Group By Project</label>
      <bar-chart id="resourceGroupCountChart" :chart-data="groupResourceCountDataCollection"></bar-chart>
    </b-card>
  </div>
</template>

<script>
  import LineChart from 'src/components/UIComponents/LineChart.js'
  import PieChart from 'src/components/UIComponents/PieChart.js'
  import BarChart from 'src/components/UIComponents/BarChart.js'
  import axios from 'axios'
  import Multiselect from 'vue-multiselect'

  export default {
    components: {
      LineChart,
      PieChart,
      BarChart,
      Multiselect
    },
    data() {
      return {
        monthlyResourceDataCollection: null,
        skillResourceCountDataCollection: null,
        groupResourceCountDataCollection: null,
        projectsSkill: null,
        projects: []
      }
    },
    mounted() {
      this.fillData()
    },
    methods: {
      fillData() {
        let info = this;

        axios
          .get(this.$root.serverURL + "/api/resourceGroupNumberData?projectId=123")
          .then(response => {
            let labels = [];
            let data = [];
            response.data.forEach(dataEntry => {
              labels.push(dataEntry.group);
              data.push(dataEntry.number);
            });
            info.groupResourceCountDataCollection = {
              labels: labels,
              datasets: [
                {
                  label: '# Resources / Group',
                  backgroundColor: '#68ff65',
                  data: data
                }]
            }
          })
          .catch(error => alert("problem loading pie chart" + error));

        axios
          .get(this.$root.serverURL + "/api/resourceSkillNumberData?projectId=123")
          .then(response => {
            let labels = [];
            let data = [];
            response.data.forEach(dataEntry => {
              labels.push(dataEntry.skill);
              data.push(dataEntry.number);
            });
            info.skillResourceCountDataCollection = {
              labels: labels,
              datasets: [
                {
                  label: '# Resources / Skill',
                  backgroundColor: '#5ebdff',
                  data: data
                }]
            }
          })
          .catch(error => console.log(error));

        axios
          .get(this.$root.serverURL + "/api/resourceProjectDateRange?start=2004-01-01&end=2004-12-12")
          .then(response => {
            let labels = [];
            let data = [];
            response.data.forEach(dataEntry => {
              labels.push(dataEntry.date);
              data.push(dataEntry.number);
            });
            info.monthlyResourceDataCollection = {
              labels: labels,
              datasets: [
                {
                  label: '# Resources / Month',
                  backgroundColor: '#f87979',
                  data: data
                }]
            }
          })
          .catch(error => alert("graph didn't load: " + error));
      }
    }
  }
</script>

<style>
  .small {
    max-width: 600px;
    margin: 150px auto;
  }
</style>

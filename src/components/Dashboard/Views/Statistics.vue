<template>
  <div class="content">
    <b-card>
      <label for="monthlyResourceChart">Amount of Resources Occupied By Month</label>

      <div class="row">
        <div class="col-md-6">
          <fg-input type="date"
                    label="start"
                    placeholder="Start"
                    v-model="startDateQuery">
          </fg-input>
        </div>
        <div class="col-md-4">
          <fg-input type="date"
                    label="End"
                    placeholder="end date"
                    v-model="endDateQuery">
          </fg-input>
        </div>
        <button type="submit" class="btn btn-info btn-fill float-right" style="position:relative;height:50px;top:20px;"  @click.prevent="populateDateRange">
          Calculate
        </button>
      </div>

      <pulse-loader :loading="isLoadingDate"></pulse-loader>
      <line-chart style="margin:auto;width:60%;" id="monthlyResourceChart" :chart-data="monthlyResourceDataCollection"></line-chart>
    </b-card>
    <b-card>
      <line-chart id="monthlyResourceChart" :chart-data="monthlyResourceDataCollection"></line-chart>
      <label for="resourceSkillCountChart">Amount of Resources By Skill By Project</label>
      <multiselect placeholder="Pick a project"
                   :options="projects"
                   label="name"
                   :searchable="false"
                   @select="populateSkillsChart"></multiselect>
      <pulse-loader :loading="isLoadingSkill"></pulse-loader>
      <pie-chart style="margin:auto;width:60%;" id="resourceSkillCountChart" :chart-data="skillResourceCountDataCollection"></pie-chart>
    </b-card>
    <!--<b-card>-->
      <!--<label for="resourceSkillCountChart">Amount of Resources By Skill By Project</label>-->
      <!--<multiselect placeholder="Pick a project"-->
                   <!--:options="projects"-->
                   <!--label="name"-->
                   <!--:searchable="false"-->
                   <!--@select="populateSkillsChart"></multiselect>-->
      <!--<pulse-loader :loading="isLoadingSkill"></pulse-loader>-->
      <!--<pie-chart id="resourceSkillCountChart" :chart-data="skillResourceCountDataCollection"></pie-chart>-->
    <!--</b-card>-->
    <b-card>
      <label for="resourceGroupCountChart">Amount of Resources By Group By Project</label>
      <multiselect placeholder="Pick a project"
                   :options="projects"
                   label="name"
                   :searchable="false"
                   @select="populateGroupsChart"></multiselect>
      <pulse-loader :loading="isLoadingGroup"></pulse-loader>
      <bar-chart style="margin:auto;width:60%;" id="resourceGroupCountChart" :chart-data="groupResourceCountDataCollection"></bar-chart>
    </b-card>
  </div>
</template>

<script>
  import LineChart from 'src/components/UIComponents/LineChart.js'
  import PieChart from 'src/components/UIComponents/PieChart.js'
  import BarChart from 'src/components/UIComponents/BarChart.js'
  import axios from 'axios'
  import Multiselect from 'vue-multiselect'
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue'

  export default {
    components: {
      LineChart,
      PieChart,
      BarChart,
      Multiselect,
      PulseLoader
    },
    data() {
      return {
        startDateQuery: '',
        endDateQuery: '',
        monthlyResourceDataCollection: null,
        skillResourceCountDataCollection: null,
        groupResourceCountDataCollection: null,
        selectedProject_skills: null,
        selectedProject_groups: null,
        projects: [],
        isLoadingDate: false,
        isLoadingSkill: false,
        isLoadingGroup: false
      }
    },
    mounted() {
      this.fillData()
    },
    methods: {
      populateSkillsChart(project) {
        this.isLoadingSkill = true;
        let info = this;
        axios
          .get(this.$root.serverURL + "/api/resourceSkillNumberData?projectId=" + project.id)
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
            };
            info.isLoadingSkill = false;
          })
          .catch(error => {
            console.log(error);
            info.isLoadingSkill = false;
          });
      },
      populateGroupsChart(project) {
        let info = this;
        this.isLoadingGroup = true;
        axios
          .get(this.$root.serverURL + "/api/resourceGroupNumberData?projectId=" + project.id)
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
            };
            info.isLoadingGroup = false;
          })
          .catch(error => {
            alert("problem loading pie chart" + error);
            info.isLoadingGroup = false;
          });
      },
      fillData() {
        let info = this;
        axios
          .get(this.$root.serverURL + "/api/projects")
          .then(response => {
            info.projects = response.data;
          })
          .catch(error => alert("error getting projects: " + error));
      },
      populateDateRange() {
        let info = this;
        info.isLoadingDate = true;
        if ((info.startDateQuery !== undefined && info.endDateQuery !== undefined) ||
          (info.startDate !== '' && info.endDate !=='')) {
          console.log(info.startDateQuery);
          console.log(info.endDateQuery);
          axios
            .get(this.$root.serverURL + "/api/resourceProjectDateRange?start=" + info.startDateQuery + "&end=" + info.endDateQuery)
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
              };
              info.isLoadingDate = false;
            })
            .catch(error => {
              alert(error);
              info.isLoadingDate = false;
            });
        }
        else {
          alert("Please enter a valid range of dates");
        }
      }
    }
  }
</script>

<style>
</style>

<template>
  <div class="card" id="resourceBreakdownClass">
    <div class="card-title">Resource Breakdown</div>
    <pie-chart v-if="!useBarChart" :chart-data="chartData" v-model="chartOptions" ></pie-chart>
    <bar-chart v-else="useBarChart" :chart-data="chartData" v-model="chartOptions" ></bar-chart>
    <select class="form-control mr-sm-2" id="breakdownFilterSelect" v-model="filterFieldId" @change="switchChart">
      <option v-for="option in filterOptions" v-bind:value="option.id">{{option.value}}</option>
    </select>
  </div>
</template>

<script>
  import ChartCard from 'src/components/UIComponents/Cards/ChartCard.vue'
  import PieChart from 'src/components/UIComponents/PieChart.js'
  import BarChart from 'src/components/UIComponents/BarChart.js'

export default {
    name: 'resource-breakdown',
    components: {
      ChartCard,
      PieChart,
      BarChart
    },
    props: ['resourceData'],

    data () {
        return {
          //chartData: [],
          chartOptions: [],
          useBarChart: false,

          filterFieldId: 1,

          filterOptions: [
            {id: 1, value: "Group"},
            {id: 2, value: "Peer Group"},
            {id: 3, value: "Location"},
            {id: 4, value: "Hours worked"}
          ],

          chartData: {
            labels: ['VueJs', 'EmberJs', 'ReactJs', 'AngularJs'],
            datasets: [{
              backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
              data: [40, 20, 80, 10]
            }]
          },

          chartOptions: {
            responsive: true,
            maintainAspectRatio: false
          }

        }
    },

  created() {

  },

  mounted() {
    document.getElementById("pie-chart").addEventListener("click",
    function(e){
      console.log(e);
      console.log(e.target);
    })
  },

  methods: {
      switchChart() {
        if(this.filterField == 4){
          userBarChart = true;
        }
      },

      testClickChart(event) {
        console.log('clicked chart')
      }
  }


}
</script>

<style scoped>
  #resourceBreakdownClass {
    padding: 1em;
    /*max-width: 30%;*/
  }
</style>

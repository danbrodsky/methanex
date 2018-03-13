<template>
    <chart-card :chart-data="pieChart.data" chart-type="Pie" style="width:80%">
    <template slot="header">
        <h4 class="card-title">Resource Breakdown</h4>
    </template>
    <template slot="body">
      <select class="form-control mr-sm-2" id="breakdownFilterSelect" >
        <option v-for="option in filterOptions" v-bind:value="option.id">{{option.name}}}</option>
      </select>
      <pie-chart v-if="!useBarChart" :chart-data="this.data" v-model="this.chartOptions"></pie-chart>
      <bar-chart v-if="useBarChart" :chart-data="this.data" v-model="this.chartOptions"></bar-chart>
    </template>
    <template slot="footer">
        <div class="legend" style="width: 100%">

        </div>
    </template>
    </chart-card>
</template>

<script>
  import ChartCard from 'src/components/UIComponents/Cards/ChartCard.vue'
  import PieChart from 'src/components/UIComponents/PieChart.js'
  import BarChart from 'src/components/UIComponents/BarChart.js'

export default {
    name: 'resource-breakdown',
    data: [],
    chartOptions: [],
    useBarChart: false,

    filterOptions: [
      {id: 1, value: "Group"},
      {id: 2, value: "Peer Group"},
      {id: 3, value: "Location"}
    ],

    components: {
      ChartCard,
      PieChart,
      BarChart
    },
    data () {
        return {
            pieChart: {
                data: {
                    labels: ['28%', '40%', '32%'],
                    series: [28, 40, 32]
                }
            }
        }
    }
}
</script>

<style>

</style>

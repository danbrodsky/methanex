<template>
  <div class="card" id="resourceBreakdownClass">
    <div class="card-title">Resource Breakdown</div>
    <pie-chart v-if="!useBarChart" :chart-data="calculateChartData" v-model="chartOptions" ></pie-chart>
    <bar-chart v-else="useBarChart" :chart-data="calculateChartData" v-model="chartOptions" ></bar-chart>
    <select class="form-control mr-sm-2" id="breakdownFilterSelect" v-model="filterFieldId" >
      <option v-for="option in filterOptions" v-bind:value="option.id">{{option.name}}</option>
    </select>
  </div>
</template>

<script>
  import ChartCard from 'src/components/UIComponents/Cards/ChartCard.vue'
  import PieChart from 'src/components/UIComponents/PieChart.js'
  import BarChart from 'src/components/UIComponents/BarChart.js'
  import randomColor from 'randomcolor'

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
          chartData: [],
          unfilteredData: [],
          chartOptions: [],
          useBarChart: false,

          filterFieldId: 1,

          filterOptions: [
            {id: 0, name: "Group", value: "group"},
            //{id: 1, name: "Peer Group",value: "peerGroup"},
            {id: 1, name: "Status", value: "status"},
            {id: 2, name: "Location", value: "location"}
           // {id: 5, name: "Hours worked", value: "hours"}
          ],

          chartOptions: {
            responsive: true,
            maintainAspectRatio: false
          }

        }
    },

  computed: {
      calculateChartData: function() {
        console.log("Calculate chart data");
        var filterId = this.filterFieldId;
        self = this;
        console.log(this.filterOptions);
        var filterType = this.filterOptions[filterId].value;
        console.log(this.filterOptions[filterId].value);
        console.log(self.resourceData);
        var result = this.calculateDistribution(filterType);

        console.log("After calculate distribution");
        console.log(this.chartData);

        var numItems = result.count.length;
        var colours = [];
        for(var i=0; i<numItems; i++){
          colours.push(randomColor());
        }

        this.chartData = {
          labels: result.labels,
          datasets:[{
            backgroundColor: colours,
            data: result.count
          }]
        }
        console.log("return chart data");
        console.log(this.chartData);
        return this.chartData;
      }
  },

  methods: {

    calculateDistribution(labelType) {
      var labelMap = new Map();
      var data = this.resourceData;

      console.log(data.length);
      for(var i = 0; i < data.length; i++){
        //console.log(data[i].hasOwnProperty(labelType.toLowerCase()));
        var elem = data[i];
        if(elem.hasOwnProperty(labelType)){
          //console.log(elem.resourceName);
          var labelName = elem[labelType];
          if(labelName == null){
            labelName = "N/A";
          }
          if(labelMap.has(labelName) == false){
            labelMap.set(labelName, 1);
          } else {
            var str = "Increase label by 1" + labelName;
            console.log(labelMap.has(labelName));
            console.log(str)
            labelMap.set(labelName, labelMap.get(labelName) + 1);
          }
        }

      }
      var iterator = labelMap.entries();
      console.log("map");
      console.log(labelMap);

      var result = {
        labels: [],
        count: []
      };

      for(var curr = iterator.next(); !curr.done; curr=iterator.next()){
        console.log(curr.value[0]);
        console.log(curr.value[1]);
        result.labels.push(curr.value[0]);
        result.count.push(curr.value[1]);
      }
      return result;
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

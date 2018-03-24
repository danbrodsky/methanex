<template>
  <div class="card" id="resourceBreakdownClass">
    <div class="card-title">Resource Breakdown</div>
    <pie-chart v-if="!useBarChart" :chart-data="chartData" v-model="chartOptions" ></pie-chart>
    <bar-chart v-else="useBarChart" :chart-data="chartData" v-model="chartOptions" ></bar-chart>
    <select class="form-control mr-sm-2" id="breakdownFilterSelect" v-model="filterFieldId" @change="switchChart">
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
          //chartData: [],
          unfilteredData: [],
          chartOptions: [],
          useBarChart: false,

          filterFieldId: 1,

          filterOptions: [
            {id: 0, name: "Group", value: "group"},
            {id: 1, name: "Peer Group",value: "peerGroup"},
            {id: 2, name: "Status", value: "status"},
            {id: 3, name: "Location", value: "location"}
           // {id: 5, name: "Hours worked", value: "hours"}
          ],

          // since portfolio data is pretty barebones right now, display stub data for resource break down in portfolios
          chartData: {
            labels: ['VueJs', 'EmberJs', 'ReactJs', 'AngularJs'],
            datasets: [{
              backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1BFF'],
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
    // This is being called too early, consider calling on a different event

    // console.log("Created in resourceBreakdown")
    // this.unfilteredData = this.resourceData;
    // if(this.unfilteredData.length > 0) {
    //   this.calculateChartData();
    // }
  },

  mounted() {
    document.getElementById("pie-chart").addEventListener("click",
    function(e){
      console.log(e);
      console.log(e.target);
    })
  },

  methods: {

    calculateDistribution(labelType) {
      var labelMap = new Map();
      var data = this.unfilteredData;

      console.log(data.length);
      for(var i = 0; i < data.length; i++){
        //console.log(data[i].hasOwnProperty(labelType.toLowerCase()));
        var elem = data[i];
        if(elem.hasOwnProperty(labelType)){
          //console.log(elem.resourceName);
          var labelName = elem[labelType];
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

    calculateChartData() {
      var filterId = this.filterFieldId;
      self = this;
      console.log(this.filterOptions);
      var filterType = this.filterOptions[filterId].value;
      console.log(this.filterOptions[filterId].value);
      console.log(self.resourceData);
      var result = this.calculateDistribution(filterType);

      this.chartData.datasets.data = [];
      this.chartData.datasets.backgroundColor = [];


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
      console.log(this.chartData);

        // chartData: {
        // labels: ['VueJs', 'EmberJs', 'ReactJs', 'AngularJs'],
        //   datasets: [{
        //   backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1BFF'],
        //   data: [40, 20, 80, 10]
        // }]

      //console.log(labelName);
      // switch(filter) {
      //   case 1:
      //     console.log(filter);
      //     labelName =
      //     break;
      //   case 2:
      //     console.log(filter);
      //     break;
      //   case 3:
      //     console.log(filter);
      //     break;
      //   case 4:
      //     console.log(filter);
      //     break;
      //   default:
      //     console.log("default");
      //     console.log(filter);
      //     console.log("end");
      // }
    },

      switchChart() {
        if(this.filterField == 4){
          userBarChart = true;
        }
        this.calculateChartData();
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

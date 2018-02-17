<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="filters">
          <span>Search: </span>
          <input v-model="searchKeyword" v-on:input="filterPortfolios" />
        </div>
      </div>
      <div class="row">
          <portfolio-card style="margin: 0.5%;box-shadow: 5px 5px 5px grey;"
            v-for="portfolio of portfoliosDisplayed"
            v-bind:key="portfolio.classification"
            v-bind="portfolio">
          </portfolio-card>
      </div>
    </div>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import LTable from 'src/components/UIComponents/Table.vue'
  import Checkbox from 'src/components/UIComponents/Inputs/Checkbox.vue'
  import PortfolioCard from 'src/components/UIComponents/Cards/PortfolioCard.vue'
  import AddProjectCard from 'src/components/UIComponents/Cards/AddProjectCard.vue'
  import _ from 'lodash'
  import axios from 'axios'

  export default {
    components: {
      Checkbox,
      Card,
      LTable,
      PortfolioCard,
      AddProjectCard
    },
    created() {
      this.fetchData();
    },
    data() {
      return {
        // portfolios: [],
        portfolios: [
          {
            classification: 'Top Secret',
            businessOwner: 'Mark Penderson',
            numProjects: 52,
            totalBudget: 800000
          },
          {
            classification: 'Sheep hunting operations',
            businessOwner: 'Nigel',
            numProjects: 8,
            totalBudget: 1000000
          },
          {
            classification: 'Financials',
            businessOwner: 'John Doe',
            numProjects: 29,
            totalBudget: 550000
          }
        ],
        portfoliosDisplayed: [
          {
            classification: 'Top Secret',
            businessOwner: 'Mark Penderson',
            numProjects: 52,
            totalBudget: 800000
          },
          {
            classification: 'Sheep hunting operations',
            businessOwner: 'Nigel',
            numProjects: 8,
            totalBudget: 1000000
          },
          {
            classification: 'Financials',
            businessOwner: 'John Doe',
            numProjects: 29,
            totalBudget: 550000
          }
        ],
        searchKeyword: '',
      }
    },
    methods: {
      fetchData() {
        axios.get("https://peaceful-hamlet-75445.herokuapp.com/api/projects")
          .then(response => {
            this.portfoliosDisplayed = response.data;
          })
      },
      filterPortfolios: _.debounce(function () {
        this.portfoliosDisplayed = [];
        for(var aPortfolio of this.portfolios) {
            if(aPortfolio.classification.toLowerCase().indexOf(this.searchKeyword.toLowerCase())!=-1) {
                this.portfoliosDisplayed.push(aPortfolio);
            }
        }
      }, 500)

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

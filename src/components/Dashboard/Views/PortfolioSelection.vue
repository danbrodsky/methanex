<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="filters">
          <span>Search: </span>
          <input v-model="searchKeyword" v-on:input="filterPortfolios" />
          <div class="row" style="margin: 0.5%;" v-if='hasAccess()'>
            <button type="submit" class="btn btn-info btn-fill float-right" v-on:click="createPortfolio()">
              Add Portfolio
            </button>
          </div>
        </div>
      </div>
      <div class="row">
          <portfolio-card style="margin: 0.5%;box-shadow: 5px 5px 5px grey;cursor:pointer;"
            v-for="portfolio of portfoliosDisplayed"
            v-bind:key="portfolio.id"
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
  import AddPortfolioCard from 'src/components/UIComponents/Cards/AddPortfolioCard.vue'
  import _ from 'lodash'
  import axios from 'axios'

  export default {
    components: {
      Checkbox,
      Card,
      LTable,
      PortfolioCard,
      AddProjectCard,
      AddPortfolioCard
    },
    created() {
      let that = this;
      axios.get(this.$root.serverURL + "/user/" + JSON.parse(that.$root.$data.cookies.get('user')).id + "/roles")
        .then(response => {
          that.role = response.data[0].name;
      })
      this.fetchData();
    },
    data() {
      return {
        portfolios: [],
        portfoliosDisplayed: [],
        role: '',
        searchKeyword: ''
      }
    },
    methods: {
      fetchData() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/portfolios")
          .then(response => {
            info.portfolios = response.data;
            for (var p of info.portfolios){
              p.numProjects = p.projects.length;
            }
            info.portfoliosDisplayed = info.portfolios.slice();
          })
      },
      hasAccess() {
        return this.role == "ROLE_ADMIN";
      },
      createPortfolio(){
        this.$router.push({path: '/admin/create-portfolio'});
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

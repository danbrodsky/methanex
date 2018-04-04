<template>
  <div class="content">
    <div class="container-fluid">
      <div>
      <b-alert :show=updatedPortfolioSuccessBanner dismissible variant="success">
        <h4 class="alert-heading">Your information has been updated</h4>
        <p>
          Please refresh the page to view any changes
        </p>
      </b-alert>
        <b-alert :show=createPortfolioBanner dismissible variant="success">
          <h4 class="alert-heading">Portfolio Added</h4>
        </b-alert>
      </div>
      <div class="row">
        <button v-if="hasAccess()" v-b-modal.addPortfolioModal class="btn btn-info btn-fill float-right">
          <b style="font-size: large">+</b>
        </button>
        <b-modal
          id="addPortfolioModal"
          ref="savePortfolio"
          @ok="savePortfolio">
          <div>
            <b-card title="Add A Portfolio" style="border:none;">
              <b-form-input id="nestedName"
                            v-model="newPortfolio.classification"
                            type="text"
                            placeholder="Enter a name"></b-form-input>
            </b-card>
          </div>
        </b-modal>
      </div>
      <div class="row">
        <pulse-loader :loading="isLoadingPortfolios"></pulse-loader>
        <portfolio-card style="margin: 0.5%;box-shadow: 5px 5px 5px grey;cursor:pointer;border:1px;border-style:solid;border-color:#cccccc;"
                        v-for="portfolio of portfoliosDisplayed"
                        v-bind:key="portfolio.id"
                        v-bind:portfolio="portfolio"
                        v-bind:role="role"
                        v-on:portfolio-remove="remove"
                        v-on:portfolio-alert="alert">
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
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue'

  export default {
    components: {
      Checkbox,
      Card,
      LTable,
      PortfolioCard,
      AddProjectCard,
      AddPortfolioCard,
      PulseLoader
    },
    created() {
      let that = this;
      axios.get(this.$root.serverURL + "/user/" + JSON.parse(that.$root.$data.cookies.get('user')).id + "/roles")
        .then(response => {
          that.role = response.data[0].name;
        })
        .catch(error => console.log(error));
      this.fetchData();
    },
    data() {
      return {
        isLoadingPortfolios: true,
        updatedPortfolioSuccessBanner: false,
        createPortfolioBanner: false,
        portfolios: [],
        portfoliosDisplayed: [],
        role: '',
        searchKeyword: '',
        newPortfolio: {
          classification: null
        }
      }
    },
    methods: {
      goToPortfolio (id) {
        this.$router.push({path: `/admin/portfolio/${id}`});
      },
      savePortfolio() {
        let info = this;
        if (this.newPortfolio.classification) {
          axios
            .post(this.$root.serverURL + "/api/portfolios", this.newPortfolio)
            .then(() => {
              info.createPortfolioBanner = true;
              info.fetchData();
            })
            .catch(error => console.log(error));
        }
      },
      fetchData() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/portfolios")
          .then(response => {
            info.portfolios = response.data;
            for (let i=0; i<info.portfolios.length;i++){
              info.portfolios[i].numProjects = info.portfolios[i].projects.length;
            }
            info.portfoliosDisplayed = info.portfolios.slice();
            info.isLoadingPortfolios = false;
          })
          .catch(error => {
            console.log(error);
            info.isLoadingPortfolios = false;
          });
      },
      hasAccess() {
        return this.role == "ROLE_ADMIN";
      },
      createPortfolio(){
        this.$router.push({path: '/admin/create-portfolio'});
      },
      remove(id) {
        this.portfoliosDisplayed.splice(id, 1);
      },
      alert (){
        this.updatedPortfolioSuccessBanner = true;
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

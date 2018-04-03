<template>
  <div class="content">
    <b-navbar toggleable="md" type="light">

      <b-collapse is-nav id="nav_collapse">
        <b-navbar-nav>
          <button v-b-modal.addPortfolioModal class="btn btn-success btn-fill float-right">
            <b style="font-size: large">+</b>
          </button>
          <b-modal
            id="addPortfolioModal"
            ref="savePortfolio"
            @ok="savePortfolio">
            <div>
              <b-card title="Add A Portfolio" bg-variant="light">
                <b-form-input id="nestedName"
                              v-model="newPortfolio.classification"
                              type="text"
                              placeholder="Enter a name"></b-form-input>
              </b-card>
            </div>
          </b-modal>
        </b-navbar-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">

          <b-nav-form>
            <b-form-input size="sm" class="mr-sm-2" type="text" placeholder="Search"/>
            <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
          </b-nav-form>

        </b-navbar-nav>

      </b-collapse>
    </b-navbar>
    <div class="container-fluid">
      <div>
        <b-alert :show=createPortfolioBanner dismissible variant="success">
          <h4 class="alert-heading">Portfolio Added</h4>
        </b-alert>
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
        .catch(error => console.log(error));
      this.fetchData();
    },
    data() {
      return {
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
        console.log(id);
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
            for (var p of info.portfolios){
              p.numProjects = p.projects.length;
            }
            info.portfoliosDisplayed = info.portfolios.slice();
          })
          .catch(error => console.log(error));
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

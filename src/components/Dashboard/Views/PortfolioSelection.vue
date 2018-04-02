<template>
  <div class="content">
    <b-navbar toggleable="md" type="light">

      <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

      <b-navbar-brand href="#">NavBar</b-navbar-brand>

      <b-collapse is-nav id="nav_collapse">

        <b-navbar-nav>
          <b-nav-item href="#">Link</b-nav-item>
          <b-nav-item href="#" disabled>Disabled</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">

          <b-nav-form>
            <b-form-input size="sm" class="mr-sm-2" type="text" placeholder="Search"/>
            <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
          </b-nav-form>

          <b-nav-item-dropdown text="Lang" right>
            <b-dropdown-item href="#">EN</b-dropdown-item>
            <b-dropdown-item href="#">ES</b-dropdown-item>
            <b-dropdown-item href="#">RU</b-dropdown-item>
            <b-dropdown-item href="#">FA</b-dropdown-item>
          </b-nav-item-dropdown>

          <b-nav-item-dropdown right>
            <!-- Using button-content slot -->
            <template slot="button-content">
              <em>User</em>
            </template>
            <b-dropdown-item href="#">Profile</b-dropdown-item>
            <b-dropdown-item href="#">Signout</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>

      </b-collapse>
    </b-navbar>
    <div class="container-fluid">
      <div>
        <b-alert :show=createPortfolioBanner dismissible variant="success">
          <h4 class="alert-heading">Portfolio Added</h4>
        </b-alert>
      </div>
      <div>
        <b-card-group>
          <b-card title="Card Title"
                  img-src="https://lorempixel.com/300/300/"
                  img-alt="Image"
                  img-top
                  tag="article"
                  style="max-width: 20rem;"
                  class="mb-2"
                  @click
                  v-for="portfolio of portfoliosDisplayed"
                  v-on:click="goToPortfolio(portfolio.id)">
            <p class="card-text">
              Hello, my name is Mark
            </p>
          </b-card>
        </b-card-group>
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
            console.log(response.data);
            info.portfolios = response.data;
            for (let i =0; i < info.portfolios.length; i++){
              info.portfolios[i].numProjects = info.portfolios[i].projects.length;
              if (info.portfolios[i].businessOwner != null)
                info.portfolios[i].businessOwner = info.portfolios[i].businessOwner.name;
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

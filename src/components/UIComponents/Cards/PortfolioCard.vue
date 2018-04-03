<template>
  <div class="card" v-on:click="goToPortfolio">
    <div class="card-header">
      <span span class="header">
<span style="font-weight:bold;float:left;">{{ portfolio.classification }}</span>
<div v-on:click.stop class="dropdown" style="float: right;">
  <button v-if='hasAccess()' class="dropbtn">&#8942;</button>
  <div class="dropdown-content">
    <a v-on:click.stop v-b-modal.editPortfolioModal>Edit</a>
              <b-modal
                id="editPortfolioModal"
                ref="editPortfolio"
                @ok="editPortfolio"
                @cancel="refresh">
                <div id="modalContainer">
                  <b-card bg-variant="light" id="portfolioForm">
                    <b-form-group horizontal
                                  breakpoint="lg"
                                  label="Edit Portfolio"
                                  label-size="lg"
                                  label-class="font-weight-bold pt-0"
                                  class="mb-0">
                      <b-form-group horizontal
                                    label="Class:"
                                    label-class="text-sm-right"
                                    label-for="nestedName">
                        <b-form-input id="formPlaceholder"
                                      v-model="portfolio.classification"
                                      type="text"
                                      placeholder="Enter classification"></b-form-input>
                      </b-form-group>
                      <b-form-group horizontal
                                    label="Resource Breakdown:"
                                    label-class="text-sm-right"
                                    label-for="nestedResourceBreakdown">
                        <b-form-input id="formPlaceholder"
                                      v-model="portfolio.resourceBreakdown"
                                      type="text"
                                      placeholder="Enter resource breakdown"></b-form-input>
                      </b-form-group>
                      <b-form-group horizontal
                                    label="Business Owner:"
                                    label-class="text-sm-right"
                                    label-for="nestedLocation">
                        <b-form-input id="formPlaceholder"
                                      v-model="portfolio.businessOwner"
                                      type="text"
                                      placeholder="Enter Business Owner"></b-form-input>
                      </b-form-group>
                    </b-form-group>
                  </b-card>
                </div>
              </b-modal>
    <a v-on:click.stop="deletePortfolio">Delete</a>
  </div>
</div>
      </span>
    </div>
    <div class="card-body">
          <div class="body-data">
          <span><i class="fa fa-user"></i> {{ portfolio.businessOwner }}</span>
      </div>
      <div class="body-data">
          <span><i class="fa fa-money"></i> ${{ portfolio.totalBudget }}</span>
      </div>
      <div class="body-data">
          <span><i class="fa fa-th"></i> {{ portfolio.numProjects }}</span>
      </div>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'
  import LTable from 'src/components/UIComponents/Table.vue'
  import Card from 'src/components/UIComponents/Cards/Card.vue'

  export default {
    components: {
      LTable,
      Card
    },
    name: 'portfolio-card',
    props: {
        portfolio: {
            type: Object,
            default() { return {} }
        },
        role: {
          type: String,
          default: ''
        }
    },
    created() {
      if (this.portfolio.businessOwner != null) {
        this.portfolio.businessOwner = this.portfolio.businessOwner;
      }
      else {
        this.portfolio.businessOwner = 'N/A';
      }
    },
    data () {
        return {
        }
    },
    methods: {
      goToPortfolio () {
        this.$router.push({path: `/admin/portfolio/${this.portfolio.id}`});
      },
      editPortfolio () {
        let info = this;
        axios.put(this.$root.serverURL + `/api/portfolios/${this.portfolio.id}`, {
          id: info.portfolio.id,
          classification: info.portfolio.classification,
          // businessOwner: info.portfolio.businessOwner,
          resourceBreakdown: info.portfolio.resourceBreakdown
        })
          .then(() => this.$router.push({path: `/admin/portfolio-selection`}))
          .catch(() => console.log("error while editing portfolio"))
      },
      hasAccess () {
        return this.role == "ROLE_ADMIN";
      },
      refresh () {
        this.$router.push({path: `/admin/portfolio-selection`});
      },
      deletePortfolio () {
        let info = this;
        axios
          .delete(info.$root.serverURL + `/api/portfolios/${this.portfolio.id}`)
          .then(function (res) {
          })
          .catch(error => console.log(error));
        this.$emit('portfolio-remove', this.id);
      }
    }
  }

</script>
<style scoped>
    div.card {
        width: 24%;
    background-color: inherit;
    }
    div.body-data {
        padding-top: 0.5em;
        padding-bottom: 0.5em;
    },
.dropbtn {
    font-size: 16px;
    border: none;
    background: initial;
    cursor: pointer;
}

.dropdown {
    display: inline-block;
}

.dropdown-content {
    z-index: 1;
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {background-color: grey}

.dropdown:hover .dropdown-content {
    display: block;
}

.dropdown:hover .dropbtn {
    background-color: grey;
    color: #FFF;
}
#portfolioForm {
  width: 100%;
}
#modalContainer {
  width: 105%;
  position: relative;
  left: -2.5%;
}
#formPlaceholder {
  background-color:white;
  outline-color: black;
}
</style>

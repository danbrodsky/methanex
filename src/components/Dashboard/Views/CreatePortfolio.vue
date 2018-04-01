<template>
  <div class="content">
    <card>
      <h4 slot="header" class="card-title">Create Portfolio</h4>
      <form>
        <div class="row">
          <div class="col-md-5">
            <fg-input type="text"
                      label="Classification"
                      placeholder="Classification"
                      v-model="portfolio.location">
            </fg-input>
          </div>
          <div class="col-md-5">
            <fg-input type="text"
                      label="Business Owner"
                      placeholder="Business Owner"
                      v-model="portfolio.businessOwner">
            </fg-input>
          </div>
        </div>

        <div class="row">
          <div class="col-md-6">
            <fg-input type="text"
                      label="Resource Breakdown"
                      placeholder="Resource Breakdown"
                      v-model="portfolio.resourceBreakdown">
            </fg-input>
          </div>
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-info btn-fill float-right" @click="updateProfile">
            Save
          </button>
        </div>
        <div class="clearfix"></div>
      </form>
    </card>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import axios from 'axios'

  export default {
    components: {
      Card
    },
    data() {
      return {
        modalShow: false,
        values: [],
        portfolio: {
          classification: '',
          businessOwner: '',
          resourceBreakdown: ''
        }
      }
    },
    methods: {
      updateProfile() {
        var info = this;
        axios.post(info.$root.serverURL + "/api/portfolios", {
          "classification": info.portfolio.classification,
          "businessOwner": info.portfolio.businessOwner,
          "resourceBreakdown": info.portfolio.resourceBreakdown
        })
          .then(function () {
            info.$router.push({path: `/admin/portfolio`});
          })
          .catch(() => console.log("error while creating portfolio"));
      }
    }
  }
</script>
<style>
</style>

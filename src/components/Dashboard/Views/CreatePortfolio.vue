<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-6" id="addPortfolioForm">
          <label for="addPortfolioForm" class="col-form-label">ID </label>
          <div class="form-group align-items-left" style="min-width:380px">
            <div class="col-xs-6">
              <label for="status" class="col-form-label">Portfolio Name</label>
              <textarea type="title" class="form-control form-control-lg" id="status"></textarea>
            </div>
            <div class="col-xs-6">
              <label for="addPortfolioForm" class="col-form-label">Classification</label>
              <input type="text" style="width:25%;" class="form-control form-control-sm-4"
                     id="classificationInput" v-bind:value="classification">
              <label for="addPortfolioForm" class="col-form-label">Business Owner</label>
              <input type="text" style="width:25%;" class="form-control form-control-sm-4"
                     v-bind:value="businessOwner">
              <label for="addPortfolioForm" class="col-form-label">Total Budget</label>
              <input type="text" style="width:25%;" class="form-control form-control-sm-4"
                     v-bind:value="totalBudget">
            </div>
          </div>
        </div>
        <!--<div class="col-2">-->
          <!--<div class="form-group" style="width: 100px">-->
            <!--<button type="button" v-if="isNewPortfolio" v-on:click="addNewPortfolio" style="display:block" class="btn btn-block">Add New Portfolio</button>-->
            <!--<button type="button" v-if="!editMode" v-on:click="enableEdit" style="display:block" class="btn btn-success btn-block">Edit</button>-->
            <!--<button type="button" v-else-if="!isNewPortfolio && isProjectManager" style="display:block;" class="btn btn-success btn-block" v-on:click="updateProject">Update</button>-->
            <!--<button type="button" class="mt-2 btn btn-danger btn-block" v-if="!isNewPortfolio && isProjectManager" >Delete</button>-->
          <!--</div>-->
        <!--</div>-->
                <!--this is the 'edit' button-->
      </div>
      <div class="row">
      </div>
    </div>
  </div>
</template>

<script>
  import Portfolio from 'src/components/Dashboard/Views/Portfolio.vue'

  export default {
        name: "create-portfolio",
        created() {
            console.log("Portfolio creation page");
        },
    data () {
      return {
        portfolio: {
          "id": -1,
          "name": null,
          "classification": null,
          "businessOwner": null,
          "totalBudget": -1
        }
      }
    },
    methods: {

        }
  }






</script>

<style scoped>

</style>

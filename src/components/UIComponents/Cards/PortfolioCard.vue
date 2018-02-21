<template>
  <div class="card" v-on:click="goToPortfolio">
    <div class="card-header">
      <div name="header" style="align: center"><span style="font-weight:bold">{{ classification }}</span></div>
      <div name="header" style="font-weight:bold; color:#888888"></div>
    </div>
    <div class="card-body">
      <div class="body-data">
          <span><i class="fa fa-user"></i> {{ businessOwner }}</span>
      </div>
      <div class="body-data">
          <span><i class="fa fa-money"></i> ${{ totalBudget }}</span>
      </div>
      <div class="body-data">
          <span><i class="fa fa-th"></i> {{ numProjects }}</span>
      </div>
    </div>
    <div class="card-footer" v-if="$slots.footer">
      <hr>
      <slot name="footer"></slot>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'project-card',
    props: {
        classification: {
            type: String,
            default: 'Classification'
        },
        businessOwner: {
            type: String,
            default: 'Business Owner Name'
        },
        numProjects: {
            type: Number,
            default: -1
        },
        totalBudget: {
            type: Number,
            default: -1
        }
    },
    data () {
        return {
            // classification: this.classification,
            // businessOwner: this.businessOwner,
            // numProjects: this.numProjects,
            // totalBudget: this.totalBudget
        }
    },
    methods: {
      goToPortfolio () {
        this.$router.push({path: '/admin/overview'});
      }

    }
  }

</script>
<style scoped>
    div.card {
        min-width: 280px;
    }
    div.body-data {
        padding-top: 0.5em;
        padding-bottom: 0.5em;
    }
</style>

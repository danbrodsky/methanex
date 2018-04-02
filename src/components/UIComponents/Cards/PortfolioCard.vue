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
  </div>
</template>
<script>
  export default {
    name: 'portfolio-card',
    props: {
        id: {
            type: Number,
            default: -1
        },
        classification: {
            type: String,
            default: ''
        },
        businessOwner: {
            default: 'Business Owner Name'
        },
        numProjects: {
            type: Number,
            default: 0
        },
        totalBudget: {
            type: Number,
            default: 0
        }
    },
    created() {
      if (this.props.businessOwner != null) {
        this.props.businessOwner = this.props.businessOwner.name;
      }
      else {
        this.props.businessOwner = 'N/A';
      }
    },
    data () {
        return {
        }
    },
    methods: {
      goToPortfolio () {
        this.$router.push({path: `/admin/portfolio/${this.id}`});
      }
    }
  }

</script>
<style scoped>
    div.card {
        width: 24%;
    }
    div.body-data {
        padding-top: 0.5em;
        padding-bottom: 0.5em;
    }
</style>

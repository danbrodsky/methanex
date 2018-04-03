<template>
  <div class="card" v-on:click="goToPortfolio">
    <div class="card-header">
      <span span class="header">
<span style="font-weight:bold;float:left;">{{ classification }}</span>
<div class="dropdown"  style="float: right;">
  <button class="dropbtn">&#8942;</button>
  <div class="dropdown-content">
    <a href="#">Edit</a>
    <a href="#">Delete</a>
  </div>
</div>
      </span>
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
        this.props.businessOwner = this.props.businessOwner;
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
</style>

<template>
  <div class="card">
    <div style="font-size:1000%;width:100%;text-align:center" v-on:click="createNewPortfolio">+</div>
    <div class="card-footer" v-if="$slots.footer">
      <hr>
      <slot name="footer"></slot>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'add-portfolio-card',
    methods: {
      createNewPortfolio() {
        this.$router.push({path: '/admin/create-portfolio'});
      }
    }
  }

</script>
<style scoped>
    div.card {
        width: 240px;
    }
    div.body-data {
        padding-top: 0.5em;
        padding-bottom: 0.5em;
    }
</style>

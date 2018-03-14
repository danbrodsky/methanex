<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
            <template slot="header">
              <div class="row">
              <div class="col-8">
                <h4 class="card-title">Resources</h4>
                <p class="card-category">All Methanex Resources</p>
              </div>
              <div class="col-4">
                <form id="search">
                    Search <input name="query" v-model="searchQuery">
                </form>
              </div>
              </div>
            </template>
            <div class="table-responsive">
              <l-table class="table-hover table-striped"
                       :columns="columns"
                       :data="rows"
                       :filter-key="searchQuery">
              </l-table>
            </div>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import LTable from 'src/components/UIComponents/Table.vue'
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import axios from 'axios'
  const tableColumns = ['Name', 'Group', 'Status', 'Email'];

  export default {
    components: {
      LTable,
      Card
    },
    created () {
      this.fetchData();
    },
    data() {
      return {
        columns: [...tableColumns],
        rows: [],
        searchQuery: ''
      }
    },
    methods: {
      fetchData() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/resources")
          .then(response => {
            info.rows = response.data;
          })
      }
    }
  }
</script>
<style>
</style>

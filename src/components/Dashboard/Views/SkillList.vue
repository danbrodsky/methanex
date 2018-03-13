<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
            <template slot="header">
              <h4 class="card-title">Technical Skills</h4>
                <form id="search1">
                    Search <input name="query1" v-model="searchQuery1">
                </form>
            </template>
            <div class="table-responsive">
              <l-table class="table-hover table-striped"
                       :columns="columns"
                       :data="rows"
                       :filter-key="searchQuery1">
              </l-table>
            </div>
          </card>
          <card>
            <template slot="header">
              <h4 class="card-title">Non-technical Skills</h4>
                <form id="search2">
                    Search <input name="query2" v-model="searchQuery2">
                </form>
            </template>
            <div class="table-responsive">
              <l-table class="table-hover table-striped"
                       :columns="hardColumns"
                       :data="hardData"
                       :filter-key="searchQuery2">
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
  const tableColumns = ['Name', 'Description'];

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
        searchQuery1: '',
        searchQuery2: '',
        hardColumns: ['Name', 'Description'],
        hardData: [
            { name: 'Leadership', description: 'Lead a team' },
            { name: 'Project Management', description: 'Manage projects' },
            { name: 'Communication', description: 'talk with people' }
    ]
      }
    },
    methods: {
      fetchData() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/skills")
          .then(response => {
            info.rows = response.data;
          })
      }
    }
  }
</script>
<style>
</style>
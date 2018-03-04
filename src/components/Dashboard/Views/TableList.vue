<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
            <template slot="header">
              <h4 class="card-title">Resources</h4>
              <p class="card-category">All Methanex Resources</p>
            </template>
            <div class="table-responsive">
              <l-table class="table-hover table-striped"
                       :columns="columns"
                       :data="rows">
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

  const tableData = [{
    name: 'Bob Smith',
    group: 'Finance',
    status: 'Allocated',
    email: 'smith@gmail.com'
  },
  {
    name: 'Minerva Hooper',
    group: 'IT',
    status: 'Allocated',
    email: 'minerva@gmail.com'
  },
  {
    name: 'Max Koerner',
    group: 'Finance',
    status: 'Allocated',
    email: 'koerner@gmail.com'
  },
  {
    name: 'Richard Cheese',
    group: 'IT',
    status: 'Allocated',
    email: 'cheese@gmail.com'
  },
  {
    name: 'Mark Plez',
    group: 'Other',
    status: 'Allocated',
    email: 'plez@gmail.com'
  }];


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
        rows: [...tableData]
      }
    },
    methods: {
      fetchData() {
        var info = this;
        axios.get("https://peaceful-hamlet-75445.herokuapp.com/api/resources")
          .then(response => {
            // info.rows = response.data;
          })
      }
    }
  }
</script>
<style>
</style>

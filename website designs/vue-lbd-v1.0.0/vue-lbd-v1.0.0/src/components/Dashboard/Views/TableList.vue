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
                       :columns="table1.columns"
                       :data="table1.data">
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
  const tableColumns = ['ID', 'Name', 'Group', 'Location', 'Manager', 'Status', 'Email']
  const tableData = [{
    id: 1,
    name: 'Dakota Rice',
    group: 'IT',
    location: 'Vancouver, Canada',
    manager: 'Nigel',
    status: 'Allocated',
    email: 'dakota@gmail.com'
  },
  {
    id: 2,
    name: 'Minerva Hooper',
    group: 'IT',
    location: 'Queenstown, New Zealand',
    manager: 'Andrew',
    status: 'Allocated',
    email: 'Minerva@gmail.com'
  },
  {
    id: 3,
    name: 'Sage Rodriguez',
    group: 'IT',
    location: 'Vancouver, Canada',
    manager: 'Nigel',
    status: 'Allocated',
    email: 'Sage@gmail.com'
  },
  {
    id: 4,
    name: 'Philip Chaney',
    group: 'IT',
    location: 'Vancouver, Canada',
    manager: 'Nigel',
    status: 'Allocated',
    email: 'Philip@gmail.com'
  },
  {
    id: 5,
    name: 'Doris Greene',
    group: 'IT',
    location: 'Vancouver, Canada',
    manager: 'Nigel',
    status: 'Allocated',
    email: 'Doris@gmail.com'
  }]
  export default {
    components: {
      LTable,
      Card
    },
    data () {
      return {
        table1: {
          columns: [...tableColumns],
          data: [...tableData]
        }
      }
    }
  }
</script>
<style>
</style>

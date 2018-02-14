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
  const tableColumns = ['Name', 'Group', 'Status', 'Email'];
  const tableData = [{
    name: 'Boobs McGee',
    group: 'Finance',
    status: 'Allocated',
    email: 'boobs@gmail.com'
  },
  {
    name: 'Minerva Hooper',
    group: 'IT',
    status: 'Allocated',
    email: 'Minerva@gmail.com'
  },
  {
    name: 'Hugh Koch',
    group: 'Finance',
    status: 'Allocated',
    email: 'koch@gmail.com'
  },
  {
    name: 'Richard Cheese',
    group: 'IT',
    status: 'Allocated',
    email: 'cheese@gmail.com'
  },
  {
    name: 'Moby Dick',
    group: 'Some other group',
    status: 'Allocated',
    email: 'moby@gmail.com'
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

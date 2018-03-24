<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
            <template slot="header">
              <div class="row">
                <div class="col-8">
                  <h4 class="card-title">All Portfolios</h4>
                  <p class="card-category">Filter attributes to see desired portfolios</p>
                </div>
                <div class="col-4">
                </div>
              </div>
            </template>
            <vue-good-table
              :columns="columnsPortfolio"
              :paginate="true"
              :rows="rowsPortfolio"
              :globalSearch = "false"
              styleClass="table table-bordered table-striped">
              <template slot="table-column" slot-scope="props">
                {{props.column.label}}
              </template>
            </vue-good-table>
          </card>
          <card>
            <template slot="header">
              <div class="row">
                <div class="col-8">
                  <h4 class="card-title">All Projects</h4>
                  <p class="card-category">Filter projects to see desired project report</p>
                </div>
                <div class="col-4">
                </div>
              </div>
            </template>
            <vue-good-table
              :columns="columnsProject"
              :paginate="true"
              :rows="rowsProject"
              :globalSearch = "false"
              styleClass="table table-bordered table-striped">
              <template slot="table-column" slot-scope="props">
                    {{props.column.label}}
              </template>
            </vue-good-table>
          </card>
          <card>
            <template slot="header">
              <div class="row">
                <div class="col-8">
                  <h4 class="card-title">All Resources</h4>
                  <p class="card-category">Filter resources to see desired report</p>
                </div>
                <div class="col-4">
                </div>
              </div>
            </template>
            <vue-good-table
              :columns="columnsResource"
              :paginate="true"
              :rows="rowsResource"
              :globalSearch = "false"
              styleClass="table table-bordered table-striped">
              <template slot="table-column" slot-scope="props">
                {{props.column.label}}
              </template>
            </vue-good-table>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import axios from 'axios'

  const tableColumns = ['Name', 'ProjectStatus', 'Manager', 'ProjectOwner', 'Status', 'ProjectResources', 'Budget', 'Budget Used'];
  // 'Start Date', 'End Date'

  import Vue from 'vue';
  import VueGoodTable from 'vue-good-table';

  Vue.use(VueGoodTable);

  export default {
    components: {
      Card
    },

    name: 'Checkbox-table',
    created () {
      this.fetchData();
    },
    status: 'Checkbox-table',
    created () {
      this.fetchData();
    },
    rag_status: 'Checkbox-table',
    created () {
      this.fetchData();
    },
    businessOwner: 'Checkbox-table',
    created () {
      this.fetchData();
    },


    data () {
      return {
        columnsPortfolio: [
          {
            label: 'ID',
            field: 'id',
            filterable: true,
          },
          {
            label: 'Classification',
            field: 'classification',
            type: 'string',
            filterable: true,
          },
          {
            label: 'Business Owner',
            field: 'businessOwner',
            filterable: true,
          },
          {
            label: 'Resource Breakdown',
            field: 'resourceBreakdown',
            filterable: true,
          },
        ],
        columnsProject: [
          {
            label: 'Name',
            field: 'name',
            filterable: true,
          },
          {
            label: 'Project Status',
            field: 'status',
            filterable: true,
          },
          {
            label: 'Manager',
            field: 'manager',
            type: 'string',
            filterable: true,
          },
          {
            label: 'Project Owner',
            field: 'businessOwner',
            filterable: true,
          },
          {
            label: 'RAG Status',
            field: 'rag_status',
            filterable: true,
          },
          {
            label: 'Number of Resources',
            field: 'projectResourses',
            filterable: true,
          },
          {
            label: 'Budget',
            field: 'budget',
            filterable: true,
          },
          {
            label: 'Budget Used',
            field: 'budget_used',
            filterable: true,
          }
          // {
          //   label: 'Start Date',
          //   field: 'start_date',
          //   filterable: true,
          // },
          // {
          //   label: 'End Date',
          //   field: 'end_date',
          //   filterable: true,
          // },
        ],
        columnsResource: [
          {
            label: 'Name',
            field: 'name',
            filterable: true,
          },
          {
            label: 'Email',
            field: 'email',
            type: 'string',
            filterable: true,
          },
          {
            label: 'Location',
            field: 'location',
            filterable: true,
          },
          {
            label: 'Group',
            field: 'group',
            filterable: true,
          },
        ],
        rowsProject: [],
        rowsResource: [],
        rowsPortfolio: [],
      };
    },
    methods: {
      fetchData() {
        let info = this;
        axios.get(this.$root.serverURL + "/api/portfolios")
          .then(response => {
            info.rowsPortfolio = response.data;
            for (let i = 0; i < info.rowsPortfolio.length; i++){
              info.rowsPortfolio[i].businessOwner = info.rowsPortfolio[i].businessOwner.name;
              info.rowsPortfolio[i].classification = info.rowsPortfolio[i].classification.name;
            }
            console.log(response.data);
          });

        axios.get(this.$root.serverURL + "/api/projects")
          .then(response => {
            info.rows = response.data;
            for (let i = 0; i < info.rows.length; i++){
              if (info.rows[i].manager != null)
                info.rows[i].manager = info.rows[i].manager.name;
              }
            console.log(info.rows);
            axios.get(this.$root.serverURL + "/api/resources")
              .then(response => {
                info.rowsResource = response.data;
          })
      })
    }
  }
</script>
<style>
</style>

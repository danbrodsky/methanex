<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
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
              :columns="columns"
              :paginate="true"
              :rows="rows"
              :globalSearch = "false"
              styleClass="table table-bordered table-striped">
              <template slot="table-column" slot-scope="props">
                <span v-if="props.column.label =='SelectAll'">
                  <label class="checkbox">
                    <input
                      type="checkbox"
                      @click="toggleSelectAll()">
                  </label>
                </span>
                <span v-else>
                    {{props.column.label}}
                </span>
              </template>
              <template slot="table-row-before" slot-scope="props">
                <td>
                  <label class="checkbox">
                    <input type="checkbox" v-model="rows[props.row.originalIndex].selected">
                  </label>
                </td>
              </template>
            </vue-good-table>
            <button type="submit" aria-hidden="true" v-on:click="createPortfolio()" style='margin-right:16px' class="btn btn-success btn-sm btn-fill float-right">Create</button>
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
  const tableColumns = ['Name', 'ProjectStatus', 'Manager', 'ProjectOwner', 'Status', 'ProjectResources', 'Budget', 'Budget Used'];
  // 'Start Date', 'End Date'
  import Vue from 'vue';
  import VueGoodTable from 'vue-good-table';

  Vue.use(VueGoodTable);

  export default {
    components: {
      LTable,
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
        allSelected: false,
        columns: [
          {
            label: 'Add project',
            sortable: false,
          },
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
        rows: [],
        displayProjectReport: [],

        // {selected: false, name:"John", age:20, joined: '20120201'},
        // {selected: false, name:"Jane", age:24, joined: '20120305'},
      };
    },
    methods: {
      fetchData() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/projects")
          .then(response => {
            info.rows = response.data;
            console.log('info.rows:' +info.rows);
            for (let i = 0; i < info.rows.length; i++){
              // info.rows[i].manager = info.rows[i].manager.name.toString();    *may need this to display name later
              info.displayProjectReport[i] = info.rows[i];
              console.log('info.rows['+i+']', info.rows[i]);
            }
          });
        console.log('info.displayProjectReport:', info.displayProjectReport);

      },
      addRow(id){
        this.added.push(id);
        console.log(id);
      },
    }
  }
</script>
<style>
</style>

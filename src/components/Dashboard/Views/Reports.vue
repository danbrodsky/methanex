<template>
  <div class="content" style="background-color: #FF69B4">
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
              :rows="rowsPortfolio"
              :paginate="true"
              :search-options="{ enabled: true, trigger: 'enter' }"
              :pagination-options="{enabled: true, perPage: 10}"
              styleClass="vgt-table striped bordered">
              <template slot="table-column" slot-scope="props">
                {{props.column.label}}
              </template>
              <template slot="table-row" slot-scope="props">
                {{ props.formattedRow[props.column.field] }}
              </template>
            </vue-good-table>
          </card>
          <card>
            <template slot="header">
              <div class="row">
                <div class="col-8">
                  <h4 class="card-title">All Projects</h4>
                  <p class="card-category">Select desired columns to display in project report</p>
                  <div class="row">
                    <div class="col-10">
                      <multiselect v-model="selectedProjectColumns"
                                   placeholder="Pick a column(s)"
                                   label="name"
                                   track-by="name"
                                   :options="columnFilterNames"
                                   :multiple="true"></multiselect>
                    </div>
                    <div class="col-2">
                      <div class="btn-group">
                        <button type="submit" id="projectColumnFilterSubmit" class="btn btn-info btn-fill float-left"
                                @click="selectProjectColumns">Apply
                        </button>
                        <button type="submit" id="projectColumnSelectAll" class="btn btn-info btn-fill float-left"
                                style="margin-right: 5px;" @click="selectAllProjectColumns">Show All
                        </button>
                      </div>
                    </div>
                  </div>
                  <pre class="language-json"></pre>
                </div>
                <div class="col-4">
                </div>
              </div>
            </template>
            <vue-good-table
              :columns="columnsProject"
              :rows="rowsProject"
              :paginate="true"
              :search-options="{ enabled: true, trigger: 'enter' }"
              :pagination-options="{enabled: true, perPage: 10}"
              styleClass="vgt-table striped bordered">
              <template slot="table-column" slot-scope="props">
                {{props.column.label}}
              </template>
              <template slot="table-row" slot-scope="props">
                {{ props.formattedRow[props.column.field] }}
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
              :rows="rowsResource"
              :paginate="true"
              :search-options="{ enabled: true, trigger: 'enter' }"
              :pagination-options="{enabled: true, perPage: 10}"
              styleClass="vgt-table striped bordered">
              <template slot="table-column" slot-scope="props">
                {{props.column.label}}
              </template>
              <template slot="table-row" slot-scope="props">
                {{ props.formattedRow[props.column.field] }}
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
  import Multiselect from 'vue-multiselect'
  import axios from 'axios'
  import VueJSONExcel from 'vue-json-excel'

  const tableColumns = ['Name', 'ProjectStatus', 'Manager', 'ProjectOwner', 'Status', 'ProjectResources', 'Budget', 'Budget Used'];
  // 'Start Date', 'End Date'

  import Vue from 'vue';
  import VueGoodTable from 'vue-good-table';
  import jsPdf from 'jspdf';

  Vue.use(VueGoodTable);

  export default {
    components: {
      Card,
      Multiselect,
      jsPdf,
      VueJSONExcel
    },

    name: 'Checkbox-table',
    created() {
      this.fetchData();
    },
    status: 'Checkbox-table',
    created() {
      this.fetchData();
    },
    rag_status: 'Checkbox-table',
    created() {
      this.fetchData();
    },
    businessOwner: 'Checkbox-table',
    created() {
      this.fetchData();
    },

    mounted: function () {
      this.initProjectColumnMap();
    },


    data() {
      return {
        treeData: {
          name: "Options:",
          children: [{name: "hello"}]
        },
        selectedProjectColumns: [],
        projectColumnsMap: new Map(),
        columnFilterNames: [
          {name: "Name"},
          {name: "Project Status"},
          {name: "Manager"},
          {name: "Project Owner"},
          {name: "RAG Status"},
          {name: "Number of Resources"},
          {name: "Budget"},
          {name: "Budget used"},
          {name: "Start Date"},
          {name: "End Date"},
        ],

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
          },
          {
            label: 'Start Date',
            field: 'start_date',
            filterable: true,
          },
          {
            label: 'End Date',
            field: 'end_date',
            filterable: true,
          }
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
          {
            label: 'Manager',
            field: 'manager',
            filterable: true,
          },
          {
            label: 'Status',
            field: 'status',
            filterable: true,
          },
          {
            label: 'Skills',
            field: 'skills',
            filterable: true,
          },
          {
            label: 'Non-Technical Skills',
            field: 'nonTechnicalSkills',
            filterable: true,
          },
        ],
        rowsPortfolio: [],
        rowsProject: [],
        rowsResource: []
      };
    },
    methods: {
      fetchData() {
        let info = this;
        axios.get(this.$root.serverURL + "/api/portfolios")
          .then(response => {
            info.rowsPortfolio = response.data;
            for (let i = 0; i < info.rowsPortfolio.length; i++) {
              if (info.rowsPortfolio[i].businessOwner != null)
                info.rowsPortfolio[i].businessOwner = info.rowsPortfolio[i].businessOwner.name;
              if (info.rowsPortfolio[i].classification != null)
                info.rowsPortfolio[i].classification = info.rowsPortfolio[i].classification.name;
            }
            console.log(response.data);
          });

        axios.get(this.$root.serverURL + "/api/projects")
          .then(response => {
            info.rowsProject = response.data;
            for (let i = 0; i < info.rowsProject.length; i++) {
              if (info.rowsProject[i].status != null)
                info.rowsProject[i].status = info.rowsProject[i].status.name;
              if (info.rowsProject[i].manager != null)
                info.rowsProject[i].manager = info.rowsProject[i].manager.name;
            }
          });
        axios.get(this.$root.serverURL + "/api/resources")
          .then(response => {
            console.log(response.data);
            info.rowsResource = response.data;
            for (let i = 0; i < info.rowsResource.length; i++) {
              if (info.rowsResource[i].manager != null)
                info.rowsResource[i].manager = info.rowsResource[i].manager.name;
              if (info.rowsResource[i].group != null)
                info.rowsResource[i].group = info.rowsResource[i].group.name;
              if (info.rowsResource[i].status != null)
                info.rowsResource[i].status = info.rowsResource[i].status.name;
            }
          })
      },

      initProjectColumnMap() {
        for (var i = 0; i < this.columnsProject.length; i++) {
          this.projectColumnsMap.set(this.columnsProject[i].label.toLowerCase(), this.columnsProject[i]);
        }
      },

      selectProjectColumns() {
        var columnsToDisplay = this.selectedProjectColumns;
        if (this.selectedProjectColumns.length > 0) {
          this.columnsProject = [];
          for (var i = 0; i < columnsToDisplay.length; i++) {
            this.columnsProject.push(this.projectColumnsMap.get(columnsToDisplay[i].name.toLowerCase()));
          }
        }
      },

      selectAllProjectColumns() {
        var iterator = this.projectColumnsMap.entries();
        var entry = iterator.next();
        if (!entry.done) {
          this.columnsProject = [];
        }
        while (!entry.done) {
          this.columnsProject.push(entry.value[1]);
          entry = iterator.next();
        }
      },
    }
  }
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css">
  .btn-group {
    display: inline;
  }
</style>


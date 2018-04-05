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
                                style="margin-right: 5px"
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
            <download-excel class="btn btn-info btn-fill float-left" :data="rowsProject" :fields="project_json_fields"
                            name="projects.csv" type="csv">Export as CSV
            </download-excel>
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
  Vue.component('downloadExcel', VueJSONExcel)

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
          {name: "Name",   value: "Name"},
          {name: "Project Status",  value: "Status"},
          {name: "Manager",  value: "Manager"},
          {name: "Project Owner",  value: "Project Owner"},
          {name: "RAG Status",  value: "RAG"},
          {name: "Number of Resources", value: "Number of Resources"},
          {name: "Budget",  value: "Budget"},
          {name: "Budget used",  value: "End"},
          {name: "Start Date",  value: "Start"},
          {name: "End Date", value: "End"},
        ],

        columnsPortfolio: [
          {
            label: 'Classification',
            field: 'classification',
            filterable: true,
          },
          {
            label: 'Business Owner',
            field: 'businessOwner',
            filterable: true,
          }
        ],
        columnsProject: [
          {
            label: 'Name',
            field: 'name',
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
            field: 'projectOwner',
            type: 'string',
            filterable: true,
          },
          {
            label: 'Start',
            field: 'startDate',
            filterable: true,
          },
          {
            label: 'End',
            field: 'endDate',
            filterable: true,
          },
          {
            label: 'Budget',
            field: 'budget',
            filterable: true,
          },
          {
            label: 'Status',
            field: 'status',
            filterable: true
          },
          {
            label: 'RAG',
            field: 'ragStatus',
            filterable: true
          },
          {
            label: '% Complete',
            field: 'percentageComplete',
            filterable: true
          },
          {
            label: 'Est. Remaining Cost',
            field: 'estimatedRemainingCost',
            filterable: true
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
        rowsResource: [],

        json_meta: [
          [
            {
              'key': 'charset',
              'value': 'utf-8'
            }
          ]
        ],

        project_json_fields: {},
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
            }
          });

        axios.get(this.$root.serverURL + "/api/projects")
          .then(response => {
            info.rowsProject = response.data;
            for (let i = 0; i < info.rowsProject.length; i++) {
              if (info.rowsProject[i].manager != null) {
                info.rowsProject[i].manager = info.rowsProject[i].manager.name;
              }
              if (info.rowsProject[i].projectOwner != null) {
                info.rowsProject[i].projectOwner = info.rowsProject[i].projectOwner.name;
              }
              if (info.rowsProject[i].status != null) {
                info.rowsProject[i].status = info.rowsProject[i].status.name;
              }
              if (info.rowsProject[i].ragStatus != null) {
                info.rowsProject[i].ragStatus = info.rowsProject[i].ragStatus.name;
              }
              if (info.rowsProject[i].startDate != null) {
                info.rowsProject[i].startDate = info.dateToString(info.rowsProject[i].startDate);
              }
              if (info.rowsProject[i].endDate != null) {
                info.rowsProject[i].endDate = info.dateToString(info.rowsProject[i].endDate);
              }
            }
          });
        axios.get(this.$root.serverURL + "/api/resources")
          .then(response => {
            info.rowsResource = response.data;
            for (let i = 0; i < info.rowsResource.length; i++) {
              if (info.rowsResource[i].manager != null) {
                info.rowsResource[i].manager = info.rowsResource[i].manager.name;
              }
              if (info.rowsResource[i].group != null) {
                info.rowsResource[i].group = info.rowsResource[i].group.name;
              }
              if (info.rowsResource[i].status != null) {
                info.rowsResource[i].status = info.rowsResource[i].status.name;
              }
              let tempSkills = "";
              info.rowsResource[i].skills.forEach(skill => tempSkills += skill.name + ", ");
              info.rowsResource[i].skills = tempSkills.substring(0, tempSkills.length - 2);
              let tempNonTechSkills = "";
              info.rowsResource[i].nonTechnicalSkills.forEach(skill => tempNonTechSkills += skill.name + ", ");
              info.rowsResource[i].nonTechnicalSkills = tempNonTechSkills.substring(0, tempNonTechSkills.length - 2);
            }
          })
          .catch(error => console.log(error));
      },
      dateToString(array) {
        return array[0].toString() + "." + array[1].toString() + "." + array[2].toString();
      },
      initProjectColumnMap() {
        for (var i = 0; i < this.columnsProject.length; i++) {
          var columnAttr = this.columnsProject[i];
          this.projectColumnsMap.set(columnAttr.label.toLowerCase(), columnAttr);
          this.project_json_fields[columnAttr.label] = columnAttr.field;
        }
      },
      selectProjectColumns() {
        var columnsToDisplay = this.selectedProjectColumns;
        if (this.selectedProjectColumns.length > 0) {
          this.columnsProject = [];
          this.project_json_fields = {};
          for (var i = 0; i < columnsToDisplay.length; i++) {
            var columnName = columnsToDisplay[i].value
            var columnAttr = this.projectColumnsMap.get(columnName.toLowerCase())
            this.columnsProject.push(columnAttr);

            this.project_json_fields[columnAttr.label] = {
              field: columnAttr.field,
              callback: (value) => {
                if (!value)
                  return '';
                return value;
              }
            }

          }
        }
      }
      ,

      selectAllProjectColumns() {
        var iterator = this.projectColumnsMap.entries();
        var entry = iterator.next();
        if (!entry.done) {
          this.columnsProject = [];
        }
        this.project_json_fields = {};
        while (!entry.done) {
          this.columnsProject.push(entry.value[1]);
          console.log(entry.value[1]);
          this.project_json_fields[entry.value[1].label] = {
            field: entry.value[1].field,
            callback: (value) => {
              if (!value)
                return '';
              return value;
            }
          }
          entry = iterator.next();
        }
        this.selectedProjectColumns = [];
      }
      ,
    }
  }
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css">
  .btn-group {
    display: inline;
  }
</style>


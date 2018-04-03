<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
            <vue-good-table
              :columns="columns"
              :rows="rows"
              :paginate="true"
              :search-options="{ enabled: true, trigger: 'enter' }"
              :pagination-options="{enabled: true, perPage: 5}"
              styleClass="vgt-table striped bordered">
              <template slot="table-column" slot-scope="props">
                <span v-if="props.column.label ==''">
                  <input @click="toggleSelectAll" type="checkbox"/>
                </span>
                <span v-else>
                  {{props.column.label}}
                </span>
              </template>
              <template slot="table-row" slot-scope="props">
                <span v-if="props.column.field === 'chkbx'">
                  <input type="checkbox" v-model="rows[props.row.originalIndex].selected"/>
                </span>
                <span v-else>
                  {{ props.formattedRow[props.column.field] }}
                </span>
              </template>
            </vue-good-table>
            <div class="text-center">
              <button type="submit" class="btn btn-info btn-fill float-left" @click.prevent="addProjects">
                Save
              </button>
              <button type="submit" class="btn btn-info btn-fill float-left" @click.prevent="cancel">
                Cancel
              </button>
            </div>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import axios from 'axios'

  export default {
    components: {
      Card
    },
    created() {
      this.fetchData();
    },
    data() {
      return {
        allSelected: false,
        selected: [],
        columns: [
          {
            label: '', // checkbox
            field: 'chkbx',
            sortable: false,
          },
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
        rows: [],
      };
    },
    methods: {
      toggleSelectAll() {
        this.allSelected = !this.allSelected;
        this.rows.forEach(row => {
          row.selected = this.allSelected;
        });
      },
      fetchData() {
        var info = this;
        let portfolioId = this.$route.params.portfolioId;
        axios.get(this.$root.serverURL + "/api/portfolios/difference?portfolioId=" + portfolioId)
          .then(response => {
            info.rows = response.data;
            for (let i = 0; i < info.rows.length; i++) {
              if (info.rows[i].manager != null) {
                info.rows[i].manager = info.rows[i].manager.name;
              }
              if (info.rows[i].projectOwner != null) {
                info.rows[i].projectOwner = info.rows[i].projectOwner.name;
              }
            }
          })
      },
      addProjects() {
        let info = this;
        let portfolioId = this.$route.params.portfolioId;
        let projects = [];
        this.rows.forEach(row => {
          if (row.selected) {
            projects.push(row.id);
          }
        });
        if (projects.length > 0) {
          this.$dialog.confirm("Are you sure you want to save these projects?", {
            loader: true
          })
            .then((dialog) => {
              axios.post(info.$root.serverURL + "/api/portfolios/" + portfolioId + "/" + "projects", projects)
                .then(() => {
                  dialog.close();
                  info.$router.push({path: `/admin/portfolio/${portfolioId}`});
                })
                .catch(() => {
                  console.log("error while adding projects");
                  dialog.close();
                })
            })
            .catch(() => {
              console.log('Delete aborted');
            });
        }
      },
      cancel() {
        let portfolioId = this.$route.params.portfolioId;
        info.$router.push({path: `/admin/portfolio/${portfolioId}`});
      }
    }
  }
</script>
<style>
</style>

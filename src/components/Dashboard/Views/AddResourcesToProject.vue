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
              :pagination-options="{enabled: true, perPage: 10}"
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
              <button type="submit" style="background-color: #FF69B4" class="btn btn-info btn-fill float-left" @click.prevent="addResources">
                Save
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
        let info = this;
        let projectId = this.$route.params.projectId;
        axios
          .get(this.$root.serverURL + "/api/projects/difference?projectId=" + projectId)
          .then(response => {
            info.rows = response.data;
            info.rows.forEach(row => {
              if (row.manager != null) {
                row.manager = row.manager.name;
              }
              if (row.group != null) {
                row.group = row.group.name;
              }
            });
          })
          .catch(error => console.log(error));
      },
      addResources() {
        let info = this;
        let projectId = this.$route.params.projectId;
        let resources = [];
        this.rows.forEach(row => {
          if (row.selected) {
            resources.push(row.id);
          }
        });
        if (resources.length > 0) {
          axios.post(this.$root.serverURL + "/api/projects/" + projectId + "/" + "resources", resources)
            .then(() => {
              info.$router.push({path: `/admin/project/${projectId}`});
            })
            .catch(() => console.log("error while adding resources"))
        }
      }
    }
  }
</script>
<style>
</style>

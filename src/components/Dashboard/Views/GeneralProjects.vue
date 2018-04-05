<template>
  <div class="content">
    <card>
      <button @click="addProjectPage" v-if="hasAccess()" v-b-modal.addProjectModal class="btn btn-info btn-fill float-right">
        <b style="font-size: large">+</b>
      </button>
      <vue-good-table
        :columns="columns"
        :paginate="true"
        :rows="rows"
        :search-options="{ enabled: true, trigger: 'enter' }"
        :pagination-options="{enabled: true, perPage: 20}"
        :globalSearch="false"
        styleClass="table condensed"
        :rowStyleClass="projectRowClass"
        @on-row-click="goToProject">
        <template slot="table-column" slot-scope="props">
          {{props.column.label}}
        </template>
        <template slot="table-row" slot-scope="props" @click="goToProject">
            {{ props.formattedRow[props.column.field] }}
        </template>
      </vue-good-table>
    </card>
  </div>
</template>
<script>
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import axios from 'axios'

  export default {
    components: {
      Card
    },
    name: 'general-projects',
    created() {
      let that = this;
      axios.get(this.$root.serverURL + "/user/" + JSON.parse(that.$root.$data.cookies.get('user')).id + "/roles")
        .then(response => {
          that.role = response.data[0].name;
        })
        .catch(error => console.log(error));
      this.fetchData();
    },
    data() {
      return {
        projectRowClass: "projectRow",
        role: '',
        allSelected: false,
        rows: [],
        columns: [
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
        ]
      }
    },
    methods: {
      goToProject() {

      },
      toggleSelectAll() {
        this.allSelected = !this.allSelected;
        this.rows.forEach(row => {
          row.selected = this.allSelected;
        });
      },
      addProjectPage() {
        this.$router.push({path: `/admin/add-project-general`});
      },
      fetchData() {
        let info = this;
        axios.get(this.$root.serverURL + "/api/projects")
          .then(response => {
            info.rows = response.data;
            for (let i = 0; i < info.rows.length; i++) {
              if (info.rows[i].manager != null) {
                info.rows[i].manager = info.rows[i].manager.name;
              }
              if (info.rows[i].projectOwner != null) {
                info.rows[i].projectOwner = info.rows[i].projectOwner.name;
              }
              if (info.rows[i].status != null) {
                info.rows[i].status = info.rows[i].status.name;
              }
              if (info.rows[i].ragStatus != null) {
                info.rows[i].ragStatus = info.rows[i].ragStatus.name;
              }
              if (info.rows[i].startDate != null) {
                info.rows[i].startDate = info.dateToString(info.rows[i].startDate);
              }
              if (info.rows[i].endDate != null) {
                info.rows[i].endDate = info.dateToString(info.rows[i].endDate);
              }
            }
          })
          .catch(error => console.log(error));
      },
      hasAccess() {
        return this.role == "ROLE_ADMIN";
      },
      dateToString(array) {
        return array[0].toString() + "." + array[1].toString() + "." + array[2].toString();
      },

      isResourceSelected(rowIndex) {
        return rowIndex == this.selectedResourceIndex;
      },

      resourceRowClass(index) {
        if (index == this.selectedResourceIndex) {
          return "selected_resource_row";
        }
        else {
          return "resource_row";
        }
      },

      selectBusinessOwner(row, index) {
        console.log(row);

        if (this.selectedResourceIndex == index) {
          row.selected = false;
          this.selectedResourceIndex = -1;
          this.prevSelected = null;
        }
        else {
          this.selectedResourceIndex = index;
          if (this.prevSelected) {
            this.prevSelected.selected = false;
          }
          this.prevSelected = row;
          row.selected = true;
        }
      },

      goToProject: function(params){
        this.$router.push({path :'/admin/project/' + params.row.id})
      },

    }
  }
</script>
<style>
  .projectRow:hover{
    background: #1DC7EA;
    border-color: #1DC7EA;
  }
</style>


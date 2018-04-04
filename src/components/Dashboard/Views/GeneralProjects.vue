<template>
  <div class="content">
    <card>
      <vue-good-table
        :columns="columns"
        :paginate="true"
        :rows="rows"
        :search-options="{ enabled: true, trigger: 'enter' }"
        :pagination-options="{enabled: true, perPage: 20}"
        :globalSearch="false"
        styleClass="table table-striped condensed">
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
              <input type="checkbox" v-model="rows[props.row.originalIndex].selected">
            </span>
          <span v-else>
            {{ props.formattedRow[props.column.field] }}
          </span>
        </template>
      </vue-good-table>
    </card>
  </div>
</template>
<script>
  import LTable from 'src/components/UIComponents/Table.vue'
  import Card from 'src/components/UIComponents/Cards/Card.vue'
  import axios from 'axios'

  export default {
    components: {
      LTable,
      Card
    },
    name: 'general-projects',
    created() {
      this.fetchData();
    },
    computed: {},

    data() {
      return {
        allSelected: false,
        rows: [],
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
        ]
      }
    },
    methods: {
      fetchData() {
        let info = this;
        axios.get(this.$root.serverURL + "/api/projects")
          .then(response => {
            info.rows = response.data;
            for (let i = 0; i < info.rows.length; i++) {
              if (info.rows[i].manager != null)
                info.rows[i].manager = info.rows[i].manager.name;
            }
          })
          .catch(error => console.log(error));
      },
      addRow(id) {
        this.added.push(id);
        console.log(id);
      },
      createPortfolio() {
        //axios post request here
      },

      getProperty(property) {
        if (!property) {
          return "";
        }
        return property;
      },

      getPropertyName(propertyObj) {
        if (!propertyObj) {
          return "";
        }
        return propertyObj.name;
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

      }

    }
  }
</script>
<style>
  .selected_resource_row {
    background-color: LimeGreen
  }
</style>

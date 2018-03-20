<template>
  <div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12">
          <card>
              <vue-good-table
                :columns="columns"
                :paginate="true"
                :rows="rows"
                :globalSearch = "false"
                styleClass="table table-striped condensed">
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
  export default {
    components: {
      LTable,
      Card
    },
    created () {
      this.fetchData();
    },
    data() {
      return {
        allSelected: false,
        columns: [
          {
            label: '',
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
          }
        ],
        rows: [],
      };
    },
    methods: {
      fetchData() {
        var info = this;
        axios.get(this.$root.serverURL + "/api/resources")
          .then(response => {
            info.rows = response.data;
            for (let i = 0; i < info.rows.length; i++){
              info.rows[i].manager = info.rows[i].manager.name;
            }
          })
      },
      addRow(id){
        this.added.push(id);
      }
    }
  }
</script>
<style>
</style>
